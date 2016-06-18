package com.example.mandisi.myassign7.restapi.settings;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/29.
 */
public class AddressTypeResource implements Serializable {
    private String name;
    private String state;
    private String serverId;

    private AddressTypeResource() {
    }

    public AddressTypeResource(Builder builder) {
        this.name = builder.name;
        this.state = builder.state;
        this.serverId = builder.serverId;
    }

    public String getServerId() {
        return serverId;
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

        public Builder copy(AddressTypeResource addressType) {
            this.name = addressType.name;
            this.state = addressType.state;
            this.serverId = addressType.serverId;

            return this;
        }

        public AddressTypeResource build() {
            return new AddressTypeResource(this);
        }
    }
}
