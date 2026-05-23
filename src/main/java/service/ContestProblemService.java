package service;

import model.ContestProblem;
import repository.ContestProblemRepository;

import java.util.ArrayList;

public class ContestProblemService {

    private ContestProblemRepository repository;

    public ContestProblemService() {

        repository =
                new ContestProblemRepository();
    }

    public void addProblemToContest(
            int contestId,
            int problemId
    ) {

        if (contestId <= 0) {

            System.out.println(
                    "Invalid Contest ID"
            );

            return;
        }

        if (problemId <= 0) {

            System.out.println(
                    "Invalid Problem ID"
            );

            return;
        }

        repository.addProblemToContest(
                contestId,
                problemId
        );
    }

    public ArrayList<ContestProblem>
    getProblemsByContestId(
            int contestId
    ) {

        return repository.getProblemsByContestId(
                contestId
        );
    }
}