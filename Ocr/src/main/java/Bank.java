public class Bank {
    public static final int RAW_ACCOUNT_NUMBER_LENGTH = Ocr.TOTAL_DIGITS_IN_ACCOUNT_NUMBER * Ocr.SINGLE_RAW_NUMBER_WIDTH * Ocr.SINGLE_RAW_NUMBER_HEIGHT;
    private final Account account;
    private final Ocr ocr;
    private String rawAccountNumbers;

    public Bank(String rawAccountNumbers) {
        this.rawAccountNumbers = rawAccountNumbers;
        this.ocr = new Ocr();
        this.account = new Account();
    }

    public String generateReport() {
        int length = rawAccountNumbers.length() / RAW_ACCOUNT_NUMBER_LENGTH;
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
}
