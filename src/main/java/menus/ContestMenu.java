package menus;

import model.Contest;
import service.ContestService;

import java.util.ArrayList;
import java.util.Scanner;

public class ContestMenu {

    public static void show(
            Scanner scan,
            ContestService contestService
    ) {

        System.out.println(
                "\n===== CONTEST MANAGEMENT ====="
        );

        System.out.println("1. View Contests");
        System.out.println("2. Add Contest");
        System.out.println("3. Search Contest By ID");
        System.out.println("4. Update Contest Title");
        System.out.println("5. Delete Contest");

        System.out.print("Enter Choice: ");

        int choice = scan.nextInt();

        scan.nextLine();

        switch (choice) {

            case 1:

                ArrayList<Contest> contests =
                        contestService.getAllContests();

                if (contests.isEmpty()) {

                    System.out.println(
                            "No Contests Found"
                    );

                } else {

                    for (Contest contest : contests) {

                        contest.display();
                    }
                }

                break;

            case 2:

                System.out.print(
                        "Enter Contest Title: "
                );

                String title =
                        scan.nextLine();

                System.out.print(
                        "Enter Start Time: "
                );

                String startTime =
                        scan.nextLine();

                System.out.print(
                        "Enter End Time: "
                );

                String endTime =
                        scan.nextLine();

                Contest contest =
                        new Contest(
                                0,
                                title,
                                startTime,
                                endTime
                        );

                contestService.addContest(
                        contest
                );

                break;

            case 3:

                System.out.print(
                        "Enter Contest ID: "
                );

                int searchId =
                        scan.nextInt();

                Contest foundContest =
                        contestService.getContestById(
                                searchId
                        );

                if (foundContest != null) {

                    foundContest.display();

                } else {

                    System.out.println(
                            "Contest Not Found"
                    );
                }

                break;

            case 4:

                System.out.print(
                        "Enter Contest ID: "
                );

                int updateId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter New Contest Title: "
                );

                String newTitle =
                        scan.nextLine();

                contestService.updateContestTitle(
                        updateId,
                        newTitle
                );

                break;

            case 5:

                System.out.print(
                        "Enter Contest ID: "
                );

                int deleteId =
                        scan.nextInt();

                contestService.deleteContest(
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