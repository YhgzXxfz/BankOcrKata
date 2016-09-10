import java.util.*;

public class Bank {
    private static final int RAW_ACCOUNT_NUMBER_LENGTH = Ocr.TOTAL_DIGITS_IN_ACCOUNT_NUMBER * Ocr.SINGLE_RAW_NUMBER_WIDTH * Ocr.SINGLE_RAW_NUMBER_HEIGHT;
    private final Account account;
    private final Ocr ocr;
    private final String originalRawAccountNumbers;
    private Map<Character, List<Character>> mp = new HashMap<>();

    public Bank(String originalRawAccountNumbers) {
        this.originalRawAccountNumbers = originalRawAccountNumbers;
        this.ocr = new Ocr();
        this.account = new Account();

        mp.put('0', Arrays.asList('8'));
        mp.put('1', Arrays.asList('7'));
        mp.put('2', Arrays.asList('2'));
        mp.put('3', Arrays.asList('3'));
        mp.put('4', Arrays.asList('4'));
        mp.put('5', Arrays.asList('6', '9'));
        mp.put('6', Arrays.asList('5', '8'));
        mp.put('7', Arrays.asList('1'));
        mp.put('8', Arrays.asList('0', '6', '9'));
        mp.put('9', Arrays.asList('5', '8'));

    }

    public String generateReport() {
        int length = originalRawAccountNumbers.length() / RAW_ACCOUNT_NUMBER_LENGTH;
        String rawAccountNumbers = originalRawAccountNumbers;
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            generateReport(report, ocr.parseRawNumbers(rawAccountNumbers.substring(0, RAW_ACCOUNT_NUMBER_LENGTH)));
            rawAccountNumbers = rawAccountNumbers.substring(RAW_ACCOUNT_NUMBER_LENGTH);
        }
        report.deleteCharAt(report.length() - 1);
        return report.toString();
    }

    private void generateReport(StringBuilder report, String accountNumber) {
        account.setAccountNumber(accountNumber);
        report.append(accountNumber).append(checkStatus(account)).append("\n");
    }

    private String checkStatus(Account account) {
        if (account.isIllegal()) return " ILL";
        if (!account.isValid()) return " ERR";
        return "";
    }

    public String generateReportWithCorrection() {
        int length = originalRawAccountNumbers.length() / RAW_ACCOUNT_NUMBER_LENGTH;
        StringBuilder report = new StringBuilder();
        String rawAccountNumbers = originalRawAccountNumbers;

        for (int j = 0; j < length; ++j) {
            generateReportWithCorrection(report, rawAccountNumbers.substring(0, RAW_ACCOUNT_NUMBER_LENGTH));
            rawAccountNumbers = rawAccountNumbers.substring(RAW_ACCOUNT_NUMBER_LENGTH);
        }
        report.deleteCharAt(report.length() - 1);
        return report.toString();
    }

    private void generateReportWithCorrection(StringBuilder report, String rawAccountNumber) {
        String accountNumber = ocr.parseRawNumbers(rawAccountNumber);
        account.setAccountNumber(accountNumber);

        if (account.isValid()) {
            report.append(accountNumber);
        } else {
            List<String> corrections = guessPossibleCorrections(accountNumber);
            if (corrections.isEmpty()) {
                report.append(accountNumber).append(" ERR");
            } else {
                report.append(corrections.get(0));
                if (corrections.size() > 1) report.append(" AMB");
            }
        }
        report.append('\n');
    }

    private List<String> guessPossibleCorrections(String accountNumber) {
        List<String> corrections = new ArrayList<>();
        char[] chars = accountNumber.toCharArray();
        for (int i = 0; i < Ocr.TOTAL_DIGITS_IN_ACCOUNT_NUMBER; ++i) {
            final char temp = chars[i];
            for (char digit : mp.get(temp)) {
                chars[i] = digit;
                String correctedAccountNumber = new String(chars);
                account.setAccountNumber(correctedAccountNumber);
                if (account.isValid()) {
                    corrections.add(correctedAccountNumber);
                }
            }
            chars[i] = temp;
        }
        return corrections;
    }
}
