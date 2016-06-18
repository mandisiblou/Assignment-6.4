package com.example.mandisi.myassign7.Servic.Person;

/**
 * Created by Mandisi on 2016/05/29.
 */
public interface LoginService {
    boolean checkActivition();

    boolean checkLogin(String emailAddress, String password);
}
