import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class OcrTest {

    @DataProvider(value = {
            "" +
                    " _ " +
                    "| |" +
                    "|_|,0",
            "" +
                    "   " +
                    "  |" +
                    "  |,1",
            "" +
                    " _ " +
                    " _|" +
                    "|_ ,2",
            "" +
                    " _ " +
                    " _|" +
                    " _|,3",
            "" +
                    "   " +
                    "|_|" +
                    "  |,4",
            "" +
                    " _ " +
                    "|_ " +
                    " _|,5",
            "" +
                    " _ " +
                    "|_ " +
                    "|_|,6",
            "" +
                    " _ " +
                    "  |" +
                    "  |,7",
            "" +
                    " _ " +
                    "|_|" +
                    "|_|,8",
            "" +
                    " _ " +
                    "|_|" +
                    " _|,9"
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
                " _     _  _     _  _  _  _ " +
                "|_|  | _| _||_||_ |_   ||_|" +
                " _|  ||_  _|  | _||_|  ||_|";

        // When
        String accountNumber = ocr.parseRawNumbers(rawAccountNumber);

        // Then
        assertThat(accountNumber).isEqualTo("912345678");
    }
}
