package DomainService;

import DomainModel.TransportationOrder;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrderLifecycleService {
    // Автоматическое закрытие заявки при отсутствии активности более указанного времени
    private final Duration inactivityThreshold;

    public OrderLifecycleService(Duration inactivityThreshold) {
        this.inactivityThreshold = inactivityThreshold;
    }

    public void checkAndCloseOrder(TransportationOrder order) {
        LocalDateTime now = LocalDateTime.now();
        Duration inactivity = Duration.between(order.getLastActivityTime(), now);
        if (inactivity.compareTo(inactivityThreshold) > 0 && order.getStatus().isActive()) {
            order.closeOrder();
            System.out.println("Заявка " + order.getId() + " автоматически закрыта из-за отсутствия активности.");
        }
    }
}
