package repository;

import model.TestCase;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class TestCaseRepository {

    public void addTestCase(
            TestCase testCase
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    """
                    INSERT INTO test_cases(
                        problem_id,
                        input_data,
                        expected_output
                    )
                    VALUES (?, ?, ?)
                    """;

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(
                    1,
                    testCase.getProblemId()
            );

            statement.setString(
                    2,
                    testCase.getInputData()
            );

            statement.setString(
                    3,
                    testCase.getExpectedOutput()
            );

            statement.executeUpdate();

            System.out.println(
                    "Test Case Added Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed To Add Test Case"
            );

            e.printStackTrace();
        }
    }

    public ArrayList<TestCase>
    getTestCasesByProblemId(
            int problemId
    ) {

        ArrayList<TestCase> list =
                new ArrayList<>();

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    """
                    SELECT * FROM test_cases
                    WHERE problem_id = ?
                    """;

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(
                    1,
                    problemId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                TestCase testCase =
                        new TestCase(

                                resultSet.getInt("id"),

                                resultSet.getInt(
                                        "problem_id"
                                ),

                                resultSet.getString(
                                        "input_data"
                                ),

                                resultSet.getString(
                                        "expected_output"
                                )
                        );

                list.add(testCase);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed To Fetch Test Cases"
            );

            e.printStackTrace();
        }

        return list;
    }
}