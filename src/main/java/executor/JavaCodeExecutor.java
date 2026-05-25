package executor;

import model.TestCase;
import utils.Verdict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaCodeExecutor {

    public static String execute(

            String code,

            List<TestCase> testCases
    ) {

        String folderName =
                "temp/submission_"
                        + System.currentTimeMillis();

        File directory =
                new File(
                        folderName
                );

        File javaFile =
                new File(
                        folderName + "/Main.java"
                );

        File classFile =
                new File(
                        folderName + "/Main.class"
                );

        try {

            directory.mkdirs();

            FileWriter writer =
                    new FileWriter(
                            javaFile
                    );

            writer.write(code);

            writer.close();

            ProcessBuilder compileBuilder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            "javac "
                                    + folderName
                                    + "/Main.java"
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

                runBuilder.directory(
                        directory
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

                boolean completed =
                        runProcess.waitFor(
                                2,
                                TimeUnit.SECONDS
                        );

                if (!completed) {

                    runProcess.destroy();

                    return Verdict.TIME_LIMIT_EXCEEDED;
                }

                int runExitCode =
                        runProcess.exitValue();

                BufferedReader reader =
                        new BufferedReader(

                                new InputStreamReader(
                                        runProcess
                                                .getInputStream()
                                )
                        );

                StringBuilder outputBuilder =
                        new StringBuilder();

                String line;

                while (
                        (line = reader.readLine())
                                != null
                ) {

                    outputBuilder
                            .append(line)
                            .append("\n");
                }

                String actualOutput =
                        outputBuilder.toString();

                reader.close();

                if (runExitCode != 0) {

                    return Verdict.RUNTIME_ERROR;
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

        } finally {

            javaFile.delete();

            classFile.delete();

            directory.delete();
        }
    }
}