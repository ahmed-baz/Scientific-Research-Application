package oracle.adf.research.model.bc.classes;
import oracle.jbo.domain.Number;
public class User {
    private Number userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String active;
    private String roleName;

    public void setUserId(Number userId) {
        this.userId = userId;
    }

    public Number getUserId() {
        return userId;
    }


    public User(Number userId, String userFirstName, String userLastName, String userEmail, String active,
                String roleName) {
        super();
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.active = active;
        this.roleName = roleName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public User() {
        super();
    }
}