package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.LoginDao;
import com.hawaiianpizza.inventory.model.GoogleUser;
import com.hawaiianpizza.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ssafystudy@gmail.com";

    public User googleLogin(GoogleUser guser){

        //todo :  user 가입 여부 확인, 없을시 가입

        try{
            Optional<User> user = loginDao.findById(guser.getId());
            if(loginDao.findById(guser.getId()).isPresent()){
                System.out.println("user login");
            }
            else
            {
                User suser = new User();
                suser.setId(guser.getId());
                suser.setEmail(guser.getEmail());
                suser.setName(guser.getName());
                suser.setAccess_token(guser.getAccess_token());
                System.out.println(suser);
                loginDao.save(suser);
                System.out.println("save");
            }
            return user.get();
        }
        catch (Exception e)
        {
            User suser = new User();
            System.out.println("user join");
            suser.setId(guser.getId());
            suser.setEmail(guser.getEmail());
            suser.setName(guser.getName());
            suser.setAccess_token(guser.getAccess_token());
            loginDao.save(suser);
            return suser;
        }

    }

    // 랜덤코드 및 패스워드 생성
    public String ramdom(int num){
        String codechar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder createCode = new StringBuilder();
        for(int i = 0;i<num;i++) {
            createCode.append(codechar.charAt((int) (Math.random() * codechar.length())));
        }
        return createCode.toString();
    }

    // 이메일 전송
    public void sendMail(String title, String toEmail, String text){
        System.out.println("메일전송");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(FROM_ADDRESS);
        message.setSubject(title);
        message.setText(text);

        mailSender.send(message);
        System.out.println(toEmail+"로 메일 전송 성공");
    }
}