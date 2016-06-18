package com.example.mandisi.myassign7.domain;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/09.
 */
public class ContactType implements Serializable{

    private Long id;
    private String name;
    private String state;
    private String serverId;

    private ContactType(){}

    public String getServerId() {
        return serverId;
    }

    public ContactType(Builder builder) {
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

        public Builder copy(ContactType contactType){
            this.id = contactType.id;
            this.name = contactType.name;
            this.state = contactType.state;
            this.serverId = contactType.serverId;
            return this;
        }

        public ContactType build(){return new ContactType(this);}
    }
    public static Builder builder(){return new Builder();}


}
