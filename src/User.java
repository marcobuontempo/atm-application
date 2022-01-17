import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    
    //first name of user
    private String firstName;

    //last name of user
    private String lastName;

    //ID number of user
    private String uuid;

    //MD5 pin-hash of user's pin number
    private byte pinHash[];

    //list of accounts for this user
    private ArrayList<Account> accounts;

    /**
     * Create a new user
     * @param firstName     the user's first name
     * @param lastName      the user's last name
     * @param pin           the user's account pin number
     * @param theBank       the Bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {
        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MD5 hash, rather than original value, for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught "+e);
            e.printStackTrace();
            System.exit(1);
        }

        //get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();
        
        //print log message
        System.out.printf("New user %s, %s with ID %s %s created. \n", lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     * @param anAccount     the account to add
     */
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    /**
     * Return the user's ID
     * @return  the uuid
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true User pin
     * @param aPin      the pin to check
     * @return          whether the pin is valid or not
     */
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash)
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught "+e);
            e.printStackTrace();
            System.exit(1);
        }
    }

}
