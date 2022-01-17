import java.util.ArrayList;
import java.util.Random;

public class Bank {
    
    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Generate a universally unique ID for a user
     * @return the uuid
     */
    public String getNewUserUUID() {

        //initialise
        String uuid;
        Random rng = new Random();
        int length = 6;
        boolean nonUnique;

        //continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < length; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //check to make sure it's unqiue
            nonUnique = false;
            for(User u : this.users) {
                if(uuid.compareTo(u.getUuid()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;
    }

    /**
     * Generate a new univerally unique ID for an account
     * @return the uuid
     */
    public String getNewAccountUUID() {
        
        //initialise
        String uuid;
        Random rng = new Random();
        int length = 10;
        boolean nonUnique;

        //continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < length; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //check to make sure it's unqiue
            nonUnique = false;
            for(Account a : this.accounts) {
                if(uuid.compareTo(a.getUuid()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;
    }

    /**
     * Add an account
     * @param anAcct    the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user for the bank
     * @param firstName     the user's first name
     * @param lastName      the user's last name
     * @param pin           the user's pin
     * @return              the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {
        //create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user and add to User and Bank accounts list
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the User object associated with a particular userId and pin, if they are valid
     * @param userId    the UUID of the user to login
     * @param pin       the pin of the user
     * @return          the User object, if the login is successful, or null, if it is not
     */
    public User userLogin(String userId, String pin) {
        //search through list of users
        for(User u : this.users) {
            
            //check UUID is correct
            if(u.getUuid().compareTo(userId) == 0 && u.validatePin(pin)) {
                return u;
            }

        }

        //if we haven't found correct user or have an incorrect pin
        return null;
    }

}
