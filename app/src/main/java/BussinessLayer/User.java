package BussinessLayer;

public class User {

    private String username;
    private String emailAddress;
    private String password;
    private int employersRank;
    private int seekersRank;

    public User(String username, String emailAddress, String password, int employersRank, int seekersRank) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.employersRank = employersRank;
        this.seekersRank = seekersRank;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public int getEmployersRank() {
        return employersRank;
    }

    public int getSeekersRank() {
        return seekersRank;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPasswordValidity(String password) {
        return this.password.equals(password);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmployersRank(int employersRank) {
        this.employersRank = employersRank;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSeekersRank(int seekersRank) {
        this.seekersRank = seekersRank;
    }
}
