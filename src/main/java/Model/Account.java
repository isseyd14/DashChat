package Model;

public class Account {
    // Fields
    private String accountId;
    private String username;
    private String email;
    private String password;
    private String firstName;  // Added first name field
    private String lastName;   // Added last name field

    // Constructors
    public Account(String AccountId, String Username, String Password, String Email, String FirstName, String LastName) {
        this.accountId = AccountId;
        this.username = Username;
        this.password = Password;
        this.email = Email;
        this.firstName = FirstName;
        this.lastName = LastName;
    }

    // Getter and Setter methods
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
