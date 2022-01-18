import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        
        //initialise scanner
        Scanner sc = new Scanner(System.in);

        //initialise bank
        Bank theBank = new Bank("Bank of Drausin");

        //add a user, which also creates a savings account
        User aUser = theBank.addUser("John", "Doe", "1234");
        
        //add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        //
        User curUser;
        while(true) {
            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }

    }

    /**
     * Print the ATM's login menu
     * @param theBank       the Bank object of whose accounts to use
     * @param sc            the scanner object to use for user input
     * @return              the authenticated user object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        //initialise
        String userId;
        String pin;
        User authUser;

        //prompt the user for user id/pin combo until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userId = sc.nextLine();
            System.out.print("Enter PIN: ");
            pin = sc.nextLine();

            //try to get the user object corresponding to the id/pin combo
            authUser = theBank.userLogin(userId, pin);
            if(authUser == null) {
                System.out.println("Incorrect User ID or PIN combination. " + 
                "Please try again.");
            }
        } while(authUser == null); //continue looping until successful login

        return authUser;
    }


    public static void printUserMenu(User theUser, Scanner sc) {
        //print a summary of the user's accounts
        theUser.printAccountSummary();

        //initialise
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
            System.out.println("  1) Show the transaction history");
            System.out.println("  2) Withdrawal");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println();
            System.out.print("Enter choice (1-5): ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5.");
            }

        } while(choice < 1 || choice > 5);

        //process the choice
        switch(choice) {
        
        case 1:
            ATM.showTransactionHistory(theUser, sc);
            break;
        case 2:
            ATM.withdrawalFunds(theUser, sc);
            break;
        case 3:
            ATM.depositFunds(theUser, sc);
            break;
        case 4:
            ATM.transferFunds(theUser, sc);
            break;
        }

        //redisplay this menu unless the user wants to quit
        if(choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    public static void showTransactionHistory(User theUser, Scanner sc) {
        int theAcct;

        //get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account"+"whose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if(theAcct<0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct<0 || theAcct >= theUser.numAccounts());

        //print transaction history
        theUser.printAccountTransactionHistory(theAcct);
    }

}
