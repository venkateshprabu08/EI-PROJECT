// File: LoggerSingletonDemo.java
import java.time.LocalDateTime;

// Simple thread-safe singleton logger
class Logger {
    private static volatile Logger instance;
    private Logger() {
        // private ctor
    }

    public static Logger getInstance() {
        if (instance == null) { // first check (no locking)
            synchronized (Logger.class) {
                if (instance == null) { // second check (with locking)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void info(String msg) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + msg);
    }

    public void error(String msg) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + msg);
    }
}

public class LoggerSingletonDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.info("Application started.");

        // Simulate another part of app retrieving logger
        Logger.getInstance().info("Another module logging an info message.");

        logger.error("An error occurred (simulated).");
    }
}
