package com.example.mandisi.myassign7.EntityObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
import java.io.Serializable;
import java.security.KeyStore;

public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private int yearOfBirth;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public Person() {
    }

    public Person(Builder builder) {
        this.name = builder.name;
        this.yearOfBirth = builder.yearOfBirth;
        this.id = builder.id;
    }

    public static class Builder{
        private  Long id;
        private String name;
        private int yearOfBirth;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public Builder copy(Person value) {

            this.name = value.name;
            this.yearOfBirth = value.yearOfBirth;
            this.id = value.id;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (yearOfBirth != person.yearOfBirth) return false;
        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + yearOfBirth;
        return result;
    }
}
