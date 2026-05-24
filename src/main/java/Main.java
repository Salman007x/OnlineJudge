import menus.*;
import executor.*;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        RunDemo.runDemo();

        ProblemService problemService =
                new ProblemService();

        UserService userService =
                new UserService();

        SubmissionService submissionService =
                new SubmissionService();

        ContestService contestService =
                new ContestService();

        ContestProblemService contestProblemService =
                new ContestProblemService();

        ContestUserService contestUserService =
                new ContestUserService();

        LeaderboardService leaderboardService =
                new LeaderboardService();

        TestCaseService testCaseService =
                new TestCaseService();

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println(
                    "\n===== ONLINE JUDGE ====="
            );

            System.out.println(
                    "1. Problem Management"
            );

            System.out.println(
                    "2. User Management"
            );

            System.out.println(
                    "3. Submission Management"
            );

            System.out.println(
                    "4. Contest Management"
            );

            System.out.println(
                    "5. Test Case Menu"
            );

            System.out.println(
                    "6. Exit"
            );

            System.out.print(
                    "Enter Choice: "
            );

            int choice = scan.nextInt();

            scan.nextLine();

            switch (choice) {

                case 1:

                    ProblemMenu.show(
                            scan,
                            problemService
                    );

                    break;

                case 2:

                    UserMenu.show(
                            scan,
                            userService
                    );

                    break;

                case 3:

                    SubmissionMenu.show(
                            scan,
                            submissionService
                    );

                    break;

                case 4:

                    ContestMenu.show(
                            scan,
                            contestService,
                            contestProblemService,
                            contestUserService,
                            leaderboardService
                    );

                    break;

                case 5:

                    TestCaseMenu.show(
                            scan,
                            testCaseService
                    );

                    break;

                case 6:

                    System.out.println("Exiting Application");
                    return;

                default:

                    System.out.println(
                            "Invalid Choice"
                    );
            }
        }
    }
}