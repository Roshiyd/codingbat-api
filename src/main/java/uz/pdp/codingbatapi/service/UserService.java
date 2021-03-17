package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.entity.StarBadge;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.UserDto;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.ProgLangRepository;
import uz.pdp.codingbatapi.repository.StarBadgeRepository;
import uz.pdp.codingbatapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }


    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Result addUser(UserDto userDto){
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (userRepository.existsByEmail(userDto.getEmail())){
            return new Result("Such email already exist",false);
        }
        if (optionalStarBadge.isEmpty()){
            return new Result("Such StarBadge doesn't exist",false);
        }
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setStarBadge(optionalStarBadge.get());
        userRepository.save(user);
        return new Result("New user added!",true);
    }

    public Result editUser(Integer id,UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new Result("Such User doesn't exist",false);
        }
        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(), id)) {
            return new Result("Such email already exist",false);
        }
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (optionalStarBadge.isEmpty()){
            return new Result("Such StarBadge doesn't exist",false);
        }
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setStarBadge(optionalStarBadge.get());
        userRepository.save(user);
        return new Result("User edited!",true);


    }

    public Result deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new Result("Such User doesn't exist",false);
        }
        userRepository.deleteById(id);
        return new Result("User deleted",true);
    }

}
