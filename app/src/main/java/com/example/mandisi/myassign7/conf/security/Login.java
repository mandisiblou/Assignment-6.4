package com.example.mandisi.myassign7.conf.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Mandisi on 2016/04/10.
 */
public class Login {

    public static boolean checkLogin(String email, String password) {


        return BCrypt.checkpw(password, "hashed");
    }
}
