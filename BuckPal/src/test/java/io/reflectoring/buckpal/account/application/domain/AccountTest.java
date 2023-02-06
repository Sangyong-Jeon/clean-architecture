package io.reflectoring.buckpal.account.application.domain;

import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.ActivityWindow;
import io.reflectoring.buckpal.account.domain.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.reflectoring.buckpal.account.domain.Account.*;
import static io.reflectoring.buckpal.common.AccountTestData.defaultAccount;
import static io.reflectoring.buckpal.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.*;

class AccountTest {

    @Test
    void withdrawalSucceeds() {
        AccountId accountId = new AccountId(1L);
        Account account = defaultAccount()
                .withAccountId(accountId)
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(new ActivityWindow(
                        defaultActivity()
                                .withTargetAccount(accountId)
                                .withMoney(Money.of(999L)).build(),
                        defaultActivity()
                                .withTargetAccount(accountId)
                                .withMoney(Money.of(1L)).build()))
                .build();

        boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

        assertThat(success).isTrue();
        assertThat(account.getActivityWindow().getActivities()).hasSize(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L));
    }
}
