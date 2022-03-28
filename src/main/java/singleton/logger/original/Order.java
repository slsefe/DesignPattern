package singleton.logger.original;

public class Order {
    private final long id;
    private final String orderNo;

    public Order(long id, String orderNo) {
        this.id = id;
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
