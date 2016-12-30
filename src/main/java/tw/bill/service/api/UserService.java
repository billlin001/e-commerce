package tw.bill.service.api;

import tw.bill.domain.User;
import tw.bill.dto.UserCreateForm;

import java.util.Collection;

/**
 * Created by bill33 on 2016/2/14.
 */
public interface UserService {
    User getUserById(long id);

    User getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    void changeRole(Long userId);

    void testTransactional();
}

