import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class AccountTest {

    @DataProvider({
            "000000019",
            "000000302"
    })
    @Test
    public void should_validate_account_when_its_weighted_sum_is_divisible_by_11(String accountNumber) {

        // When
        Account account = new Account(accountNumber);

        // Then
        assertThat(account.isValid()).isTrue();
    }

    @Test
    public void should_not_validate_acoount_when_its_weighted_sum_is_not_divisible_by_11() {

        // Given
        String accountNumber = "000000018";

        // When
        Account account = new Account(accountNumber);

        // Then
        assertThat(account.isValid()).isFalse();
    }

}
