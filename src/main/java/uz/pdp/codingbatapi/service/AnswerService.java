package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Answer;
import uz.pdp.codingbatapi.entity.StarBadge;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.payload.AnswerDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.UserDto;
import uz.pdp.codingbatapi.repository.AnswerRepository;
import uz.pdp.codingbatapi.repository.StarBadgeRepository;
import uz.pdp.codingbatapi.repository.TaskRepository;
import uz.pdp.codingbatapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;


    public List<Answer> getAnswers(){
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    public List<Answer> getAnswersByTaskId(Integer taskId){
        List<Answer> allByTask_id = answerRepository.findAllByTask_Id(taskId);
        return allByTask_id;
    }


    public Answer getAnswerById(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public Result addAnswer(AnswerDto answerDto){
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (optionalTask.isEmpty()){
            return new Result("Such task doesn't exist",false);
        }
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()){
            return new Result("Such user doesn't exist",false);
        }
        Answer answer=new Answer();
        answer.setBody(answerDto.getBody());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answer.setTrue(answerDto.isTrue());
        return new Result("New asnwer added!",true);
    }

    public Result editAnswer(Integer id,AnswerDto answerDto){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()){
            return new Result("Such Answer doesn't exist",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (optionalTask.isEmpty()){
            return new Result("Such task doesn't exist",false);
        }
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()){
            return new Result("Such user doesn't exist",false);
        }
        Answer answer = optionalAnswer.get();
        answer.setBody(answerDto.getBody());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answer.setTrue(answerDto.isTrue());
        return new Result("Answer edited added!",true);

    }

    public Result deleteAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()){
            return new Result("Such Answer doesn't exist",false);
        }
        answerRepository.deleteById(id);
        return new Result("Answer deleted",true);
    }

}
