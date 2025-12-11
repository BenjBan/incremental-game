package benj.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Holds the global game state including all save slots.
 * This is a singleton managed by Spring.
 */
public class GameState {
    private static final int MAX_SLOTS = 3;

    private final List<SaveSlot> saveSlots;
    private int nextSlotId;

    public GameState() {
        this.saveSlots = new ArrayList<>();
        this.nextSlotId = 1;
    }

    /**
     * @return all save slots
     */
    public List<SaveSlot> getSaveSlots() {
        return new ArrayList<>(saveSlots);
    }

    /**
     * @return true if no save slots exist
     */
    public boolean isEmpty() {
        return saveSlots.isEmpty();
    }

    /**
     * @return true if maximum slots have been created
     */
    public boolean isFull() {
        return saveSlots.size() >= MAX_SLOTS;
    }

    /**
     * Creates a new save slot with the given name.
     * 
     * @param name the name for the save slot
     * @return the created save slot, or empty if max slots reached
     */
    public Optional<SaveSlot> createSlot(String name) {
        if (isFull()) {
            return Optional.empty();
        }
        SaveSlot slot = new SaveSlot(nextSlotId++, name);
        saveSlots.add(slot);
        return Optional.of(slot);
    }

    /**
     * Gets a save slot by its ID.
     * 
     * @param id the slot ID
     * @return the save slot, or empty if not found
     */
    public Optional<SaveSlot> getSlot(int id) {
        return saveSlots.stream()
                .filter(slot -> slot.getId() == id)
                .findFirst();
    }

    /**
     * Deletes a save slot by its ID.
     * 
     * @param id the slot ID
     * @return true if the slot was deleted
     */
    public boolean deleteSlot(int id) {
        return saveSlots.removeIf(slot -> slot.getId() == id);
    }

    /**
     * Creates the default set of upgrades for a new game.
     */
    public static List<Upgrade> createDefaultUpgrades() {
        List<Upgrade> upgrades = new ArrayList<>();

        upgrades.add(new Upgrade(1, "Basic",
                "Automatically gain +$1 per second",
                BigDecimal.ONE, 1.0, BigDecimal.ZERO));

        upgrades.add(new Upgrade(2, "Standard",
                "Automatically gain +$5 per second",
                BigDecimal.valueOf(5), 1.0, BigDecimal.valueOf(50)));

        upgrades.add(new Upgrade(3, "Advanced",
                "Automatically gain +$25 per second",
                BigDecimal.valueOf(25), 1.0, BigDecimal.valueOf(500)));

        upgrades.add(new Upgrade(4, "Premium",
                "Automatically gain +$100 per second",
                BigDecimal.valueOf(100), 1.0, BigDecimal.valueOf(5000)));

        upgrades.add(new Upgrade(5, "Elite",
                "Automatically gain +$500 per second",
                BigDecimal.valueOf(500), 1.0, BigDecimal.valueOf(50000)));

        upgrades.add(new Upgrade(6, "Ultimate",
                "Automatically gain +$2500 per second",
                BigDecimal.valueOf(2500), 1.0, BigDecimal.valueOf(500000)));

        upgrades.add(new Upgrade(7, "Legendary",
                "Automatically gain +$10000 per second",
                BigDecimal.valueOf(10000), 1.0, BigDecimal.valueOf(5000000)));

        return upgrades;
    }
}
