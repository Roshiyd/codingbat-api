package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Answer;
import uz.pdp.codingbatapi.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByProgLang_Id(Integer progLang_id);
}
