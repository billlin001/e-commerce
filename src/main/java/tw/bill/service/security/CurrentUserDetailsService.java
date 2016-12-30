package tw.bill.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tw.bill.domain.User;
import tw.bill.service.api.UserService;

/**
 * Created by bill33 on 2016/9/26.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        return new CurrentUser(user);
    }
}


