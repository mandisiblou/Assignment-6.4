package com.example.mandisi.myassign7.Persons;

/**
 * Created by Mandisi on 2016-06-09.
 */
import java.io.Serializable;

public class Persons implements Serializable {
    private Long id;
    private String serverId;
    private String firstName;
    private String emailAddress;
    private String lastName;
    private String authvalue;
    private String organisation;
    private String token;
    private String state;


    private Persons() {

    }

    public String getServerId() {
        return serverId;
    }

    public Long getId() {
        return id;
    }

    public String getAuthvalue() {
        return authvalue;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getToken() {
        return token;
    }

    public String getState() {return state;
    }

    public Persons(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.authvalue = builder.authvalue;
        this.organisation = builder.organisation;
        this.token = builder.token;
        this.serverId = builder.serverId;
        this.state = builder.state;
    }

    public static class Builder {
        private Long id;
        private String serverId;
        private String firstName;
        private String emailAddress;
        private String lastName;
        private String authvalue;
        private String organisation;
        private String token;
        private String state;


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }


        public Builder organisation(String value) {
            this.organisation = value;
            return this;
        }

        public Builder serverId(String value) {
            this.serverId = value;
            return this;
        }

        public Builder token(String value) {
            this.token = value;
            return this;
        }

        public Builder firstName(String value) {
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        public Builder emailAddress(String value) {
            this.emailAddress = value;
            return this;
        }

        public Builder authvalue(String value) {
            this.authvalue = value;
            return this;

        }
        public Builder copy(Persons value) {
            this.id = value.id;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.emailAddress = value.emailAddress;
            this.authvalue = value.authvalue;
            this.organisation = value.organisation;
            this.token = value.token;
            this.serverId = value.serverId;
            this.state = value.state;
            return this;
        }

        public Persons build() {
            return new Persons(this);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persons person = (Persons) o;

        return id != null ? id.equals(person.id) : person.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

