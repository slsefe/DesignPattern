package singleton.logger.singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter writer;
    private static final Logger instance = new Logger();

    private Logger() {
        File file = new File("/path/to/log.txt");
        try {
            writer = new FileWriter(file, true);
        } catch (IOException ignored) {
        }
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) throws IOException {
        writer.write(message);

    }
}

