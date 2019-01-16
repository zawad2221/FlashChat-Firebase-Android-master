package com.dhakanewsclub.destini;

public class Story {
    private int questionId,answerID_1, answerID_2;

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerID_1() {
        return answerID_1;
    }

    public int getAnswerID_2() {
        return answerID_2;
    }

    public Story(int questionId) {

        this.questionId = questionId;

    }

    public Story(int questionId, int answerID_1, int answerID_2) {
        this.questionId = questionId;
        this.answerID_1 = answerID_1;
        this.answerID_2 = answerID_2;
    }
}
