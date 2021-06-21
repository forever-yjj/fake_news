package com.zm.news.controller;

import com.zm.news.entity.User;
import com.zm.news.redis.UserKey;
import com.zm.news.annotation.LoginRequired;
import com.zm.news.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: news
 * @package: com.zm.news.controller
 * @className: SystemController
 * @author: ZM
 * @description: 系统控制器
 * @date: 2021/1/25 13:24
 * @version: 1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    private HashMap<String, Object> map = new HashMap<>();

    @RequestMapping ("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Boolean login(User user) {
        User users = userService.checkUserNameAndPassword(user);
        if(users != null) {
            users.setPassword(null);
            session.setAttribute("user", users);
            return true;
        }
        return false;
    }

    @GetMapping("/loginOut")
    public String loginOut() {
        session.setAttribute("user", null);
        return "forward:/system/toLogin";
    }

    @LoginRequired
    @GetMapping("/toSetting")
    public String toSetting(Model model) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userService.findUserById(user.getUserId()));
        return "view/user-setting";

    }

    @LoginRequired
    @GetMapping("/toPassword")
    public String toPassword() {
        return "view/user-password";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }


    @PostMapping("/checkCode")
    @ResponseBody
    public HashMap<String, Object> checkCode(String checkCode) {
        String verifyCode = (String) session.getAttribute("verifyCode");
        //忽略大小写
        if(verifyCode.equalsIgnoreCase(checkCode)) {
            map.put("flag", true);
            return map;
        }
        map.put("flag", false);
        return map;
    }

    @PostMapping("/sendCode")
    @ResponseBody
    public Boolean getCheckCode(String username) {

        //创建四位验证码
        String code = UUID.randomUUID().toString().substring(0, 4);
        //放入缓存，设置过期时间60秒
        redis.set(UserKey.getCode.getPrefix(), code, 60L);
        //放入消息队列
        rabbitTemplate.convertAndSend("code", "code.send", code);
        //发送验证码
        return true;
    }

    @PostMapping("/register")
    @ResponseBody
    public HashMap<String, Object> register(User user, String checkCode) {
        String str = (String) redis.get(UserKey.getCode.getPrefix());
        if(str.equals(checkCode)){
            userService.register(user);
            map.put("msg", "注册成功");
            map.put("flag", true);
        }else {
            map.put("msg", "验证码错误");
            map.put("flag", false);
        }
        return map;
    }
    /**
     * 获取验证码图片
     */
    @RequestMapping("/getVerifyCode")
    public void getVerificationCode() throws IOException {
        int width = 200;
        int height = 69;

        BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //生成对应宽高的初始图片
        String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

        //单独的一个类方法，出于代码复用考虑，进行了封装。
        //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符

        session.setAttribute("verifyCode", randomText);
        System.out.println(randomText);
        //必须设置响应内容类型为图片，否则前台不识别
        response.setContentType("image/png");
        //获取文件输出流
        OutputStream os = response.getOutputStream();
        //输出图片流
        ImageIO.write(verifyImg,"png",os);

        os.flush();
        //关闭流
        os.close();
    }
}
