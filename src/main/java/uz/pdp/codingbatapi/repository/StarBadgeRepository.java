package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.StarBadge;

public interface StarBadgeRepository extends JpaRepository<StarBadge,Integer> {

}
