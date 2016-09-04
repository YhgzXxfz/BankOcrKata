import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class BankOcrTest {

    @DataProvider(value = {
            "" +
                    " _ \n" +
                    "| |\n" +
                    "|_|\n" +
                    "   ,0",
            "" +
                    "   \n" +
                    "  |\n" +
                    "  |\n" +
                    "   ,1",
            "" +
                    " _ \n" +
                    " _|\n" +
                    "|_ \n" +
                    "   ,2",
            "" +
                    " _ \n" +
                    " _|\n" +
                    " _|\n" +
                    "   ,3",
            "" +
                    "   \n" +
                    "|_|\n" +
                    "  |\n" +
                    "   ,4",
            "" +
                    " _ \n" +
                    "|_ \n" +
                    " _|\n" +
                    "   ,5",
            "" +
                    " _ \n" +
                    "|_ \n" +
                    "|_|\n" +
                    "   ,6",
            "" +
                    " _ \n" +
                    "  |\n" +
                    "  |\n" +
                    "   ,7",
            "" +
                    " _ \n" +
                    "|_|\n" +
                    "|_|\n" +
                    "   ,8",
            "" +
                    " _ \n" +
                    "|_|\n" +
                    " _|\n" +
                    "   ,9"
    }, trimValues = false)
    @Test
    public void should_parse_single_raw_number(String rawSingleNumber, String expectedSingleNumber) {
        // Given
        Ocr ocr = new Ocr();

        // When
        String singleNumber = ocr.parseSingleRawNumber(rawSingleNumber);

        // Then
        assertThat(singleNumber).isEqualTo(expectedSingleNumber);
    }

    @Test
    public void should_parse_one_raw_account_number() {
        // Given
        Ocr ocr = new Ocr();
        String rawAccountNumber = "" +
                " _     _  _     _  _  _  _ \n" +
                "|_|  | _| _||_||_ |_   ||_|\n" +
                " _|  ||_  _|  | _||_|  ||_|\n" +
                "                           ";

        // When
        String accountNumber = ocr.parseRawNumbers(rawAccountNumber);

        // Then
        assertThat(accountNumber).isEqualTo("912345678");
    }
}
