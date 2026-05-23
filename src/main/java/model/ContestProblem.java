package model;

public class ContestProblem {

    private int contestId;

    private int problemId;

    public ContestProblem(
            int contestId,
            int problemId
    ) {

        this.contestId = contestId;
        this.problemId = problemId;
    }

    public int getContestId() {

        return contestId;
    }

    public int getProblemId() {

        return problemId;
    }

    public void display() {

        System.out.println(
                "Contest ID: "
                        + contestId
                        + " | Problem ID: "
                        + problemId
        );
    }
}