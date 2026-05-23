package repository;

import model.Problem;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProblemRepository {

    public ArrayList<Problem> getAllProblems() {

        ArrayList<Problem> list = new ArrayList<>();

        String query = "SELECT * FROM problems";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");

                String title =
                        resultSet.getString("title");

                String difficulty =
                        resultSet.getString("difficulty");

                String tags =
                        resultSet.getString("tags");

                Problem problem =
                        new Problem(id, title, difficulty, tags);

                list.add(problem);
            }

        } catch (Exception e) {

            System.out.println("Failed To Fetch Problems");

            e.printStackTrace();
        }

        return list;
    }

    public void addProblem(Problem problem) {

        String query =
                "INSERT INTO problems(title, difficulty, tags) VALUES (?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, problem.getTitle());

            statement.setString(2, problem.getDifficulty());

            statement.setString(3, problem.getTags());

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Problem Inserted Into Database");
            }

        } catch (Exception e) {

            System.out.println("Failed To Add Problem");

            e.printStackTrace();
        }
    }

    public Problem getProblemById(int id) {

        String query =
                "SELECT * FROM problems WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                String title =
                        resultSet.getString("title");

                String difficulty =
                        resultSet.getString("difficulty");

                String tags =
                        resultSet.getString("tags");

                return new Problem(
                        id,
                        title,
                        difficulty,
                        tags
                );
            }

        } catch (Exception e) {

            System.out.println("Failed To Search Problem");

            e.printStackTrace();
        }

        return null;
    }

    public void updateProblemDifficulty(
            int id,
            String difficulty
    ) {

        String query =
                "UPDATE problems SET difficulty = ? WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, difficulty);

            statement.setInt(2, id);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Problem Updated Successfully");

            } else {

                System.out.println("Problem Not Found");
            }

        } catch (Exception e) {

            System.out.println("Failed To Update Problem");

            e.printStackTrace();
        }
    }

    public void deleteProblem(int id) {

        String query =
                "DELETE FROM problems WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setInt(1, id);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Problem Deleted Successfully");

            } else {

                System.out.println("Problem Not Found");
            }

        } catch (Exception e) {

            System.out.println("Failed To Delete Problem");

            e.printStackTrace();
        }
    }
}