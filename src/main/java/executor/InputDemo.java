package executor;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputDemo {

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
                        "Compilation Failed"
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

            processInput.write("9080\n");

            processInput.flush();

            processInput.close();

            BufferedReader reader =
                    new BufferedReader(

                            new InputStreamReader(
                                    runProcess
                                            .getInputStream()
                            )
                    );

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                System.out.println(
                        "PROGRAM OUTPUT: "
                                + line
                );
            }

            runProcess.waitFor();

            System.out.println(
                    "Execution Finished"
            );

        } catch (Exception e) {

            System.out.println(
                    "Execution Error"
            );

            e.printStackTrace();
        }
    }
}