package benj.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an upgrade that generates passive income.
 * Each upgrade has a level that can be increased by purchasing.
 */
public class Upgrade {
    private final int id;
    private final String name;
    private final String description;
    private int level;
    private final BigDecimal baseIncome;
    private final double baseSpeed; // seconds between income ticks
    private final BigDecimal baseCost;
    private final double costMultiplier;

    public Upgrade(int id, String name, String description,
            BigDecimal baseIncome, double baseSpeed, BigDecimal baseCost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = 0;
        this.baseIncome = baseIncome;
        this.baseSpeed = baseSpeed;
        this.baseCost = baseCost;
        this.costMultiplier = 1.15; // 15% cost increase per level
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public void incrementLevel() {
        this.level++;
    }

    /**
     * @return income per tick at current level
     */
    public BigDecimal getCurrentIncome() {
        if (level == 0) {
            return BigDecimal.ZERO;
        }
        return baseIncome.multiply(BigDecimal.valueOf(level));
    }

    /**
     * @return income per second at current level
     */
    public BigDecimal getIncomePerSecond() {
        if (level == 0) {
            return BigDecimal.ZERO;
        }
        return getCurrentIncome().divide(BigDecimal.valueOf(baseSpeed), 2, RoundingMode.HALF_UP);
    }

    public double getSpeed() {
        return baseSpeed;
    }

    /**
     * @return the cost to purchase the next level
     */
    public Balance getCost() {
        if (level == 0) {
            // First level is free for the first upgrade
            if (id == 1) {
                return new Balance(BigDecimal.ZERO);
            }
            return new Balance(baseCost);
        }
        // Cost increases exponentially with level
        BigDecimal multiplier = BigDecimal.valueOf(Math.pow(costMultiplier, level));
        return new Balance(baseCost.multiply(multiplier).setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * @return true if cost is zero (free upgrade)
     */
    public boolean isFree() {
        return getCost().getAmount().compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Creates a copy of this upgrade for a new player
     */
    public Upgrade copy() {
        return new Upgrade(id, name, description, baseIncome, baseSpeed, baseCost);
    }
}
