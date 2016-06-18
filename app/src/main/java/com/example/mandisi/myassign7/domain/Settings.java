package com.example.mandisi.myassign7.domain;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/09.
 */
public class Settings implements Serializable{
    private Long id;
    private String code;
    private String username;
    private String password;

    private Settings(){}

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Settings(Builder builder) {
        this.id=builder.id;
        this.code =builder.code;
        this.password= builder.password;
        this.username = builder.username;
    }

    public static class  Builder{
        private Long id;
        private String code;
        private String username;
        private String password;

        public Builder id(Long value){
            this.id=value;
            return this;
        }

        public Builder code(String value){
            this.code=value;
            return this;
        }

        public Builder username(String value){
            this.username=value;
            return this;
        }

        public Builder password(String value){
            this.password=value;
            return this;
        }

        public Builder copy(Settings value){
            this.password=value.password;
            this.username=value.username;
            this.code = value.code;
            this.id = value.id;
            return this;
        }

        public Settings build(){
            return new Settings(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        return id.equals(settings.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
