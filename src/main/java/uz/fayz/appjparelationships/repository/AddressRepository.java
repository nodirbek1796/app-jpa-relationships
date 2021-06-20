package uz.fayz.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fayz.appjparelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
