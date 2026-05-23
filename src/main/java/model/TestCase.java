package model;

public class TestCase {

    private int id;

    private int problemId;

    private String inputData;

    private String expectedOutput;

    public TestCase(
            int id,
            int problemId,
            String inputData,
            String expectedOutput
    ) {

        this.id = id;

        this.problemId = problemId;

        this.inputData = inputData;

        this.expectedOutput = expectedOutput;
    }

    public int getId() {

        return id;
    }

    public int getProblemId() {

        return problemId;
    }

    public String getInputData() {

        return inputData;
    }

    public String getExpectedOutput() {

        return expectedOutput;
    }

    public void display() {

        System.out.println(
                "TestCase ID: " + id
        );

        System.out.println(
                "Problem ID: " + problemId
        );

        System.out.println(
                "Input: " + inputData
        );

        System.out.println(
                "Expected Output: "
                        + expectedOutput
        );

        System.out.println(
                "----------------------"
        );
    }
}