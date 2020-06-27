package alfa.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    private TerminalDao terminalDao;

    @Autowired
    public TerminalService(TerminalDao terminalDao) {
        this.terminalDao = terminalDao;
    }


    public Terminal get(int id) {
        return terminalDao.getTerminal(id)
                .orElseThrow();
    }

    public Terminal getNearest(double latitude, double longitude, boolean payments) {
        return terminalDao.getTerminal(latitude, longitude, payments);
    }
}
