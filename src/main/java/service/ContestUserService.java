package service;

import model.ContestUser;
import repository.ContestUserRepository;

import java.util.ArrayList;

public class ContestUserService {

    private ContestUserRepository repository;

    public ContestUserService() {

        repository =
                new ContestUserRepository();
    }

    public void joinContest(
            int contestId,
            int userId
    ) {

        if (contestId <= 0) {

            System.out.println(
                    "Invalid Contest ID"
            );

            return;
        }

        if (userId <= 0) {

            System.out.println(
                    "Invalid User ID"
            );

            return;
        }

        repository.joinContest(
                contestId,
                userId
        );
    }

    public ArrayList<ContestUser>
    getUsersByContestId(
            int contestId
    ) {

        return repository.getUsersByContestId(
                contestId
        );
    }
}