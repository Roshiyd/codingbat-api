package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.payload.ProgLangDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.service.ProgLangService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proglang")
public class ProgLangController {
    @Autowired
    ProgLangService progLangService;

    @GetMapping
    public ResponseEntity<List<ProgLang>> getProgLangs(){
        List<ProgLang> progLangList = progLangService.getProgLangList();
        return ResponseEntity.ok(progLangList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgLang> getProgLangById(@PathVariable Integer id){
        ProgLang progLangById = progLangService.getProgLangById(id);
        return ResponseEntity.ok(progLangById);
    }

    @PostMapping
    public ResponseEntity<Result> addProgLang(@Valid @RequestBody ProgLangDto progLangDto){
        Result result = progLangService.addProgLang(progLangDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editProgLang(@Valid @PathVariable Integer id,@RequestBody ProgLangDto progLangDto){
        Result result = progLangService.editProgLang(id, progLangDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteProgLang(@PathVariable Integer id){
        Result result = progLangService.deleteProgLang(id);
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
