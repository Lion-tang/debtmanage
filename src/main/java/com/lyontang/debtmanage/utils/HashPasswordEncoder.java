package com.lyontang.debtmanage.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class HashPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return Md5Utils.code( charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(Md5Utils.code(charSequence .toString()));
    }
}
