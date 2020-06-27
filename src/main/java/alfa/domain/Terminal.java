package alfa.domain;

public class Terminal {

    private String city;
    private Integer deviceId;
    private String latitude;
    private String location;
    private String longitude;
    private boolean payments;

    public Terminal(String city, Integer deviceId, String latitude, String location, String longitude, boolean payments) {
        this.city = city;
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.location = location;
        this.longitude = longitude;
        this.payments = payments;
    }


    public String getCity() {
        return city;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLocation() {
        return location;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean isPayments() {
        return payments;
    }
}
