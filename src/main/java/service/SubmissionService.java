package service;

import executor.JavaCodeExecutor;
import model.Submission;
import model.TestCase;
import repository.SubmissionRepository;
import repository.TestCaseRepository;

import java.util.ArrayList;
import java.util.List;

public class SubmissionService {

    private SubmissionRepository repository;

    private TestCaseRepository testCaseRepository;

    public SubmissionService() {

        repository =
                new SubmissionRepository();

        testCaseRepository =
                new TestCaseRepository();
    }

    public ArrayList<Submission> getAllSubmissions() {

        return repository.getAllSubmissions();
    }

    public void addSubmission(
            Submission submission
    ) {

        if (
                submission
                        .getLanguage()
                        .isEmpty()
        ) {

            System.out.println(
                    "Language Cannot Be Empty!"
            );

            return;
        }

        if (
                submission
                        .getCode()
                        .isEmpty()
        ) {

            System.out.println(
                    "Code Cannot Be Empty!"
            );

            return;
        }

        List<TestCase> testCases =
                testCaseRepository
                        .getTestCasesByProblemId(
                                submission
                                        .getProblemId()
                        );

        String verdict =
                JavaCodeExecutor.execute(

                        submission.getCode(),

                        testCases
                );

        Submission finalSubmission =
                new Submission(

                        submission.getId(),

                        submission.getUserId(),

                        submission.getProblemId(),

                        submission.getLanguage(),

                        submission.getCode(),

                        verdict
                );

        repository.addSubmission(
                finalSubmission
        );

        System.out.println(
                "Submission Added Successfully"
        );

        System.out.println(
                "Final Verdict: "
                        + verdict
        );
    }

    public Submission getSubmissionById(
            int submissionId
    ) {

        return repository.getSubmissionById(
                submissionId
        );
    }

    public void updateVerdict(
            int submissionId,

            String verdict
    ) {

        repository.updateVerdict(

                submissionId,

                verdict
        );
    }

    public void deleteSubmission(
            int submissionId
    ) {

        repository.deleteSubmission(
                submissionId
        );
    }
}