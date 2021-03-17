package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.TaskDto;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.ProgLangRepository;
import uz.pdp.codingbatapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public List<Task> getTasksByCategoryId(Integer categoryId){
        List<Task> allByCategory_id = taskRepository.findAllByCategory_Id(categoryId);
        return allByCategory_id;
    }

    public Task getTaskById(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public Result addTask(TaskDto taskDto){
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new Result("Such Category doesn't exist",false);
        }
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setExamples(taskDto.getExamples());
        task.setHasStar(task.isHasStar());
        task.setSolution(task.getSolution());
        task.setText(taskDto.getText());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);
        return new Result("New task added!",true);
    }

    public Result editTask(Integer id,TaskDto taskDto){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            return new Result("Such Task doesn't exist",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new Result("Such Category doesn't exist",false);
        }
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setExamples(taskDto.getExamples());
        task.setHasStar(task.isHasStar());
        task.setSolution(task.getSolution());
        task.setText(taskDto.getText());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);
        return new Result("task edited!",true);

    }

    public Result deleteTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            return new Result("Such Task doesn't exist",false);
        }
        taskRepository.deleteById(id);
        return new Result("Task deleted",true);
    }

}
