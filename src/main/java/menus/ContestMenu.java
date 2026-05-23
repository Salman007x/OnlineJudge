package menus;

import model.Contest;
import model.ContestProblem;
import model.ContestUser;

import service.ContestProblemService;
import service.ContestService;
import service.ContestUserService;

import java.util.ArrayList;
import java.util.Scanner;

public class ContestMenu {

    public static void show(
            Scanner scan,
            ContestService contestService,
            ContestProblemService contestProblemService,
            ContestUserService contestUserService
    ) {

        System.out.println(
                "\n===== CONTEST MANAGEMENT ====="
        );

        System.out.println("1. View Contests");
        System.out.println("2. Add Contest");
        System.out.println("3. Search Contest By ID");
        System.out.println("4. Update Contest Title");
        System.out.println("5. Delete Contest");
        System.out.println("6. Add Problem To Contest");
        System.out.println("7. Join Contest");
        System.out.println("8. View Contest Problems");
        System.out.println("9. View Contest Participants");

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

            case 6:

                System.out.print(
                        "Enter Contest ID: "
                );

                int contestId =
                        scan.nextInt();

                System.out.print(
                        "Enter Problem ID: "
                );

                int problemId =
                        scan.nextInt();

                contestProblemService
                        .addProblemToContest(
                                contestId,
                                problemId
                        );

                break;

            case 7:

                System.out.print(
                        "Enter Contest ID: "
                );

                int joinContestId =
                        scan.nextInt();

                System.out.print(
                        "Enter User ID: "
                );

                int userId =
                        scan.nextInt();

                contestUserService.joinContest(
                        joinContestId,
                        userId
                );

                break;

            case 8:

                System.out.print(
                        "Enter Contest ID: "
                );

                int problemContestId =
                        scan.nextInt();

                ArrayList<ContestProblem>
                        contestProblems =
                        contestProblemService
                                .getProblemsByContestId(
                                        problemContestId
                                );

                if (contestProblems.isEmpty()) {

                    System.out.println(
                            "No Problems Found"
                    );

                } else {

                    for (
                            ContestProblem contestProblem
                            : contestProblems
                    ) {

                        contestProblem.display();
                    }
                }

                break;

            case 9:

                System.out.print(
                        "Enter Contest ID: "
                );

                int participantContestId =
                        scan.nextInt();

                ArrayList<ContestUser>
                        contestUsers =
                        contestUserService
                                .getUsersByContestId(
                                        participantContestId
                                );

                if (contestUsers.isEmpty()) {

                    System.out.println(
                            "No Participants Found"
                    );

                } else {

                    for (
                            ContestUser contestUser
                            : contestUsers
                    ) {

                        contestUser.display();
                    }
                }

                break;

            default:

                System.out.println(
                        "Invalid Choice"
                );
        }
    }
}