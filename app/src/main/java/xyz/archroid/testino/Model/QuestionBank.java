package xyz.archroid.testino.Model;

public class QuestionBank {

    private String QUESTION_BANK_ID;
    private String QUESTION_BANK_CREATOR;
    private String QUESTION_BANK_NAME;

    public QuestionBank(String QUESTION_BANK_ID, String QUESTION_BANK_CREATOR, String QUESTION_BANK_NAME) {
        this.QUESTION_BANK_ID = QUESTION_BANK_ID;
        this.QUESTION_BANK_CREATOR = QUESTION_BANK_CREATOR;
        this.QUESTION_BANK_NAME = QUESTION_BANK_NAME;
    }

    public QuestionBank() {
    }

    public String getQUESTION_BANK_ID() {
        return QUESTION_BANK_ID;
    }

    public void setQUESTION_BANK_ID(String QUESTION_BANK_ID) {
        this.QUESTION_BANK_ID = QUESTION_BANK_ID;
    }

    public String getQUESTION_BANK_CREATOR() {
        return QUESTION_BANK_CREATOR;
    }

    public void setQUESTION_BANK_CREATOR(String QUESTION_BANK_CREATOR) {
        this.QUESTION_BANK_CREATOR = QUESTION_BANK_CREATOR;
    }

    public String getQUESTION_BANK_NAME() {
        return QUESTION_BANK_NAME;
    }

    public void setQUESTION_BANK_NAME(String QUESTION_BANK_NAME) {
        this.QUESTION_BANK_NAME = QUESTION_BANK_NAME;
    }
}
