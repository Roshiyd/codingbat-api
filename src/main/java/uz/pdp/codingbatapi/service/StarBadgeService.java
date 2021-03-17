package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.entity.StarBadge;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.payload.StarBadgeDto;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.ProgLangRepository;
import uz.pdp.codingbatapi.repository.StarBadgeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    ProgLangRepository progLangRepository;

    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<StarBadge> getStarBadges(){
        List<StarBadge> starBadgeList = starBadgeRepository.findAll();
        return starBadgeList;
    }


    public StarBadge getStarBadgeById(Integer id){
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        return optionalStarBadge.orElse(null);
    }

    public Result addStarBadge(StarBadgeDto starBadgeDto){
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(starBadgeDto.getProgLangId());
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programming language doesn't exist",false);
        }

        StarBadge starBadge=new StarBadge();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(optionalProgLang.get());
        starBadgeRepository.save(starBadge);
        return new Result("New starBadge added!",true);
    }

    public Result editStarBadge(Integer id,StarBadgeDto starBadgeDto){
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (optionalStarBadge.isEmpty()){
            return new Result("Such starBadge doesn't exist",false);
        }
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(starBadgeDto.getProgLangId());
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programming language doesn't exist",false);
        }
        StarBadge starBadge = optionalStarBadge.get();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(optionalProgLang.get());
        starBadgeRepository.save(starBadge);
        return new Result("starBadge edited!",true);
    }

    public Result deleteCategory(Integer id){
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (optionalStarBadge.isEmpty()){
            return new Result("Such StarBadge doesn't exist",false);
        }
        starBadgeRepository.deleteById(id);
        return new Result("StarBadge deleted",true);
    }

}
