import java.io.Serializable; //import the serializeable class

public class user implements Serializable
{
    private String username;
    private String password;

    user(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Username: " + username + " Password: " + password;
    }
}