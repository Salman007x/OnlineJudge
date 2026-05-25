package executor;

import model.TestCase;
import utils.Verdict;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class JavaCodeExecutor {

    public static String execute(

            String code,

            List<TestCase> testCases
    ) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "Main.java"
                    );

            writer.write(code);

            writer.close();

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

                return Verdict.COMPILATION_ERROR;
            }

            for (TestCase testCase : testCases) {

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
                        testCase.getInputData()
                                + "\n"
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

                int runExitCode =
                        runProcess.exitValue();

                reader.close();

                if (runExitCode != 0) {

                    return Verdict.RUNTIME_ERROR;
                }

                if (actualOutput == null) {

                    actualOutput = "";
                }

                if (
                        !actualOutput.trim().equals(
                                testCase
                                        .getExpectedOutput()
                                        .trim()
                        )
                ) {

                    return Verdict.WRONG_ANSWER;
                }
            }

            return Verdict.ACCEPTED;

        } catch (Exception e) {

            e.printStackTrace();

            return Verdict.RUNTIME_ERROR;
        }
    }
}