package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Answer;
import uz.pdp.codingbatapi.entity.ProgLang;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findAllByTask_Id(Integer task_id);
}
