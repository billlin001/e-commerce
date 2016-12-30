package tw.bill.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.bill.domain.User;

/**
 * Created by bill33 on 2016/2/14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
}