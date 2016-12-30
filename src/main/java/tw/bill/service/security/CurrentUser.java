package tw.bill.service.security;

import org.springframework.security.core.authority.AuthorityUtils;
import tw.bill.domain.Role;
import tw.bill.domain.User;

/**
 * Created by bill33 on 2016/9/26.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

}