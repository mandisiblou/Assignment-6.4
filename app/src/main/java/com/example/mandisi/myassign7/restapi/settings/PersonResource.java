package com.example.mandisi.myassign7.restapi.settings;

import java.io.Serializable;

/**
 * Created by Mandisi on 2016/04/24.
 */
public class PersonResource implements Serializable {
    private String serverId;
    private String firstName;
    private String emailAddress;
    private String lastName;
    private String authvalue;
    private String organisation;
    private String token;

    public String getServerId() {
        return serverId;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAuthvalue() {
        return authvalue;
    }

    public PersonResource(Builder builder) {
        this.serverId = builder.serverId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.authvalue = builder.authvalue;
        this.emailAddress = builder.emailAddress;
        this.token = builder.token;
        this.organisation = builder.organisation;
    }

    public static class Builder {
        private String serverId;
        private String firstName;
        private String emailAddress;
        private String lastName;
        private String authvalue;
        private String token;
        private String organisation;

        public Builder serverId(String value) {
            this.serverId = value;
            return this;
        }

        public Builder organisation(String value) {
            this.organisation = value;
            return this;
        }

        public Builder firstName(String value) {
            this.firstName = value;
            return this;
        }

        public Builder token(String value) {
            this.token = value;
            return this;
        }

        public Builder emailAddress(String value) {
            this.emailAddress = value;
            return this;
        }

        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        public Builder authvalue(String value) {
            this.authvalue = value;
            return this;
        }

        public PersonResource build() {
            return new PersonResource(this);
        }
    }
}
