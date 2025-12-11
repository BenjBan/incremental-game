package benj.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Immutable value object representing an amount of money.
 * Uses {@link BigDecimal} for arbitrary‑precision arithmetic, which is
 * important for incremental games where values can become very large.
 */
public final class Balance {
    private static final int SCALE = 2; // cents precision
    private final BigDecimal amount;

    public Balance(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public Balance(long amount) {
        this(BigDecimal.valueOf(amount));
    }

    public Balance(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    /**
     * @return the underlying {@link BigDecimal} value.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return a new {@code Balance} representing this amount plus {@code other}.
     */
    public Balance add(Balance other) {
        return new Balance(this.amount.add(other.amount));
    }

    /**
     * @return a new {@code Balance} representing this amount minus {@code other}.
     */
    public Balance subtract(Balance other) {
        return new Balance(this.amount.subtract(other.amount));
    }

    @Override
    public String toString() {
        return "$" + amount.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Balance))
            return false;
        Balance balance = (Balance) o;
        return amount.compareTo(balance.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
