package dev.group3.models;

public class UserAndPassword {
    private String username;
    private String password;

    public UserAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAndPassword() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAndPassword{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
