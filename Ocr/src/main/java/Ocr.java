import java.util.ArrayList;
import java.util.List;

public class Ocr {
    public static final int SINGLE_RAW_NUMBER_HEIGHT = 4;
    public static final int SINGLE_RAW_NUMBER_WIDTH = 3;
    List<String> numberDictionary = new ArrayList<>();

    public Ocr() {
        numberDictionary.add("" +
                " _ \n" +
                "| |\n" +
                "|_|\n" +
                "   ");
        numberDictionary.add("" +
                "   \n" +
                "  |\n" +
                "  |\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                " _|\n" +
                "|_ \n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                " _|\n" +
                " _|\n" +
                "   ");
        numberDictionary.add("" +
                "   \n" +
                "|_|\n" +
                "  |\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                "|_ \n" +
                " _|\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                "|_ \n" +
                "|_|\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                "  |\n" +
                "  |\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                "|_|\n" +
                "|_|\n" +
                "   ");
        numberDictionary.add("" +
                " _ \n" +
                "|_|\n" +
                " _|\n" +
                "   ");
    }

    public String parseSingleRawNumber(String rawSingleNumber) {

        for (int i = 0; i < numberDictionary.size(); ++i) {
            if (numberDictionary.get(i).equals(rawSingleNumber)) return Integer.toString(i);
        }
        return "-1";
    }

    public String parseRawNumbers(String rawAccountNumber) {
        String[] lines = rawAccountNumber.split("\n");
        StringBuilder accountNumber = new StringBuilder();
        while (!lines[0].isEmpty()) {
            accountNumber.append(parseSingleRawNumber(extractSingleRawNumber(lines)));
        }
        return accountNumber.toString();
    }

    private String extractSingleRawNumber(String[] lines) {
        StringBuilder singleRawNumber = new StringBuilder();
        for (int i = 0; i < SINGLE_RAW_NUMBER_HEIGHT; ++i) {
            singleRawNumber.append(lines[i].substring(0, SINGLE_RAW_NUMBER_WIDTH)).append("\n");
            lines[i] = lines[i].substring(SINGLE_RAW_NUMBER_WIDTH);
        }
        singleRawNumber.deleteCharAt(singleRawNumber.length() - 1);
        return singleRawNumber.toString();
    }
}
