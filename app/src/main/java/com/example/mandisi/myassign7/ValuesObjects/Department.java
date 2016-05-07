package com.example.mandisi.myassign7.ValuesObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
import java.io.Serializable;


public class Department implements Serializable {
    private Long SID;
    private String name;

    public Long getSID() {
        return SID;
    }

    public String getName() {
        return name;
    }

    private Department() {
    }

    public Department(Builder builder) {
        this.SID = builder.SID;
        this.name = builder.name;
    }
    public static class Builder {
        private Long SID;
        private String name;

        public Builder SID(Long SID) {
            this.SID = SID;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder copy(Department value) {
            this.SID = value.SID;
            this.name = value.name;
            return this;
        }
        public Department build() {
            return new Department(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (SID != null ? !SID.equals(that.SID) : that.SID != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = SID != null ? SID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
