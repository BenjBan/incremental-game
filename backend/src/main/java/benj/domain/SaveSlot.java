package benj.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a save slot in the game.
 * Each player can have up to 3 save slots.
 */
public class SaveSlot {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy");

    private final int id;
    private String name;
    private final LocalDateTime createdAt;
    private long playTimeSeconds;
    private Player player;

    public SaveSlot(int id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.playTimeSeconds = 0;
        this.player = new Player();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DATE_FORMATTER);
    }

    public long getPlayTimeSeconds() {
        return playTimeSeconds;
    }

    public String getFormattedPlayTime() {
        long hours = playTimeSeconds / 3600;
        return hours + "h";
    }

    public void addPlayTime(long seconds) {
        this.playTimeSeconds += seconds;
    }

    public Player getPlayer() {
        return player;
    }

    public Balance getMoney() {
        return player.getBalance();
    }
}
