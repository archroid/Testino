package xyz.archroid.testino.Model;

public class Question {

    private String QUESTION_ID;
    private String QUESTION_TITLE;
    private String QUESTION_A;
    private String QUESTION_B;
    private String QUESTION_C;
    private String QUESTION_D;
    private String QUESTION_ANSWER;
    private String QUESTION_BANK_ID;

    public Question(String QUESTION_ID, String QUESTION_TITLE, String QUESTION_A, String QUESTION_B, String QUESTION_C, String QUESTION_D, String QUESTION_ANSWER, String QUESTION_BANK_ID) {
        this.QUESTION_ID = QUESTION_ID;
        this.QUESTION_TITLE = QUESTION_TITLE;
        this.QUESTION_A = QUESTION_A;
        this.QUESTION_B = QUESTION_B;
        this.QUESTION_C = QUESTION_C;
        this.QUESTION_D = QUESTION_D;
        this.QUESTION_ANSWER = QUESTION_ANSWER;
        this.QUESTION_BANK_ID = QUESTION_BANK_ID;
    }

    public Question() {
    }

    public String getQUESTION_ID() {
        return QUESTION_ID;
    }

    public void setQUESTION_ID(String QUESTION_ID) {
        this.QUESTION_ID = QUESTION_ID;
    }

    public String getQUESTION_TITLE() {
        return QUESTION_TITLE;
    }

    public void setQUESTION_TITLE(String QUESTION_TITLE) {
        this.QUESTION_TITLE = QUESTION_TITLE;
    }

    public String getQUESTION_A() {
        return QUESTION_A;
    }

    public void setQUESTION_A(String QUESTION_A) {
        this.QUESTION_A = QUESTION_A;
    }

    public String getQUESTION_B() {
        return QUESTION_B;
    }

    public void setQUESTION_B(String QUESTION_B) {
        this.QUESTION_B = QUESTION_B;
    }

    public String getQUESTION_C() {
        return QUESTION_C;
    }

    public void setQUESTION_C(String QUESTION_C) {
        this.QUESTION_C = QUESTION_C;
    }

    public String getQUESTION_D() {
        return QUESTION_D;
    }

    public void setQUESTION_D(String QUESTION_D) {
        this.QUESTION_D = QUESTION_D;
    }

    public String getQUESTION_ANSWER() {
        return QUESTION_ANSWER;
    }

    public void setQUESTION_ANSWER(String QUESTION_ANSWER) {
        this.QUESTION_ANSWER = QUESTION_ANSWER;
    }

    public String getQUESTION_BANK_ID() {
        return QUESTION_BANK_ID;
    }

    public void setQUESTION_BANK_ID(String QUESTION_BANK_ID) {
        this.QUESTION_BANK_ID = QUESTION_BANK_ID;
    }
}
