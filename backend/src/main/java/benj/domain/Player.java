package benj.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a player in the incremental game.
 * 
 * @field balance the current balance of the player.
 * @field upgrades the list of upgrades owned by the player.
 */
public class Player {
    private Balance balance;
    private List<Upgrade> upgrades;

    public Player() {
        this.balance = new Balance(BigDecimal.ZERO);
        this.upgrades = GameState.createDefaultUpgrades();
    }

    public Player(Balance initialBalance) {
        this.balance = initialBalance;
        this.upgrades = GameState.createDefaultUpgrades();
    }

    /**
     * @return the current balance.
     */
    public Balance getBalance() {
        return balance;
    }

    /**
     * @return all upgrades
     */
    public List<Upgrade> getUpgrades() {
        return new ArrayList<>(upgrades);
    }

    /**
     * Gets an upgrade by its ID.
     */
    public Optional<Upgrade> getUpgrade(int id) {
        return upgrades.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    /**
     * Adds the specified amount to the player's balance.
     * 
     * @param amount the amount to add
     */
    public void addBalance(Balance amount) {
        this.balance = this.balance.add(amount);
    }

    /**
     * Subtracts the specified amount from the player's balance.
     * 
     * @param amount the amount to subtract
     */
    public void subtractBalance(Balance amount) {
        this.balance = this.balance.subtract(amount);
    }

    /**
     * Convenience method to add a raw BigDecimal amount.
     * 
     * @param amount the amount to add
     */
    public void addBalance(BigDecimal amount) {
        addBalance(new Balance(amount));
    }

    /**
     * Convenience method to subtract a raw BigDecimal amount.
     * 
     * @param amount the amount to subtract
     */
    public void subtractBalance(BigDecimal amount) {
        subtractBalance(new Balance(amount));
    }

    /**
     * Calculates total income per second from all upgrades.
     */
    public BigDecimal getTotalIncomePerSecond() {
        return upgrades.stream()
                .map(Upgrade::getIncomePerSecond)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Attempts to purchase an upgrade.
     * 
     * @param upgradeId the ID of the upgrade to purchase
     * @return true if purchase was successful
     */
    public boolean purchaseUpgrade(int upgradeId) {
        Optional<Upgrade> upgradeOpt = getUpgrade(upgradeId);
        if (upgradeOpt.isEmpty()) {
            return false;
        }

        Upgrade upgrade = upgradeOpt.get();
        Balance cost = upgrade.getCost();

        // Check if player can afford it
        if (balance.getAmount().compareTo(cost.getAmount()) < 0) {
            return false;
        }

        // Deduct cost and increment level
        subtractBalance(cost);
        upgrade.incrementLevel();
        return true;
    }

    /**
     * Processes a game tick, adding passive income.
     * 
     * @param deltaSeconds time elapsed since last tick
     */
    public void processTick(double deltaSeconds) {
        BigDecimal income = getTotalIncomePerSecond().multiply(BigDecimal.valueOf(deltaSeconds));
        addBalance(income);
    }
}
