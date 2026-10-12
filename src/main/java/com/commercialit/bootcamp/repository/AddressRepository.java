package com.commercialit.bootcamp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.commercialit.bootcamp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomerIdOrderByDefaultAddressDesc(Long customerId);
}
