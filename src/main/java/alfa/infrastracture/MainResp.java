package alfa.infrastracture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainResp {

    private DataResp data;

    public DataResp getData() {
        return data;
    }

    public void setData(DataResp data) {
        this.data = data;
    }
}
