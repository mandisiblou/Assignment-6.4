package com.example.mandisi.myassign7.Persons;

/**
 * Created by Mandisi on 2016-06-09.
 */
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2016/04/09.
 */
public class PersonContact implements Serializable {
    private String id;
    private String contactTypeId;
    private String contactValue;
    private String status;
    private Date date;
    private String state;
    private PersonContact(){}

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getContactValue() {
        return contactValue;
    }

    public String getContactTypeId() {
        return contactTypeId;
    }

    public PersonContact(Builder builder) {
        this.id = builder.id;
        this.contactTypeId= builder.contactTypeId;
        this.contactValue = builder.contactValue;
        this.date = builder.date;
        this.state = builder.state;
        this.status = builder.status;
    }

    public static class Builder{
        private String id;
        private String contactTypeId;
        private String contactValue;
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

        public Builder contactTypeId(String value){
            this.contactTypeId =value;
            return this;
        }

        public Builder contactValue(String value){
            this.contactValue =value;
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

        public Builder copy(PersonContact value){
            this.id = value.id;
            this.contactTypeId= value.contactTypeId;
            this.contactValue=value.contactValue;
            this.state=value.state;
            this.date=value.date;
            this.status=value.status;
            return  this;
        }

        public PersonContact build(){
            return new PersonContact(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonContact that = (PersonContact) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

