import java.util.ArrayList;
import java.util.List;

public class Ocr {
    public static final int SINGLE_RAW_NUMBER_HEIGHT = 3;
    public static final int SINGLE_RAW_NUMBER_WIDTH = 3;
    public static final int TOTAL_DIGITS_IN_ACCOUNT_NUMBER = 9;
    public static final List<String> numberDictionary = new ArrayList<>();

    public Ocr() {
        setUpNumberTemplate();
    }

    private void setUpNumberTemplate() {
        numberDictionary.add("" +
                " _ " +
                "| |" +
                "|_|");
        numberDictionary.add("" +
                "   " +
                "  |" +
                "  |");
        numberDictionary.add("" +
                " _ " +
                " _|" +
                "|_ ");
        numberDictionary.add("" +
                " _ " +
                " _|" +
                " _|");
        numberDictionary.add("" +
                "   " +
                "|_|" +
                "  |");
        numberDictionary.add("" +
                " _ " +
                "|_ " +
                " _|");
        numberDictionary.add("" +
                " _ " +
                "|_ " +
                "|_|");
        numberDictionary.add("" +
                " _ " +
                "  |" +
                "  |");
        numberDictionary.add("" +
                " _ " +
                "|_|" +
                "|_|");
        numberDictionary.add("" +
                " _ " +
                "|_|" +
                " _|");
    }

    public String parseSingleRawNumber(String singleRawNumber) {

        for (int i = 0; i < numberDictionary.size(); ++i) {
            if (numberDictionary.get(i).equals(singleRawNumber)) return Integer.toString(i);
        }
        return "?";
    }

    public String parseRawNumbers(String rawAccountNumber) {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < TOTAL_DIGITS_IN_ACCOUNT_NUMBER; ++i) {
            accountNumber.append(parseSingleRawNumber(extractSingleRawNumber(rawAccountNumber, i)));
        }
        return accountNumber.toString();
    }

    private String[] splitRawNumberIntoLines(String rawAccountNumber) {
        String[] lines = new String[3];
        lines[0] = rawAccountNumber.substring(0, SINGLE_RAW_NUMBER_WIDTH * TOTAL_DIGITS_IN_ACCOUNT_NUMBER);
        lines[1] = rawAccountNumber.substring(
                SINGLE_RAW_NUMBER_WIDTH * TOTAL_DIGITS_IN_ACCOUNT_NUMBER,
                2 * SINGLE_RAW_NUMBER_WIDTH * TOTAL_DIGITS_IN_ACCOUNT_NUMBER
        );
        lines[2] = rawAccountNumber.substring(
                2 * SINGLE_RAW_NUMBER_WIDTH * TOTAL_DIGITS_IN_ACCOUNT_NUMBER,
                3 * SINGLE_RAW_NUMBER_WIDTH * TOTAL_DIGITS_IN_ACCOUNT_NUMBER
        );
        return lines;
    }

    public String extractSingleRawNumber(String rawAccountNumber, int i) {
        String[] lines = splitRawNumberIntoLines(rawAccountNumber);
        StringBuilder singleRawNumber = new StringBuilder();

        for (int j = 0; j < SINGLE_RAW_NUMBER_HEIGHT; ++j) {
            singleRawNumber.append(lines[j].substring(i * SINGLE_RAW_NUMBER_WIDTH, (i + 1) * SINGLE_RAW_NUMBER_WIDTH));
        }

        return singleRawNumber.toString();
    }

    public List<String> splitRawNumberIntoSingleRawNumbers(String rawAccountNumber) {
        List<String> singleRawNumberList = new ArrayList<>();

        for (int i = 0; i < TOTAL_DIGITS_IN_ACCOUNT_NUMBER; ++i) {
            singleRawNumberList.add(extractSingleRawNumber(rawAccountNumber, i));
        }

        return singleRawNumberList;
    }
}
