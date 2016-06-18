package com.example.mandisi.myassign7.restapi.settings;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/29.
 */
public class GenderResource implements Serializable {
    private String name;
    private String state;
    private String serverId;

    private GenderResource() {
    }

    public String getServerId() {
        return serverId;
    }

    public GenderResource(Builder builder) {
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

        public Builder copy(GenderResource gender) {

            this.name = gender.name;
            this.state = gender.state;
            this.serverId = gender.serverId;

            return this;
        }

        public GenderResource build() {
            return new GenderResource(this);
        }
    }
}
