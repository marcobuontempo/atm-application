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


}
