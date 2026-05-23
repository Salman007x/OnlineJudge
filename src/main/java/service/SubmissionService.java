package service;

import judge.SimpleJudge;
import model.Submission;
import repository.SubmissionRepository;

import java.util.ArrayList;

public class SubmissionService {

    private SubmissionRepository repository;

    public SubmissionService() {

        repository = new SubmissionRepository();
    }

    public ArrayList<Submission> getAllSubmissions() {

        return repository.getAllSubmissions();
    }

    public void addSubmission(
            Submission submission
    ) {

        if (
                submission.getLanguage().isEmpty()
        ) {

            System.out.println(
                    "Language Cannot Be Empty!"
            );

            return;
        }

        if (
                submission.getCode().isEmpty()
        ) {

            System.out.println(
                    "Code Cannot Be Empty!"
            );

            return;
        }
        String verdict = SimpleJudge.judge(
                submission.getCode()
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
        repository.addSubmission(finalSubmission);
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