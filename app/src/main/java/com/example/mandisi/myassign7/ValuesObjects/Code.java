package com.example.mandisi.myassign7.ValuesObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
import java.io.Serializable;

public class Code implements Serializable {
    private Long codeId;
    private static final long serialVersionUID = 1L;
    private String name;

    public Long  getCodeId() {
        return codeId;
    }

    public String getName() {
        return name;
    }

    public Code() {
    }


    public Code(Builder builder) {
        this.codeId = builder.codeId;
        this.name = builder.name;
    }

    public static class Builder {
        private Long  codeId;
        private static final long serialVersionUID = 1L;
        private String name;

        public Builder codeId(Long  codeId) {
            this.codeId = codeId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder copy(Code value) {
            this.codeId = value.codeId;
            this.name = value.name;
            return this;
        }

        public Code build() {
            return new Code(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code)) return false;

        Code code = (Code) o;

        if (codeId != null ? !codeId.equals(code.codeId) : code.codeId != null) return false;
        return name != null ? name.equals(code.name) : code.name == null;

    }

    @Override
    public int hashCode() {
        int result = codeId != null ? codeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}