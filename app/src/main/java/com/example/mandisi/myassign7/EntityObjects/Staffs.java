package com.example.mandisi.myassign7.EntityObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
import java.io.Serializable;
import java.security.KeyStore;

public class Staffs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long SID;
    private int yearOfBirth;
    private String name;

    public Long getSID() {
        return SID;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getName() {
        return name;
    }


    private Staffs() {
    }

    public Staffs(Builder builder) {
        this.SID = builder.SID;
        this.yearOfBirth = builder.yearOfBirth;
        this.name = builder.name;
    }

    public static class Builder{
        private Long SID;
        private int yearOfBirth;
        private String name;

        public Builder SID(Long SID) {
            this.SID = SID;
            return this;
        }

        public Builder yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder copy(Staffs value) {
            this.SID = value.SID;
            this.yearOfBirth = value.yearOfBirth;
            this.name = value.name;
            return this;
        }
        public Staffs build() {
            return new Staffs(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staffs)) return false;

        Staffs staffs = (Staffs) o;

        if (yearOfBirth != staffs.yearOfBirth) return false;
        if (SID != null ? !SID.equals(staffs.SID) : staffs.SID != null) return false;
        return name != null ? name.equals(staffs.name) : staffs.name == null;

    }

    @Override
    public int hashCode() {
        int result = SID != null ? SID.hashCode() : 0;
        result = 31 * result + yearOfBirth;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}