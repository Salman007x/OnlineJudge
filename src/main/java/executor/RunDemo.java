package executor;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class RunDemo {

    public static void runDemo() {

        try {

            String code =
                    """
                    public class Main {

                        public static void main(
                                String[] args
                        ) {

                            System.out.println(
                                    "Hello Salmankhan, I am From Executed Code"
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