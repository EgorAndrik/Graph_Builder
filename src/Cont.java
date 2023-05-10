import java.io.IOException;

public class Cont {
    public static void arithmetic_example_Function(String arithmetic_example)
            throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                "venv/Scripts/python.exe",
                "src/algor.py",
                arithmetic_example);
        builder.redirectErrorStream(true);

        Process process = builder.start();

        process.waitFor();
    }
}