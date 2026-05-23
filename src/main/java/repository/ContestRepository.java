package repository;

import model.Contest;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContestRepository {

    public ArrayList<Contest> getAllContests() {

        ArrayList<Contest> list =
                new ArrayList<>();

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM contests";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String title =
                        resultSet.getString("title");

                String startTime =
                        resultSet.getString("start_time");

                String endTime =
                        resultSet.getString("end_time");

                Contest contest =
                        new Contest(
                                id,
                                title,
                                startTime,
                                endTime
                        );

                list.add(contest);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Contests"
            );

            e.printStackTrace();
        }

        return list;
    }

    public void addContest(
            Contest contest
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO contests(title, start_time, end_time) VALUES (?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(
                    1,
                    contest.getTitle()
            );

            statement.setTimestamp(
                    2,
                    java.sql.Timestamp.valueOf(
                            contest.getStartTime()
                    )
            );

            statement.setTimestamp(
                    3,
                    java.sql.Timestamp.valueOf(
                            contest.getEndTime()
                    )
            );

            statement.executeUpdate();

            System.out.println(
                    "Contest Added Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed To Add Contest"
            );

            e.printStackTrace();
        }
    }

    public Contest getContestById(
            int contestId
    ) {

        Contest contest = null;

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM contests WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String title =
                        resultSet.getString("title");

                String startTime =
                        resultSet.getString("start_time");

                String endTime =
                        resultSet.getString("end_time");

                contest =
                        new Contest(
                                id,
                                title,
                                startTime,
                                endTime
                        );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Contest"
            );

            e.printStackTrace();
        }

        return contest;
    }

    public void updateContestTitle(
            int contestId,
            String newTitle
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "UPDATE contests SET title = ? WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, newTitle);

            statement.setInt(2, contestId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println(
                        "Contest Updated Successfully!"
                );

            } else {

                System.out.println(
                        "Contest Not Found"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Update Contest"
            );

            e.printStackTrace();
        }
    }

    public void deleteContest(
            int contestId
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "DELETE FROM contests WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, contestId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println(
                        "Contest Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "Contest Not Found"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Delete Contest"
            );

            e.printStackTrace();
        }
    }
}