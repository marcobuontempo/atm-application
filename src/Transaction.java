import java.util.Date;

public class Transaction {
    
    //the amount of this transaction
    private double amount;

    //time and date of transaction
    private Date timestamp;

    //memo for the transaction
    private String memo;

    //the account in which the transaction was performed
    private Account inAccount;

    /**
     * Create a new transaction (w/o memo)
     * @param amount        the amount transacted
     * @param inAccount     the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction (w/ memo)
     * @param amount        the amount transacted
     * @param memo          the memo for the transaction
     * @param inAccount     the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount) {
        //call the two-argument constructor first (any changes in other constructor will therefore also be effective here)
        this(amount, inAccount);

        //set the memo
        this.memo = memo;
    }

}
