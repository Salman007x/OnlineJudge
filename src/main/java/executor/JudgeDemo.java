package executor;

import utils.Verdict;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JudgeDemo {

    public static void runDemo() {

        try {

            String code =
                    """
                    import java.util.Scanner;

                    public class Main {

                        public static void main(
                                String[] args
                        ) {

                            Scanner sc =
                                    new Scanner(
                                            System.in
                                    );

                            int n =
                                    sc.nextInt();

                            System.out.println(
                                    n * n
                            );
                        }
                    }
                    """;

            String testcaseInput =
                    "5\n";

            String expectedOutput =
                    "35";

            FileWriter writer =
                    new FileWriter(
                            "Main.java"
                    );

            writer.write(code);

            writer.close();

            System.out.println(
                    "Java File Created"
            );

            ProcessBuilder compileBuilder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            "javac Main.java"
                    );

            Process compileProcess =
                    compileBuilder.start();

            int compileExitCode =
                    compileProcess.waitFor();

            if (compileExitCode != 0) {

                System.out.println(
                        "COMPILATION_ERROR"
                );

                return;
            }

            System.out.println(
                    "Compilation Successful"
            );

            ProcessBuilder runBuilder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            "java Main"
                    );

            Process runProcess =
                    runBuilder.start();

            OutputStreamWriter processInput =
                    new OutputStreamWriter(
                            runProcess
                                    .getOutputStream()
                    );

            processInput.write(
                    testcaseInput
            );

            processInput.flush();

            processInput.close();

            BufferedReader reader =
                    new BufferedReader(

                            new InputStreamReader(
                                    runProcess
                                            .getInputStream()
                            )
                    );

            String actualOutput =
                    reader.readLine();

            runProcess.waitFor();

            System.out.println(
                    "Expected Output: "
                            + expectedOutput
            );

            System.out.println(
                    "Actual Output: "
                            + actualOutput
            );

            String verdict;

            if (
                    actualOutput.trim().equals(
                            expectedOutput.trim()
                    )
            ) {

                verdict =
                        Verdict.ACCEPTED;

            } else {

                verdict =
                        Verdict.WRONG_ANSWER;
            }

            System.out.println(
                    "VERDICT: "
                            + verdict
            );

        } catch (Exception e) {

            System.out.println(
                    "Judge Execution Error"
            );

            e.printStackTrace();
        }
    }
}