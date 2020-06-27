package alfa.infrastracture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminalResp {

    private AddressResp address;
    private Integer deviceId;
    private CoordinatesResp coordinates;
    private List<String> availablePaymentSystems;

    public AddressResp getAddress() {
        return address;
    }

    public void setAddress(AddressResp address) {
        this.address = address;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public CoordinatesResp getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesResp coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getAvailablePaymentSystems() {
        return availablePaymentSystems;
    }

    public void setAvailablePaymentSystems(List<String> availablePaymentSystems) {
        this.availablePaymentSystems = availablePaymentSystems;
    }
}
