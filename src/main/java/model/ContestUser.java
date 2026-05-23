package model;

public class ContestUser {

    private int contestId;

    private int userId;

    public ContestUser(
            int contestId,
            int userId
    ) {

        this.contestId = contestId;
        this.userId = userId;
    }

    public int getContestId() {

        return contestId;
    }

    public int getUserId() {

        return userId;
    }

    public void display() {

        System.out.println(
                "Contest ID: "
                        + contestId
                        + " | User ID: "
                        + userId
        );
    }
}