package com.example.mandisi.myassign7.restapi.settings;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/29.
 */
public class Credential implements Serializable {
    private String email;
    private String value;

    public Credential(Builder builder) {
        this.email = builder.email;
        this.value = builder.value;
    }

    public String getEmail() {
        return email;
    }



    public String getValue() {
        return value;
    }


    public static class Builder {
        private String email;
        private String value;

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Credential build() {
            return new Credential(this);
        }
    }
}
