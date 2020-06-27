package alfa.app;

import alfa.domain.Terminal;

public class AtmResponse {
    private String city;
    private Integer deviceId;
    private String latitude;
    private String location;
    private String longitude;
    private boolean payments;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isPayments() {
        return payments;
    }

    public void setPayments(boolean payments) {
        this.payments = payments;
    }

    public static AtmResponse of(Terminal terminal) {
        var res = new AtmResponse();
        res.setCity(terminal.getCity());
        res.setDeviceId(terminal.getDeviceId());
        res.setLatitude(terminal.getLatitude());
        res.setLongitude(terminal.getLongitude());
        res.setLocation(terminal.getLocation());
        res.setPayments(terminal.isPayments());
        return res;
    }
}
