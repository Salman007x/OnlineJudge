package executor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessDemo {

    public static void runDemo() {

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            "echo Hello Judge"
                    );

            Process process =
                    processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(

                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                System.out.println(
                        "OUTPUT: " + line
                );
            }

            process.waitFor();

            System.out.println(
                    "Process Finished"
            );

        } catch (Exception e) {

            System.out.println(
                    "Execution Failed"
            );

            e.printStackTrace();
        }
    }
}