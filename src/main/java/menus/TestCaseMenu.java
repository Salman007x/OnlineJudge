package menus;

import model.TestCase;
import service.TestCaseService;

import java.util.ArrayList;
import java.util.Scanner;

public class TestCaseMenu {

    public static void show(
            Scanner scan,
            TestCaseService testCaseService
    ) {

        System.out.println(
                "\n===== TEST CASE MENU ====="
        );

        System.out.println(
                "1. Add Test Case"
        );

        System.out.println(
                "2. View Test Cases By Problem ID"
        );

        System.out.print(
                "Enter Choice: "
        );

        int choice =
                scan.nextInt();

        scan.nextLine();

        switch (choice) {

            case 1:

                System.out.print(
                        "Enter Problem ID: "
                );

                int problemId =
                        scan.nextInt();

                scan.nextLine();

                System.out.print(
                        "Enter Input Data: "
                );

                String inputData =
                        scan.nextLine();

                System.out.print(
                        "Enter Expected Output: "
                );

                String expectedOutput =
                        scan.nextLine();

                TestCase testCase =
                        new TestCase(
                                0,
                                problemId,
                                inputData,
                                expectedOutput
                        );

                testCaseService.addTestCase(
                        testCase
                );

                break;

            case 2:

                System.out.print(
                        "Enter Problem ID: "
                );

                int searchProblemId =
                        scan.nextInt();

                ArrayList<TestCase>
                        testCases =
                        testCaseService
                                .getTestCasesByProblemId(
                                        searchProblemId
                                );

                if (testCases.isEmpty()) {

                    System.out.println(
                            "No Test Cases Found"
                    );

                } else {

                    for (
                            TestCase caseTest
                            : testCases
                    ) {

                        caseTest.display();
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