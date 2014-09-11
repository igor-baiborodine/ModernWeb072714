package auction.model;

import com.google.common.base.MoreObjects;

/**
 * @author Yakov Fain
 * @author Igor Baiborodine
 */
public class User {

    private int id;
    private String name;
    private String email;
    private boolean getOverbidNotifications;

    public User() {}

    public User(int id, String name, String email, boolean getOverbidNotifications) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.getOverbidNotifications = getOverbidNotifications;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isGetOverbidNotifications() {
        return getOverbidNotifications;
    }

    public void setGetOverbidNotifications(final boolean getOverbidNotifications) {
        this.getOverbidNotifications = getOverbidNotifications;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("getOverbidNotifications", getOverbidNotifications)
                .toString();
    }

    public String toShortString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

}
