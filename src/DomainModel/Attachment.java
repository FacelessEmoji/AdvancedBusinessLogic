package DomainModel;

import java.util.UUID;

public class Attachment {
    private final String id;
    private final String fileName;
    private final String fileType;

    public Attachment(String fileName, String fileType) {
        this.id = UUID.randomUUID().toString();
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
