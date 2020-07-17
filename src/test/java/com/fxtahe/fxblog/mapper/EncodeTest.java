package com.fxtahe.fxblog.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {

    public static void main(String[] args) {
        String password= "$2a$10$dPawqV2YrKPwLZ41DXONtORf59L81Z/28vsI37EkZvpLLMyIL/3fm";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String xyz = bCryptPasswordEncoder.encode("xyz123456");
        System.out.println(xyz);
    }
}
