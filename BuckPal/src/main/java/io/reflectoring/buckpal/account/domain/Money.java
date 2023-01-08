package io.reflectoring.buckpal.account.domain;

import lombok.NonNull;
import lombok.Value;

import java.math.BigInteger;

@Value
public class Money {

    public static Money ZERO = Money.of(0L);

    @NonNull
    private final BigInteger amount;

    public boolean isPositiveOrZero() {
        return amount.compareTo(BigInteger.ZERO) >= 0;
    }

    public boolean isNegative() {
        return amount.compareTo(BigInteger.ZERO) < 0;
    }

    public boolean isPositive() {
        return amount.compareTo(BigInteger.ZERO) > 0;
    }

    public boolean isGreaterThanOrEqualTo(Money money) {
        return amount.compareTo(money.amount) >= 0;
    }

    public boolean isGreaterThan(Money money) {
        return amount.compareTo(money.amount) >= 1;
    }

    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public static Money add(Money a, Money b) {
        return new Money(a.amount.add(b.amount));
    }

    public Money minus(Money money) {
        return new Money(amount.subtract(money.amount));
    }

    public Money plus(Money money) {
        return new Money(amount.add(money.amount));
    }

    public static Money subtract(Money a, Money b) {
        return new Money(a.amount.subtract(b.amount));
    }

    public Money negate() {
        return new Money(amount.negate());
    }
}
