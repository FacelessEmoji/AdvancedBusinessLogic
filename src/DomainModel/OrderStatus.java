package DomainModel;

public class OrderStatus {
    public enum Status {
        ACTIVE, CLOSED
    }

    private final Status status;

    public OrderStatus(Status status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status=" + status +
                '}';
    }
}
