package banque3.banqueTest3.repositories;

import banque3.banqueTest3.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepositorie extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c  where c.name like :kw")
    List<Customer> findCustomerByName(String kw) ;



}
