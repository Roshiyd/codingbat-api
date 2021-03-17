package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.TaskDto;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        Task taskById = taskService.getTaskById(id);
        return ResponseEntity.ok(taskById);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Task>> getCategories(@PathVariable Integer categoryId){
        List<Task> tasksByCategoryId = taskService.getTasksByCategoryId(categoryId);
        return ResponseEntity.ok(tasksByCategoryId);
    }

    @PostMapping
    public ResponseEntity<Result> addTask(@Valid @RequestBody TaskDto taskDto){
        Result result = taskService.addTask(taskDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editTask(@Valid @PathVariable Integer id,@RequestBody TaskDto taskDto){
        Result result = taskService.editTask(id, taskDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteTask(@PathVariable Integer id){
        Result result = taskService.deleteTask(id);
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
