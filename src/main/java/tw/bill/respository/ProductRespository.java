package tw.bill.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.bill.domain.Product;

import java.util.List;

/**
 * Created by bill33 on 2016/9/11.
 */
@Repository
public interface ProductRespository  extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
