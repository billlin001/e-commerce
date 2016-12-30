package tw.bill.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.bill.domain.Order;
import tw.bill.dto.OrderDto;

import java.util.List;

/**
 * Created by bill33 on 2016/10/16.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByToken(String orderToken);

    List<Order> findByUserId(Long id);

    List<Order> findAllByOrderByIdDesc();
}
