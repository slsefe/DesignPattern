package singleton.logger.multicase;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();

    private Logger() {
    }

    public static Logger getInstance(String name) {
        instances.putIfAbsent(name, new Logger());
        return instances.get(name);
    }

    public void log() {
    }

    public static void main(String[] args) {
        Logger l1 = Logger.getInstance("User.class");
        Logger l2 = Logger.getInstance("User.class");
        Logger l3 = Logger.getInstance("Order.class");
        System.out.println(l1 == l2);
        System.out.println(l1 == l3);
    }
}
