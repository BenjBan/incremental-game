package benj.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    @GetMapping("/click")
    public Map<String, Object> handleClick() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Button clicked! Hello from Java Backend!");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
