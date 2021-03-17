package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.ProgLangDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.ProgLangService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryById);
    }

    @GetMapping("/{progLangId}")
    public ResponseEntity<List<Category>> getCategories(@PathVariable Integer progLangId){
        List<Category> categoriesByProgLang = categoryService.getCategoriesByProgLang(progLangId);
        return ResponseEntity.ok(categoriesByProgLang);
    }

    @PostMapping
    public ResponseEntity<Result> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editCategory(@Valid @PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(id, categoryDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
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
