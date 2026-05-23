package model;

public class Submission {

    private int id;

    private int userId;

    private int problemId;

    private String language;

    private String code;

    private String verdict;

    public Submission(
            int id,
            int userId,
            int problemId,
            String language,
            String code,
            String verdict
    ) {

        this.id = id;
        this.userId = userId;
        this.problemId = problemId;
        this.language = language;
        this.code = code;
        this.verdict = verdict;
    }

    public int getId() {

        return id;
    }

    public int getUserId() {

        return userId;
    }

    public int getProblemId() {

        return problemId;
    }

    public String getLanguage() {

        return language;
    }

    public String getCode() {

        return code;
    }

    public String getVerdict() {

        return verdict;
    }

    public void setVerdict(String verdict) {

        this.verdict = verdict;
    }

    public void display() {

        System.out.println(
                id + " | "
                        + userId + " | "
                        + problemId + " | "
                        + language + " | "
                        + verdict
        );
    }
}