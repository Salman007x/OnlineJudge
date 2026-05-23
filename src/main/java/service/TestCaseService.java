package service;

import model.TestCase;
import repository.TestCaseRepository;

import java.util.ArrayList;

public class TestCaseService {

    private TestCaseRepository repository;

    public TestCaseService() {

        repository =
                new TestCaseRepository();
    }

    public void addTestCase(
            TestCase testCase
    ) {

        if (
                testCase.getProblemId() <= 0
        ) {

            System.out.println(
                    "Invalid Problem ID"
            );

            return;
        }

        repository.addTestCase(
                testCase
        );
    }

    public ArrayList<TestCase>
    getTestCasesByProblemId(
            int problemId
    ) {

        return repository
                .getTestCasesByProblemId(
                        problemId
                );
    }
}