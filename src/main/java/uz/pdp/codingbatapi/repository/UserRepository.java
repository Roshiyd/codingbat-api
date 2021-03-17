package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
