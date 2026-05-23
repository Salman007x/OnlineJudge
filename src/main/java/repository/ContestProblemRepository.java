package repository;

import model.ContestProblem;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContestProblemRepository {

    public void addProblemToContest(
            int contestId,
            int problemId
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO contest_problems(contest_id, problem_id) VALUES (?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            statement.setInt(2, problemId);

            statement.executeUpdate();

            System.out.println(
                    "Problem Added To Contest Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed To Add Problem To Contest"
            );

            e.printStackTrace();
        }
    }

    public ArrayList<ContestProblem>
    getProblemsByContestId(
            int contestId
    ) {

        ArrayList<ContestProblem> list =
                new ArrayList<>();

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM contest_problems WHERE contest_id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int problemId =
                        resultSet.getInt("problem_id");

                ContestProblem contestProblem =
                        new ContestProblem(
                                contestId,
                                problemId
                        );

                list.add(contestProblem);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Contest Problems"
            );

            e.printStackTrace();
        }

        return list;
    }
}