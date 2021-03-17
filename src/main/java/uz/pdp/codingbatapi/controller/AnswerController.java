package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Answer;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.payload.AnswerDto;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.service.AnswerService;
import uz.pdp.codingbatapi.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers(){
        List<Answer> answers = answerService.getAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Integer id){
        Answer answerById = answerService.getAnswerById(id);
        return ResponseEntity.ok(answerById);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<Answer>> getAnswersByTaskId(@PathVariable Integer taskId){
        List<Answer> answersByTaskId = answerService.getAnswersByTaskId(taskId);
        return ResponseEntity.ok(answersByTaskId);
    }


    @PostMapping
    public ResponseEntity<Result> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        Result result = answerService.addAnswer(answerDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editAnswer(@Valid @PathVariable Integer id,@RequestBody AnswerDto answerDto){
        Result result = answerService.editAnswer(id, answerDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAnswer(@PathVariable Integer id){
        Result result = answerService.deleteAnswer(id);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
