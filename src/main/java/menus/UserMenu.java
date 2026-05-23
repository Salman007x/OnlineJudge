package menus;

import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {

    public static void show(
            Scanner scan,
            UserService userService
    ) {

        System.out.println(
                "\n===== USER MANAGEMENT ====="
        );

        System.out.println("1. View Users");
        System.out.println("2. Add User");
        System.out.println("3. Search User By ID");
        System.out.println("4. Update User Role");
        System.out.println("5. Delete User");

        System.out.print("Enter Choice: ");

        int choice = scan.nextInt();

        scan.nextLine();

        switch (choice) {

            case 1:

                ArrayList<User> users =
                        userService.getAllUsers();

                if (users.isEmpty()) {

                    System.out.println(
                            "No Users Found"
                    );

                } else {

                    for (User user : users) {

                        user.display();
                    }
                }

                break;

            case 2:

                System.out.print(
                        "Enter Name: "
                );

                String name =
                        scan.nextLine();

                System.out.print(
                        "Enter Email: "
                );

                String email =
                        scan.nextLine();

                System.out.print(
                        "Enter Password: "
                );

                String password =
                        scan.nextLine();

                System.out.print(
                        "Enter Role (ADMIN/STUDENT): "
                );

                String role =
                        scan.nextLine();

                userService.addUser(
                        name,
                        email,
                        password,
                        role
                );

                break;

            case 3:

                System.out.print(
                        "Enter User ID: "
                );

                int searchId =
                        scan.nextInt();

                User user =
                        userService.getUserById(
                                searchId
                        );

                if (user != null) {

                    user.display();

                } else {

                    System.out.println(
                            "User Not Found"
                    );
                }

                break;

            case 4:

                System.out.print(
                        "Enter User ID: "
                );

                int updateId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter New Role: "
                );

                String newRole =
                        scan.nextLine();

                userService.updateUserRole(
                        updateId,
                        newRole
                );

                break;

            case 5:

                System.out.print(
                        "Enter User ID: "
                );

                int deleteId =
                        scan.nextInt();

                userService.deleteUser(
                        deleteId
                );

                break;

            default:

                System.out.println(
                        "Invalid Choice"
                );
        }
    }
}