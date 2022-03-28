package singleton.logger.original;

import java.io.IOException;

public class OrderController {
    private Logger logger;

    public OrderController() throws IOException {
        logger = new Logger();
    }

    public void createOrder(Order order) throws IOException {
        logger.log("create an order: " + order.toString());
    }
}
