package com.example.mandisi.myassign7.ValuesObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
public class Questions {
    private static final long serialVersionUID = 1L;
    private Long questionID;
    private String questionName;
    private String questions;
    private String corrects;

    public Long getQuestionID() {
        return questionID;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getQuestions() {
        return questions;
    }

    public String getCorrects() {
        return corrects;
    }

    public Questions() {
    }

    public Questions(Builder builder) {
        this.questionID = builder.questionID;
        this.questionName = builder.questionName;
        this.questions = builder.questions;
        this.corrects = builder.corrects;
    }
    public static class Builder {
        private Long questionID;
        private String questionName;
        private String questions;
        private String corrects;

        public Builder questionID(Long questionID) {
            this.questionID = questionID;
            return this;
        }

        public Builder questionName(String questionName) {
            this.questionName = questionName;
            return this;
        }

        public Builder questions(String questions) {
            this.questions = questions;
            return this;
        }

        public Builder corrects(String corrects) {
            this.corrects = corrects;
            return this;
        }
        public Builder copy(Questions value) {
            this.questionID = value.questionID;
            this.questionName = value.questionName;
            this.questions = value.questions;
            this.corrects = value.corrects;
            return this;
        }
        public Questions build() {
            return new Questions(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questions)) return false;

        Questions questions1 = (Questions) o;

        if (questionID != null ? !questionID.equals(questions1.questionID) : questions1.questionID != null)
            return false;
        if (questionName != null ? !questionName.equals(questions1.questionName) : questions1.questionName != null)
            return false;
        if (questions != null ? !questions.equals(questions1.questions) : questions1.questions != null) return false;
        return corrects != null ? corrects.equals(questions1.corrects) : questions1.corrects == null;

    }

    @Override
    public int hashCode() {
        int result = questionID != null ? questionID.hashCode() : 0;
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (corrects != null ? corrects.hashCode() : 0);
        return result;
    }
}