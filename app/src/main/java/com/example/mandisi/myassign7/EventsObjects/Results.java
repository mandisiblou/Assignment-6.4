package com.example.mandisi.myassign7.EventsObjects;

/**
 * Created by 211014486 on 4/17/2016.
 */
public class Results {
    private static final long serialVersionUID = 1L;
    private Long resultsID;
    private String questionName;
    private String questions;
    private String corrects;

    public Long getResultsID() {
        return resultsID;
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

    public Results() {
    }

    public Results(Builder builder) {
        this.resultsID = builder.resultsID;
        this.questionName = builder.questionName;
        this.questions = builder.questions;
        this.corrects = builder.corrects;
    }
    public static class Builder {
        private Long resultsID;
        private String questionName;
        private String questions;
        private String corrects;

        public Builder resultsID(Long resultsID) {
            this.resultsID = resultsID;
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
        public Builder copy(Results value) {
            this.resultsID = value.resultsID;
            this.questionName = value.questionName;
            this.questions = value.questions;
            this.corrects = value.corrects;
            return this;
        }
        public Results build() {
            return new Results(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;

        Results questions1 = (Results) o;

        if (resultsID != null ? !resultsID.equals(questions1.resultsID) : questions1.resultsID != null)
            return false;
        if (questionName != null ? !questionName.equals(questions1.questionName) : questions1.questionName != null)
            return false;
        if (questions != null ? !questions.equals(questions1.questions) : questions1.questions != null) return false;
        return corrects != null ? corrects.equals(questions1.corrects) : questions1.corrects == null;

    }

    @Override
    public int hashCode() {
        int result = resultsID != null ? resultsID.hashCode() : 0;
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (corrects != null ? corrects.hashCode() : 0);
        return result;
    }
}