package com.example.mandisi.myassign7.Persons;

/**
 * Created by Mandisi on 2016-06-09.
 */
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2016/04/09.
 */
public class PersonAddress implements Serializable {
    private String id;
    private String description;
    private String postalCode;
    private String addressTypeId;
    private String status;
    private Date date;
    private String state;

    private PersonAddress(){}

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public PersonAddress(Builder builder) {
        this.id = builder.id;
        this.description= builder.description;
        this.postalCode = builder.postalCode;
        this.addressTypeId = builder.addressTypeId;
        this.date = builder.date;
        this.state = builder.state;
        this.status = builder.status;
    }

    public static class Builder{
        private String id;
        private String description;
        private String postalCode;
        private String addressTypeId;
        private String status;
        private Date date;
        private String state;

        public Builder id(String value) {
            this.id =value;
            return this;
        }

        public Builder date(Date value){
            this.date =value;
            return this;
        }

        public Builder description(String value){
            this.description =value;
            return this;
        }

        public Builder postalCode(String value){
            this.postalCode =value;
            return this;
        }

        public Builder status(String value){
            this.status =value;
            return this;
        }

        public Builder state(String value){
            this.state =value;
            return this;
        }

        public Builder addressTypeId(String value){
            this.addressTypeId =value;
            return this;
        }

        public Builder copy(PersonAddress value){
            this.id = value.id;
            this.description= value.description;
            this.addressTypeId=value.addressTypeId;
            this.state=value.state;
            this.date=value.date;
            this.status=value.status;
            this.postalCode = value.postalCode;
            return  this;
        }

        public PersonAddress build(){
            return new PersonAddress(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonAddress that = (PersonAddress) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
