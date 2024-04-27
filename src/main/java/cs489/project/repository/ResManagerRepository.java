package cs489.project.repository;

import cs489.project.model.RestaurantManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResManagerRepository extends JpaRepository<RestaurantManager, Integer> {
    List<RestaurantManager> findByResNameContainingOrAddressContainingOrEmailContainingOrPhoneNumberContaining(
            String resName, String address, String email, String phoneNumber
    );
}
