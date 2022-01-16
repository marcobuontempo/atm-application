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


}
