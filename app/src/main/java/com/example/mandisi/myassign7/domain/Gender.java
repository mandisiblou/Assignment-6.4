package com.example.mandisi.myassign7.domain;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/09.
 */
public class Gender implements Serializable {

    private Long id;
    private String name;
    private String state;
    private String serverId;

    private Gender(){}

    public String getServerId() {
        return serverId;
    }
    public  Gender(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.state = builder.state;
        this.serverId = builder.serverId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private Long id;
        private String name;
        private String state;
        private String serverId;

        public Builder serverId(String id) {
            this.serverId = id;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }
        public Builder copy(Gender gender){
            this.id = gender.id;
            this.name = gender.name;
            this.state = gender.state;
            this.serverId = gender.serverId;

            return this;
        }
        public Gender build(){
            return new Gender(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gender gender = (Gender) o;

        return id != null ? id.equals(gender.id) : gender.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
