package com.fxtahe.fxblog.mapper;

import java.util.regex.Pattern;

public class EncodeTest {

    public static void main(String[] args) {
        String url = "/user/article/feature";
        String pattern = "*/user/\\**";
        Pattern compile = Pattern.compile(pattern);
        boolean matches = compile.matcher(pattern).matches();
        System.out.println(matches);
    }
}
