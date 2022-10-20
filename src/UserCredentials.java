import Types.UserType;

public class UserCredentials{

    private String name;
    private String username;
    private String password;

    private UserType userType;

    UserCredentials(String name, String username, String password, UserType userType){
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "Hello, " + name + "!";
    }
}
