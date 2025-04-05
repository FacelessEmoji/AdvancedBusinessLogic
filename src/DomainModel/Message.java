package DomainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Message {
    private final String id;
    private final String content;
    private final String sender; // например, "client" или "driver"
    private boolean confirmed;
    private final List<Attachment> attachments;

    public Message(String content, String sender) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.sender = sender;
        this.confirmed = false;
        this.attachments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    // Добавление вложения возможно только вместе с сообщением
    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    // Подтверждение сообщения доступно только для стороны, отличной от отправителя
    public void confirm(String confirmer) {
        if (this.sender.equals(confirmer)) {
            throw new IllegalArgumentException("Отправитель не может подтверждать собственное сообщение.");
        }
        this.confirmed = true;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", confirmed=" + confirmed +
                ", attachments=" + attachments +
                '}';
    }
}
