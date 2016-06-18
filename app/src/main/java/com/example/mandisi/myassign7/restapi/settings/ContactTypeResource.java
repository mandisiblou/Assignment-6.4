package com.example.mandisi.myassign7.restapi.settings;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/29.
 */
public class ContactTypeResource implements Serializable {
    private String name;
    private String state;
    private String serverId;

    private ContactTypeResource() {
    }

    public String getServerId() {
        return serverId;
    }

    public ContactTypeResource(Builder builder) {
        this.name = builder.name;
        this.state = builder.state;
        this.serverId = builder.serverId;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static class Builder {
        private String name;
        private String state;
        private String serverId;

        public Builder serverId(String id) {
            this.serverId = id;
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

        public Builder copy(ContactTypeResource contactType) {
            this.name = contactType.name;
            this.state = contactType.state;
            this.serverId = contactType.serverId;
            return this;
        }

        public ContactTypeResource build() {
            return new ContactTypeResource(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
