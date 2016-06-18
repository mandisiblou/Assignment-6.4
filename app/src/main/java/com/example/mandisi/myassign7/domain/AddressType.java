package com.example.mandisi.myassign7.domain;

import java.io.Serializable;

/**
 *  Mandisi on 2016/04/09.
 */
public class AddressType implements Serializable {

    private Long id;
    private String name;
    private String state;
    private String serverId;

    private AddressType(){}

    public AddressType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.state = builder.state;
        this.serverId = builder.serverId;
    }

    public String getServerId() {
        return serverId;
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

        public Builder copy(AddressType addressType){
            this.id = addressType.id;
            this.name = addressType.name;
            this.state = addressType.state;
            this.serverId = addressType.serverId;

            return this;
        }

        public AddressType build(){return new AddressType(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressType that = (AddressType) o;

        return serverId != null ? serverId.equals(that.serverId) : that.serverId == null;

    }

    @Override
    public int hashCode() {
        return serverId != null ? serverId.hashCode() : 0;
    }
}
