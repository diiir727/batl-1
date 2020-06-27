package alfa.app;

import alfa.domain.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/atms")
public class MainController {

    private TerminalService terminalService;

    @Autowired
    public MainController(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        try {
            var res = this.terminalService.get(id);
            return ResponseEntity.ok(AtmResponse.of(res));
        } catch (NoSuchElementException e) {
            var error = new NotFoundResp();
            error.setStatus("atm not found");
            return ResponseEntity.status(404).body(error);
        }
    }

    @GetMapping(path = "/nearest")
    public ResponseEntity<?> getProduct(@RequestParam double latitude, @RequestParam double longitude,
                                        @RequestParam(required = false) boolean payments) {
        var res = this.terminalService.getNearest(latitude, longitude, payments);
        return ResponseEntity.ok(AtmResponse.of(res));
    }

}
