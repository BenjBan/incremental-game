package benj.app;

import benj.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameState gameState = new GameState();

    // ========== Save Slot Endpoints ==========

    @GetMapping("/slots")
    public Map<String, Object> getSlots() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> slots = gameState.getSaveSlots().stream()
                .map(this::slotToMap)
                .collect(Collectors.toList());
        response.put("slots", slots);
        response.put("isEmpty", gameState.isEmpty());
        response.put("isFull", gameState.isFull());
        return response;
    }

    @PostMapping("/slots")
    public Map<String, Object> createSlot(@RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        String name = body.getOrDefault("name", "My First World");

        Optional<SaveSlot> slotOpt = gameState.createSlot(name);
        if (slotOpt.isPresent()) {
            response.put("success", true);
            response.put("slot", slotToMap(slotOpt.get()));
        } else {
            response.put("success", false);
            response.put("error", "Maximum save slots reached");
        }
        return response;
    }

    @DeleteMapping("/slots/{id}")
    public Map<String, Object> deleteSlot(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = gameState.deleteSlot(id);
        response.put("success", deleted);
        if (!deleted) {
            response.put("error", "Slot not found");
        }
        return response;
    }

    // ========== Game Endpoints ==========

    @GetMapping("/game/{slotId}")
    public Map<String, Object> getGameState(@PathVariable int slotId) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaveSlot> slotOpt = gameState.getSlot(slotId);

        if (slotOpt.isEmpty()) {
            response.put("success", false);
            response.put("error", "Slot not found");
            return response;
        }

        SaveSlot slot = slotOpt.get();
        Player player = slot.getPlayer();

        response.put("success", true);
        response.put("slotId", slot.getId());
        response.put("slotName", slot.getName());
        response.put("money", player.getBalance().getAmount());
        response.put("incomePerSecond", player.getTotalIncomePerSecond());
        response.put("upgrades", player.getUpgrades().stream()
                .map(this::upgradeToMap)
                .collect(Collectors.toList()));

        return response;
    }

    @PostMapping("/game/{slotId}/upgrade/{upgradeId}")
    public Map<String, Object> buyUpgrade(
            @PathVariable int slotId,
            @PathVariable int upgradeId) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaveSlot> slotOpt = gameState.getSlot(slotId);

        if (slotOpt.isEmpty()) {
            response.put("success", false);
            response.put("error", "Slot not found");
            return response;
        }

        Player player = slotOpt.get().getPlayer();
        boolean purchased = player.purchaseUpgrade(upgradeId);

        response.put("success", purchased);
        if (!purchased) {
            response.put("error", "Cannot afford upgrade or upgrade not found");
        }
        response.put("money", player.getBalance().getAmount());
        response.put("incomePerSecond", player.getTotalIncomePerSecond());

        return response;
    }

    @PostMapping("/game/{slotId}/tick")
    public Map<String, Object> processTick(
            @PathVariable int slotId,
            @RequestBody Map<String, Object> body) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaveSlot> slotOpt = gameState.getSlot(slotId);

        if (slotOpt.isEmpty()) {
            response.put("success", false);
            response.put("error", "Slot not found");
            return response;
        }

        double deltaSeconds = 1.0;
        if (body.containsKey("deltaSeconds")) {
            Object delta = body.get("deltaSeconds");
            if (delta instanceof Number) {
                deltaSeconds = ((Number) delta).doubleValue();
            }
        }

        SaveSlot slot = slotOpt.get();
        Player player = slot.getPlayer();
        player.processTick(deltaSeconds);
        slot.addPlayTime((long) deltaSeconds);

        response.put("success", true);
        response.put("money", player.getBalance().getAmount());
        response.put("incomePerSecond", player.getTotalIncomePerSecond());

        return response;
    }

    // ========== Helper Methods ==========

    private Map<String, Object> slotToMap(SaveSlot slot) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", slot.getId());
        map.put("name", slot.getName());
        map.put("createdAt", slot.getFormattedCreatedAt());
        map.put("money", slot.getMoney().toString());
        map.put("playTime", slot.getFormattedPlayTime());
        return map;
    }

    private Map<String, Object> upgradeToMap(Upgrade upgrade) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", upgrade.getId());
        map.put("name", upgrade.getName());
        map.put("description", upgrade.getDescription());
        map.put("level", upgrade.getLevel());
        map.put("income", upgrade.getCurrentIncome());
        map.put("incomePerSecond", upgrade.getIncomePerSecond());
        map.put("speed", upgrade.getSpeed());
        map.put("cost", upgrade.getCost().getAmount());
        map.put("costDisplay", upgrade.isFree() ? "FREE" : upgrade.getCost().toString());
        return map;
    }
}
