package com.example.mandisi.myassign7.ValuesObjects;

import java.io.Serializable;

/**
 * Created by 211014486 on 4/17/2016.
 */
public class Addrec implements Serializable {
    private Long addressId;
    private String street;
    private String town;
    private String postCode;
    private String country;

    public Long getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public Addrec() {
    }
    public Addrec(Builder builder) {
        this.addressId = builder.addressId;
        this.country = builder.country;
        this.postCode = builder.postCode;
        this.street = builder.street;
        this.town = builder.town;
    }
    public static class Builder {
        private Long addressId;
        private String street;
        private String town;
        private String postCode;
        private String country;

        public Builder addressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder town(String town) {
            this.town = town;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder copy(Addrec value) {
            this.addressId = value.addressId;
            this.country = value.country;
            this.postCode = value.postCode;
            this.street = value.street;
            this.town = value.town;
            return this;
        }

        public Addrec build() {
            return new Addrec(this);
        }

    }
}