package singleton.logger.singleton;

import java.io.IOException;

public class OrderController {
    public void createOrder(Order order) throws IOException {
        // ...
        Logger.getInstance().log("created order: " + order.toString());
    }
}
