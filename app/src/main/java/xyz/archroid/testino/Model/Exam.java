package xyz.archroid.testino.Model;

public class Exam {

    private String EXAM_NAME;
    private String EXAM_DESC;
    private String EXAM_ID;
    private String EXAM_STARTTIME;
    private String EXAM_QUESTION_BANK_ID;
    private String EXAM_CREATOR;
    private String EXAM_DURATION;
    private String EXAM_ICON_URL;

    public Exam(String EXAM_NAME, String EXAM_DESC, String EXAM_ID, String EXAM_STARTTIME, String EXAM_QUESTION_BANK_ID, String EXAM_CREATOR, String EXAM_DURATION, String EXAM_CREATION_TIMESTAMP) {
        this.EXAM_NAME = EXAM_NAME;
        this.EXAM_DESC = EXAM_DESC;
        this.EXAM_ID = EXAM_ID;
        this.EXAM_STARTTIME = EXAM_STARTTIME;
        this.EXAM_QUESTION_BANK_ID = EXAM_QUESTION_BANK_ID;
        this.EXAM_CREATOR = EXAM_CREATOR;
        this.EXAM_DURATION = EXAM_DURATION;
        this.EXAM_ICON_URL = EXAM_CREATION_TIMESTAMP;
    }

    public Exam() {
    }

    public String getEXAM_NAME() {
        return EXAM_NAME;
    }

    public void setEXAM_NAME(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
    }

    public String getEXAM_DESC() {
        return EXAM_DESC;
    }

    public void setEXAM_DESC(String EXAM_DESC) {
        this.EXAM_DESC = EXAM_DESC;
    }

    public String getEXAM_ID() {
        return EXAM_ID;
    }

    public void setEXAM_ID(String EXAM_ID) {
        this.EXAM_ID = EXAM_ID;
    }

    public String getEXAM_STARTTIME() {
        return EXAM_STARTTIME;
    }

    public void setEXAM_STARTTIME(String EXAM_STARTTIME) {
        this.EXAM_STARTTIME = EXAM_STARTTIME;
    }

    public String getEXAM_QUESTION_BANK_ID() {
        return EXAM_QUESTION_BANK_ID;
    }

    public void setEXAM_QUESTION_BANK_ID(String EXAM_QUESTION_BANK_ID) {
        this.EXAM_QUESTION_BANK_ID = EXAM_QUESTION_BANK_ID;
    }

    public String getEXAM_CREATOR() {
        return EXAM_CREATOR;
    }

    public void setEXAM_CREATOR(String EXAM_CREATOR) {
        this.EXAM_CREATOR = EXAM_CREATOR;
    }

    public String getEXAM_DURATION() {
        return EXAM_DURATION;
    }

    public void setEXAM_DURATION(String EXAM_DURATION) {
        this.EXAM_DURATION = EXAM_DURATION;
    }

    public String getEXAM_ICON_URL() {
        return EXAM_ICON_URL;
    }

    public void setEXAM_ICON_URL(String EXAM_ICON_URL) {
        this.EXAM_ICON_URL = EXAM_ICON_URL;
    }
}
