package org.skypro.generator.model;

import java.util.Objects;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() ) return false;
        Question otherQuestion = (Question) obj;
        return Objects.equals(question, otherQuestion.question) && Objects.equals(answer, otherQuestion.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

}
