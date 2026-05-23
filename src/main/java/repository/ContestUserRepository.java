package repository;

import model.ContestUser;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContestUserRepository {

    public void joinContest(
            int contestId,
            int userId
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO contest_users(contest_id, user_id) VALUES (?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            statement.setInt(2, userId);

            statement.executeUpdate();

            System.out.println(
                    "User Joined Contest Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed To Join Contest"
            );

            e.printStackTrace();
        }
    }

    public ArrayList<ContestUser>
    getUsersByContestId(
            int contestId
    ) {

        ArrayList<ContestUser> list =
                new ArrayList<>();

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM contest_users WHERE contest_id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int userId =
                        resultSet.getInt("user_id");

                ContestUser contestUser =
                        new ContestUser(
                                contestId,
                                userId
                        );

                list.add(contestUser);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Contest Users"
            );

            e.printStackTrace();
        }

        return list;
    }
}