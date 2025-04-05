import DomainModel.*;
import DomainService.FraudDetectionService;
import DomainService.OrderLifecycleService;
import Repository.TransportationOrderRepository;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        // Создаем репозиторий для хранения заявок
        TransportationOrderRepository orderRepository = new TransportationOrderRepository();

        // Создаем заказ на перевозку с клиентом и маршрутом доставки
        DeliveryRoute route = new DeliveryRoute("Склад A", "Магазин B", "Основной маршрут через город");
        TransportationOrder order = new TransportationOrder("Компания X", route);
        orderRepository.save(order);

        // Добавляем сообщение от клиента
        Message message1 = new Message("Просьба доставить груз как можно быстрее.", "client");
        order.addMessage(message1);

        // Добавляем вложение к сообщению
        Attachment attachment1 = new Attachment("invoice.pdf", "application/pdf");
        message1.addAttachment(attachment1);

        // Попытка подтвердить сообщение отправителем (ожидается ошибка)
        try {
            message1.confirm("client");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка подтверждения сообщения: " + e.getMessage());
        }

        // Подтверждение сообщения водителем (допустимый сценарий)
        message1.confirm("driver");
        System.out.println("Сообщение подтверждено: " + message1);

        // Демонстрация доменного сервиса для управления жизненным циклом заявки
        // Используем короткий порог неактивности для демонстрации автоматического закрытия
        OrderLifecycleService lifecycleService = new OrderLifecycleService(Duration.ofSeconds(1));
        try {
            Thread.sleep(2000); // имитация задержки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lifecycleService.checkAndCloseOrder(order);
        System.out.println("Статус заявки после проверки: " + order.getStatus());

        // Для демонстрации FraudDetectionService создаем новую активную заявку
        TransportationOrder fraudOrder = new TransportationOrder("Компания X", route);
        orderRepository.save(fraudOrder);

        // Добавляем несколько сообщений для вызова подозрительной активности
        for (int i = 0; i < 6; i++) {
            fraudOrder.addMessage(new Message("Дополнительное сообщение " + i, "client"));
        }
        FraudDetectionService fraudService = new FraudDetectionService();
        boolean fraudulent = fraudService.isFraudulent(fraudOrder);
        System.out.println("Заявка подозрительна: " + fraudulent);
    }
}
