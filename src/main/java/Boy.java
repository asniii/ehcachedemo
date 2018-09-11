import java.util.List;

public class Boy {

    String firstname;
    String lastname;
    List<String> friends;

    public Boy(String firstname, String lastname, List<String> girlfriends) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.friends = girlfriends;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
