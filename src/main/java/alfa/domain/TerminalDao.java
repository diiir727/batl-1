package alfa.domain;

import java.util.Optional;

public interface TerminalDao {
    Optional<Terminal> getTerminal(Integer id);

    Terminal getTerminal(double latitude, double longitude, boolean payments);
}
