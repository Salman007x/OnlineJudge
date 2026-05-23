package model;

public class Problem {

    private int id;
    private String title;
    private String difficulty;
    private String tags;

    public Problem(int id, String title, String difficulty, String tags) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTags() {
        return tags;
    }

    public String getSummary() {
        return title + " [" + difficulty + "]";
    }

    public void display() {
        System.out.println(
                getId() + " | " +
                        getTitle() + " | " +
                        getDifficulty() + " | " +
                        getTags()
        );
    }
}