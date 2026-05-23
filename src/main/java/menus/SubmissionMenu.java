package menus;

import model.Submission;
import service.SubmissionService;

import java.util.ArrayList;
import java.util.Scanner;

public class SubmissionMenu {

    public static void show(
            Scanner scan,
            SubmissionService submissionService
    ) {

        System.out.println(
                "\n===== SUBMISSION MANAGEMENT ====="
        );

        System.out.println("1. View Submissions");
        System.out.println("2. Add Submission");
        System.out.println("3. Search Submission By ID");
        System.out.println("4. Update Verdict");
        System.out.println("5. Delete Submission");

        System.out.print("Enter Choice: ");

        int choice = scan.nextInt();

        scan.nextLine();

        switch (choice) {

            case 1:

                ArrayList<Submission> submissions =
                        submissionService.getAllSubmissions();

                if (submissions.isEmpty()) {

                    System.out.println(
                            "No Submissions Found"
                    );

                } else {

                    for (
                            Submission submission
                            : submissions
                    ) {

                        submission.display();
                    }
                }

                break;

            case 2:

                System.out.print(
                        "Enter User ID: "
                );

                int userId =
                        scan.nextInt();

                System.out.print(
                        "Enter Problem ID: "
                );

                int problemId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter Language: "
                );

                String language =
                        scan.nextLine();

                System.out.print(
                        "Enter Code: "
                );

                String code =
                        scan.nextLine();

                System.out.print(
                        "Enter Verdict: "
                );

                String verdict =
                        scan.nextLine();

                Submission submission =
                        new Submission(
                                0,
                                userId,
                                problemId,
                                language,
                                code,
                                verdict
                        );

                submissionService.addSubmission(
                        submission
                );

                break;

            case 3:

                System.out.print(
                        "Enter Submission ID: "
                );

                int searchId =
                        scan.nextInt();

                Submission foundSubmission =
                        submissionService.getSubmissionById(
                                searchId
                        );

                if (foundSubmission != null) {

                    foundSubmission.display();

                } else {

                    System.out.println(
                            "Submission Not Found"
                    );
                }

                break;

            case 4:

                System.out.print(
                        "Enter Submission ID: "
                );

                int updateId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter New Verdict: "
                );

                String newVerdict =
                        scan.nextLine();

                submissionService.updateVerdict(
                        updateId,
                        newVerdict
                );

                break;

            case 5:

                System.out.print(
                        "Enter Submission ID: "
                );

                int deleteId =
                        scan.nextInt();

                submissionService.deleteSubmission(
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