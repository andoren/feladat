package core.model;

import java.util.Collection;

public class UserAndAdress {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    private User user;
    private Collection<Address> addresses;
}
