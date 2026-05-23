package repository;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaderboardRepository {

    public void showLeaderboard(
            int contestId
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    """
SELECT
    users.name,

    COUNT(
        DISTINCT submissions.problem_id
    ) AS solved_count

FROM contest_users

JOIN users
ON contest_users.user_id = users.id

LEFT JOIN submissions
ON submissions.user_id = users.id

WHERE
    contest_users.contest_id = ?
    AND submissions.verdict = 'accepted'

GROUP BY users.name

ORDER BY solved_count DESC
                    """;

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            ResultSet resultSet =
                    statement.executeQuery();

            System.out.println(
                    "\n===== LEADERBOARD ====="
            );

            int rank = 1;

            while (resultSet.next()) {

                String name =
                        resultSet.getString("name");

                int solvedCount =
                        resultSet.getInt(
                                "solved_count"
                        );

                System.out.println(
                        rank
                                + ". "
                                + name
                                + " | Solved: "
                                + solvedCount
                );

                rank++;
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Generate Leaderboard"
            );

            e.printStackTrace();
        }
    }
}