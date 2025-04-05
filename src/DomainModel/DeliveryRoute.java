package DomainModel;

public class DeliveryRoute {
    private final String origin;
    private final String destination;
    private final String details;

    public DeliveryRoute(String origin, String destination, String details) {
        this.origin = origin;
        this.destination = destination;
        this.details = details;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "DeliveryRoute{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
