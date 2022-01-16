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

        //add to holder and bank lists
        holder.addAccount(this);
        theBank.addAccount(this);
    }

    /**
     * Return the user's ID
     * @return  the uuid
     */
    public String getUuid() {
        return this.uuid;
    } 
    

}
