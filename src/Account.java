import java.util.ArrayList;

public class Account {
    
    //name of account
    private String name;

    //current balance of account
    private double balance;

    //account ID number
    private String uuid;

    //User object that holds the account
    private User holder;

    //list of transactions for the account
    private ArrayList<Transaction> transactions;

}
