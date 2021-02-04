package com.jtrio.zagzag.util;

public class Nickname {
    public static String getNick(String email) {
        String nick = email.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
        return nick;
    }
}
