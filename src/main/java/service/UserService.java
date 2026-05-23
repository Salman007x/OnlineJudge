package service;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;

public class UserService {

    private UserRepository repository;

    public UserService() {

        repository = new UserRepository();
    }

    public ArrayList<User> getAllUsers() {

        return repository.getAllUsers();
    }

    public void addUser(
            String name,
            String email,
            String password,
            String role
    ) {

        if (
                !role.equalsIgnoreCase("ADMIN")
                        && !role.equalsIgnoreCase("STUDENT")
        ) {

            System.out.println("Invalid Role!");

            return;
        }

        if (email.isEmpty()) {

            System.out.println("Email Cannot Be Empty!");

            return;
        }

        repository.addUser(
                name,
                email,
                password,
                role
        );
    }

    public User getUserById(int userId) {

        return repository.getUserById(userId);
    }

    public void updateUserRole(
            int userId,
            String newRole
    ) {

        if (
                !newRole.equalsIgnoreCase("ADMIN")
                        && !newRole.equalsIgnoreCase("STUDENT")
        ) {

            System.out.println("Invalid Role!");

            return;
        }

        repository.updateUserRole(
                userId,
                newRole
        );
    }

    public void deleteUser(int userId) {

        repository.deleteUser(userId);
    }
}