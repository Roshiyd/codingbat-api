package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.ProgLangDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.ProgLangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    ProgLangRepository progLangRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public List<Category> getCategoriesByProgLang(Integer progLangId){
        List<Category> allByProgLang_id = categoryRepository.findAllByProgLang_Id(progLangId);
        return allByProgLang_id;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public Result addCategory(CategoryDto categoryDto){
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(categoryDto.getProgLangId());
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programming language doesn't exist",false);
        }
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setProgLang(optionalProgLang.get());
        categoryRepository.save(category);
        return new Result("New category added!",true);
    }

    public Result editCategory(Integer id,CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            return new Result("Such Category doesn't exist",false);
        }
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(categoryDto.getProgLangId());
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programming language doesn't exist",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setProgLang(optionalProgLang.get());
        categoryRepository.save(category);
        return new Result("category edited!",true);

    }

    public Result deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            return new Result("Such Category doesn't exist",false);
        }
        categoryRepository.deleteById(id);
        return new Result("Category deleted",true);
    }

}
