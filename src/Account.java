import java.util.ArrayList;

public class Account {
    
    //name of account
    private String name;

    //account ID number
    private String uuid;

    //User object that holds the account
    private User holder;

    //list of transactions for the account
    private ArrayList<Transaction> transactions;

    /**
     * 
     * @param name      the name of the account
     * @param holder    the User object that holds the account
     * @param theBank   the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank) {
        //set the account name and holder
        this.name = name;
        this.holder = holder;

        //get next account UUID
        this.uuid = theBank.getNewAccountUUID();

        //initialise transactions
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * Return the user's ID
     * @return  the uuid
     */
    public String getUuid() {
        return this.uuid;
    } 

    /**
     * Get summary line for the account
     * @return  the string summary for the account
     */
    public String getSummaryLine() {
        
        //get the account's balance
        double balance = this.getBalance();

        //format the summary line, depending on whether the balance is negative
        if(balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name); //positive balance
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name); //negative balance, surrounds value with ()
        }
    }
    
    /**
     * Get the balance of an account by adding the amounts of the transactions
     * @return  the account's balance value
     */
    public double getBalance() {
        double balance = 0;
        for(Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * Print the transaction history for the account
     */
    public void printTransactionHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int t=this.transactions.size()-1; t>=0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this account
     * @param amount        the amount transacted
     * @param memo          the transaction memo
     */
    public void addTransaction(double amount, String memo) {
        //create a new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

}
