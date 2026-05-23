package service;

import repository.LeaderboardRepository;

public class LeaderboardService {

    private LeaderboardRepository repository;

    public LeaderboardService() {

        repository =
                new LeaderboardRepository();
    }

    public void showLeaderboard(
            int contestId
    ) {

        if (contestId <= 0) {

            System.out.println(
                    "Invalid Contest ID"
            );

            return;
        }

        repository.showLeaderboard(
                contestId
        );
    }
}