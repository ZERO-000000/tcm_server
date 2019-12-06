package com.login.controller;

import com.base.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController{

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestParam String name,@RequestParam String passWord){
        System.out.println("登录人"+name+"密码："+passWord);
        Map<String,Object> resultMap = new HashMap<>();
        // 从SecurityUtils里边创建一个 subject
        Subject currentUser = SecurityUtils.getSubject();
        ////密码可以通过SimpleHash加密，保存到UsernamePasswordToken，用于验证
        String newPassword = new SimpleHash(
                "md5",
                passWord,
                ByteSource.Util.bytes("small"),
                2).toHex();
        System.out.println("登录人密码："+newPassword);
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(name, newPassword);
        //用户登录操作
        try{
            currentUser.login(token);
            resultMap.put("code","200");
            resultMap.put("msg","用户登录成功");
            resultMap.put("token",currentUser.getSession().getId());
        }catch (AuthenticationException e){
            //登录失败原因 1 用户不存在 2 用户密码不正确
            resultMap.put("code","-1");
            resultMap.put("msg","用户名或者密码错误！");
        }
        return resultMap;
    }
    @RequestMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    @RequestMapping("/checkLoginSession")
    public void checkLoginSession(String token, HttpServletResponse response) {
        SessionKey sessionKey = new SessionKey() {
            @Override
            public Serializable getSessionId() {
                return token;
            }
        };
        try {
            Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
            System.out.println("登录的******"+token+"******session未过期");
        } catch (UnknownSessionException e) {
            response.setStatus(999);
            System.out.println("登录的******"+token+"******session已过期");
            e.printStackTrace();
        }
    }
}
