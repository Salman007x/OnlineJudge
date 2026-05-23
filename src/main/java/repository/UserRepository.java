package repository;

import model.User;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserRepository {

    public ArrayList<User> getAllUsers() {

        ArrayList<User> list = new ArrayList<>();

        try {

            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM users";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                User user = new User(
                        id,
                        name,
                        email,
                        password,
                        role
                );

                list.add(user);
            }

        } catch (Exception e) {

            System.out.println("Failed To Fetch Users");

            e.printStackTrace();
        }

        return list;
    }

    public void addUser(
            String name,
            String email,
            String password,
            String role
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "INSERT INTO users(name, email, password, role) VALUES (?, ?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, role);

            statement.executeUpdate();

            System.out.println("User Inserted Successfully!");

        } catch (Exception e) {

            System.out.println("Failed To Insert User");

            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {

        User user = null;

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM users WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                user = new User(
                        id,
                        name,
                        email,
                        password,
                        role
                );
            }

        } catch (Exception e) {

            System.out.println("Failed To Fetch User");

            e.printStackTrace();
        }

        return user;
    }

    public void updateUserRole(
            int userId,
            String newRole
    ) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "UPDATE users SET role = ? WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, newRole);
            statement.setInt(2, userId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("User Role Updated!");

            } else {

                System.out.println("User Not Found");
            }

        } catch (Exception e) {

            System.out.println("Failed To Update User");

            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "DELETE FROM users WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(1, userId);

            int rowsAffected =
                    statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("User Deleted!");

            } else {

                System.out.println("User Not Found");
            }

        } catch (Exception e) {

            System.out.println("Failed To Delete User");

            e.printStackTrace();
        }
    }
}