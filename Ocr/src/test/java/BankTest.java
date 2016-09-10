import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    @Test
    public void should_generate_report_for_different_types_of_raw_account_numbers() {

        // Given
        final String rawAccountNumbers = "" +
                "    _  _  _  _  _  _  _  _ " +
                "|_||_   ||_ | ||_|| || || |" +
                "  | _|  | _||_||_||_||_||_|" +
                " _  _     _  _        _  _ " +
                "|_ |_ |_| _|  |  ||_||_||_ " +
                "|_||_|  | _|  |  |  | _| _|" +
                " _  _        _  _  _  _  _ " +
                "|_||_   |  || |  ||_  _||_ " +
                "|_||_|  |  ||_|    _  _||_|";
        Bank bank = new Bank(rawAccountNumbers);

        // When
        String report = bank.generateReport();

        // Then
        assertThat(report).isEqualTo("457508000\n664371495 ERR\n86110??36 ILL");
    }

    @Test
    public void should_generate_report_with_correction_when_raw_account_numbers_are_invalid() {

        // Given
        final String rawAccountNumbers = "" +
                "    _  _  _  _  _  _     _ " +
                "|_||_|| || ||_   |  |  ||_ " +
                "  | _||_||_||_|  |  |  | _|" +
                "    _  _  _  _  _  _  _  _ " +
                "|_||_   ||_ | ||_|| || || |" +
                "  | _|  | _||_||_||_||_||_|" +
                " _  _     _  _        _  _ " +
                "|_ |_ |_| _|  |  ||_||_||_ " +
                "|_||_|  | _|  |  |  | _| _|";
        Bank bank = new Bank(rawAccountNumbers);

        // When
        String report = bank.generateReportWithCorrection();

        // Then
        assertThat(report).isEqualTo("490867715 AMB\n457508000\n664371485");
    }
}
