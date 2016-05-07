package com.example.mandisi.myassign7.EntityObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.ValuesObjects.Addrec;

import java.io.Serializable;

public class Students implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long studentID;
    private int yearOfBirth;
    private String name;
    private Addrec addrec;

    public Long getSID() {
        return studentID;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getName() {
        return name;
    }

    /**
     * Return the address of this Students.
     */
    public Addrec getAddress()
    {
        return addrec;
    }



    private Students() {
    }

    public Students(Builder builder) {
        this.studentID = builder.studentID;
        this.yearOfBirth = builder.yearOfBirth;
        this.addrec = builder.addrec;
        this.name = builder.name;
    }

    public static class Builder{
        private Long studentID;
        private int yearOfBirth;
        private String name;
        private Addrec addrec;

        public Builder studentID(Long studentID) {
            this.studentID = studentID;
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

        public Builder addrec(Addrec value){
            this.addrec =value;
            return this;
        }

        public Builder copy(Students value) {
            this.studentID = value.studentID;
            this.yearOfBirth = value.yearOfBirth;
            this.name = value.name;
            this.addrec=value.addrec;

            return this;
        }
        public Students build() {
            return new Students(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Students)) return false;

        Students students = (Students) o;

        if (yearOfBirth != students.yearOfBirth) return false;
        if (studentID != null ? !studentID.equals(students.studentID) : students.studentID != null) return false;
        return name != null ? name.equals(students.name) : students.name == null;

    }

    @Override
    public int hashCode() {
        int result = studentID != null ? studentID.hashCode() : 0;
        result = 31 * result + yearOfBirth;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
