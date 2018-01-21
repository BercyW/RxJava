package security.bercy.com.week4day2rxjava.model;

/**
 * Created by Bercy on 1/21/18.
 */

public class ApiUser {
    public long id;
    public String firstname;
    public String lastname;

    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
