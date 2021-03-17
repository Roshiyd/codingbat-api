package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.StarBadge;
import uz.pdp.codingbatapi.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByCategory_Id(Integer category_id);
}
