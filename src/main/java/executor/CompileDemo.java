package executor;

import java.io.FileWriter;

public class CompileDemo {

    public static void runDemo() {

        try {

            String code =
                    """
                    public class Main {

                        public static void main(
                                String[] args
                        ) {

                            System.ioout.println(
                                    "Compilation Success"
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

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            "javac Main.java"
                    );

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();

            if (exitCode == 0) {

                System.out.println(
                        "Compilation Successful"
                );

            } else {

                System.out.println(
                        "Compilation Failed"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Compilation Error"
            );

            e.printStackTrace();
        }
    }
}