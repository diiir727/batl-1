package alfa.infrastracture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResp {

    List<TerminalResp> atms;

    public List<TerminalResp> getAtms() {
        return atms;
    }

    public void setAtms(List<TerminalResp> atms) {
        this.atms = atms;
    }
}
