package com.example.mandisi.myassign7.SettingsFactory;

import com.example.mandisi.myassign7.domain.Settings;

/**
 * Created by Mandisi on 2016/04/14.
 */
public class SettingsFactory {
    public static Settings getSettings(String email, String orgCode, String password){
        return new Settings.Builder()
                .username(email)
                .password(password)
                .code(orgCode)
                .build();

    }
}
