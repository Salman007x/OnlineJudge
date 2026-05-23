package menus;

import model.Problem;
import service.ProblemService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProblemMenu {

    public static void show(
            Scanner scan,
            ProblemService service
    ) {

        System.out.println(
                "\n===== PROBLEM MANAGEMENT ====="
        );

        System.out.println("1. View Problems");
        System.out.println("2. Add Problem");
        System.out.println("3. Search Problem By ID");
        System.out.println("4. Update Problem Difficulty");
        System.out.println("5. Delete Problem");

        System.out.print("Enter Choice: ");

        int choice = scan.nextInt();

        scan.nextLine();

        switch (choice) {

            case 1:

                ArrayList<Problem> problems =
                        service.getAllProblems();

                if (problems.isEmpty()) {

                    System.out.println(
                            "No Problems Found"
                    );

                } else {

                    for (Problem problem : problems) {

                        problem.display();
                    }
                }

                break;

            case 2:

                System.out.print(
                        "Enter Problem Title: "
                );

                String title =
                        scan.nextLine();

                System.out.print(
                        "Enter Difficulty: "
                );

                String difficulty =
                        scan.nextLine();

                System.out.print(
                        "Enter Tags: "
                );

                String tags =
                        scan.nextLine();

                Problem problem =
                        new Problem(
                                0,
                                title,
                                difficulty,
                                tags
                        );

                service.addProblem(problem);

                break;

            case 3:

                System.out.print(
                        "Enter Problem ID: "
                );

                int searchId =
                        scan.nextInt();

                Problem foundProblem =
                        service.getProblemById(
                                searchId
                        );

                if (foundProblem != null) {

                    foundProblem.display();

                } else {

                    System.out.println(
                            "Problem Not Found"
                    );
                }

                break;

            case 4:

                System.out.print(
                        "Enter Problem ID: "
                );

                int updateId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter New Difficulty: "
                );

                String newDifficulty =
                        scan.nextLine();

                service.updateProblemDifficulty(
                        updateId,
                        newDifficulty
                );

                break;

            case 5:

                System.out.print(
                        "Enter Problem ID: "
                );

                int deleteId =
                        scan.nextInt();

                service.deleteProblem(
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