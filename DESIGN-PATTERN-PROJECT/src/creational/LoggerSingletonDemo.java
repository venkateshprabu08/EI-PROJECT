
import java.time.LocalDateTime;


class Logger {
    private static volatile Logger instance;
    private Logger() {
    
    }

    public static Logger getInstance() {
        if (instance == null) { 
            synchronized (Logger.class) {
                if (instance == null) { 
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

     
        Logger.getInstance().info("Another module logging an info message.");

        logger.error("An error occurred (simulated).");
    }
}
