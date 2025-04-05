package DomainService;

import DomainModel.TransportationOrder;

public class FraudDetectionService {
    // Простейшая реализация обнаружения мошенничества.
    // Например, если в заявке слишком много сообщений за короткий промежуток времени.
    public boolean isFraudulent(TransportationOrder order) {
        int messageCount = order.getMessages().size();
        // Если в заявке более 5 сообщений, считаем активность подозрительной (примерная логика)
        if (messageCount > 5) {
            System.out.println("Обнаружена подозрительная активность в заявке " + order.getId());
            return true;
        }
        return false;
    }
}
