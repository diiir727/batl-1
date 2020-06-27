package alfa.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/live")
public class LiveController {

    @GetMapping
    public ResponseEntity<?> getProduct() {
        return ResponseEntity.ok().build();
    }

}
