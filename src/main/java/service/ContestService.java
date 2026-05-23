package service;

import model.Contest;
import repository.ContestRepository;

import java.util.ArrayList;

public class ContestService {

    private ContestRepository repository;

    public ContestService() {

        repository = new ContestRepository();
    }

    public ArrayList<Contest> getAllContests() {

        return repository.getAllContests();
    }

    public void addContest(
            Contest contest
    ) {

        if (
                contest.getTitle().isEmpty()
        ) {

            System.out.println(
                    "Contest Title Cannot Be Empty!"
            );

            return;
        }

        if (
                contest.getStartTime().isEmpty()
        ) {

            System.out.println(
                    "Start Time Cannot Be Empty!"
            );

            return;
        }

        if (
                contest.getEndTime().isEmpty()
        ) {

            System.out.println(
                    "End Time Cannot Be Empty!"
            );

            return;
        }

        repository.addContest(contest);
    }

    public Contest getContestById(
            int contestId
    ) {

        return repository.getContestById(
                contestId
        );
    }

    public void updateContestTitle(
            int contestId,
            String newTitle
    ) {

        if (newTitle.isEmpty()) {

            System.out.println(
                    "Contest Title Cannot Be Empty!"
            );

            return;
        }

        repository.updateContestTitle(
                contestId,
                newTitle
        );
    }

    public void deleteContest(
            int contestId
    ) {

        repository.deleteContest(
                contestId
        );
    }
}