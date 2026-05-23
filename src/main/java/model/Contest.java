package model;

public class Contest {

    private int id;

    private String title;

    private String startTime;

    private String endTime;

    public Contest(
            int id,
            String title,
            String startTime,
            String endTime
    ) {

        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getStartTime() {

        return startTime;
    }

    public String getEndTime() {

        return endTime;
    }

    public void display() {

        System.out.println(
                id + " | "
                        + title + " | "
                        + startTime + " | "
                        + endTime
        );
    }
}