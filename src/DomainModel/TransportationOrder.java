package DomainModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransportationOrder {
    private final String id;
    private final String client;
    private OrderStatus status;
    private DeliveryRoute deliveryRoute;
    private final List<Message> messages;
    private LocalDateTime lastActivityTime;

    public TransportationOrder(String client, DeliveryRoute deliveryRoute) {
        this.id = UUID.randomUUID().toString();
        this.client = client;
        this.deliveryRoute = deliveryRoute;
        this.status = new OrderStatus(OrderStatus.Status.ACTIVE);
        this.messages = new ArrayList<>();
        this.lastActivityTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryRoute getDeliveryRoute() {
        return deliveryRoute;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    // Добавление сообщения возможно только если заявка активна
    public void addMessage(Message message) {
        if (!status.isActive()) {
            throw new IllegalStateException("Нельзя добавить сообщение к неактивной заявке.");
        }
        messages.add(message);
        updateLastActivity();
    }

    // Метод для закрытия заявки (например, по таймауту)
    public void closeOrder() {
        this.status = new OrderStatus(OrderStatus.Status.CLOSED);
        updateLastActivity();
    }

    public void updateLastActivity() {
        this.lastActivityTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TransportationOrder{" +
                "id='" + id + '\'' +
                ", client='" + client + '\'' +
                ", status=" + status +
                ", deliveryRoute=" + deliveryRoute +
                ", messages=" + messages +
                ", lastActivityTime=" + lastActivityTime +
                '}';
    }
}
