public class Account {
    private static final int MODULO = 11;
    private final String accountNumber;
    private static final int ACCOUNT_NUMBER_LENGTH = 9;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isValid() {
        return calculateWeightedSum() % MODULO == 0;
    }

    private int calculateWeightedSum() {
        int sum = 0;
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; ++i) {
            sum += (ACCOUNT_NUMBER_LENGTH-i) * (accountNumber.charAt(i) - '0');
        }
        return sum;
    }
}
