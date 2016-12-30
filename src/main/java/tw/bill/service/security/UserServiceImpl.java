package tw.bill.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tw.bill.domain.Role;
import tw.bill.domain.User;
import tw.bill.dto.UserCreateForm;
import tw.bill.respository.UserRepository;
import tw.bill.service.api.UserService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by bill33 on 2016/2/14.
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    @Override
    public void changeRole(Long userId) {
        User user = userRepository.findOne(userId);

        if(user.getRole() == Role.ADMIN)
            user.setRole(Role.USER);
        else if(user.getRole() == Role.USER)
            user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void testTransactional() {
        for(int i = 0; i < 3; i++) {
//            if(i == 1) throw new RuntimeException("");
            User user = new User();
            user.setEmail("bbb");
            user.setPasswordHash(new BCryptPasswordEncoder().encode("1234"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }
}
