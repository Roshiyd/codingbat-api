package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.StarBadge;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.StarBadgeDto;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.StarBadgeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/starBadge")
public class StarBadgeController {
    @Autowired
    StarBadgeService starBadgeService;

    @GetMapping
    public ResponseEntity<List<StarBadge>> getStarBadges(){
        List<StarBadge> starBadges = starBadgeService.getStarBadges();
        return ResponseEntity.ok(starBadges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarBadge> getStarBadgeById(@PathVariable Integer id){
        StarBadge starBadgeById = starBadgeService.getStarBadgeById(id);
        return ResponseEntity.ok(starBadgeById);
    }

    @PostMapping
    public ResponseEntity<Result> addStarBadges(@Valid @RequestBody StarBadgeDto starBadgeDto){
        Result result = starBadgeService.addStarBadge(starBadgeDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editStarBadge(@Valid @PathVariable Integer id,@RequestBody StarBadgeDto starBadgeDto){
        Result result = starBadgeService.editStarBadge(id, starBadgeDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteStarBadge(@PathVariable Integer id){
        Result result = starBadgeService.deleteCategory(id);
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
