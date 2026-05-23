package repository;

import model.Submission;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubmissionRepository {

    public ArrayList<Submission> getAllSubmissions() {

        ArrayList<Submission> list =
                new ArrayList<>();

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM submissions";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                int userId =
                        resultSet.getInt("user_id");

                int problemId =
                        resultSet.getInt("problem_id");

                String language =
                        resultSet.getString("language");

                String code =
                        resultSet.getString("code");

                String verdict =
                        resultSet.getString("verdict");

                Submission submission =
                        new Submission(
                                id,
                                userId,
                                problemId,
                                language,
                                code,
                                verdict
                        );

                list.add(submission);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Submissions"
            );

            e.printStackTrace();
        }

        return list;
    }

    public void addSubmission(
            Submission submission
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO submissions(user_id, problem_id, language, code, verdict) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(
                    1,
                    submission.getUserId()
            );

            statement.setInt(
                    2,
                    submission.getProblemId()
            );

            statement.setString(
                    3,
                    submission.getLanguage()
            );

            statement.setString(
                    4,
                    submission.getCode()
            );

            statement.setString(
                    5,
                    submission.getVerdict()
            );

            statement.executeUpdate();

            System.out.println(
                    "Submission Added Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed To Add Submission"
            );

            e.printStackTrace();
        }
    }

    public Submission getSubmissionById(
            int submissionId
    ) {

        Submission submission = null;

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM submissions WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, submissionId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                int userId =
                        resultSet.getInt("user_id");

                int problemId =
                        resultSet.getInt("problem_id");

                String language =
                        resultSet.getString("language");

                String code =
                        resultSet.getString("code");

                String verdict =
                        resultSet.getString("verdict");

                submission =
                        new Submission(
                                id,
                                userId,
                                problemId,
                                language,
                                code,
                                verdict
                        );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Submission"
            );

            e.printStackTrace();
        }

        return submission;
    }

    public void updateVerdict(
            int submissionId,
            String verdict
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "UPDATE submissions SET verdict = ? WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, verdict);

            statement.setInt(2, submissionId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println(
                        "Verdict Updated Successfully!"
                );

            } else {

                System.out.println(
                        "Submission Not Found"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Update Verdict"
            );

            e.printStackTrace();
        }
    }

    public void deleteSubmission(
            int submissionId
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "DELETE FROM submissions WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, submissionId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println(
                        "Submission Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "Submission Not Found"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Delete Submission"
            );

            e.printStackTrace();
        }
    }
}