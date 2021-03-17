package uz.pdp.codingbatapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.ProgLang;
import uz.pdp.codingbatapi.payload.ProgLangDto;
import uz.pdp.codingbatapi.payload.Result;
import uz.pdp.codingbatapi.repository.ProgLangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgLangService {
    @Autowired
    ProgLangRepository progLangRepository;

    public List<ProgLang> getProgLangList(){
        List<ProgLang> progLangList = progLangRepository.findAll();
        return progLangList;
    }

    public ProgLang getProgLangById(Integer id){
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(id);
        return optionalProgLang.orElse(null);
    }

    public Result addProgLang(ProgLangDto progLangDto){
        ProgLang progLang=new ProgLang();
        progLang.setName(progLangDto.getName());
        progLang.setDescription(progLangDto.getDescription());
        progLangRepository.save(progLang);
        return new Result("New programming language added!",true);
    }

    public Result editProgLang(Integer id,ProgLangDto progLangDto){
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(id);
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programming language doesn't exist",false);
        }
        ProgLang progLang = optionalProgLang.get();
        progLang.setName(progLangDto.getName());
        progLang.setDescription(progLangDto.getDescription());
        progLangRepository.save(progLang);
        return new Result("New programming language added!",true);
    }

    public Result deleteProgLang(Integer id){
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(id);
        if (optionalProgLang.isEmpty()){
            return new Result("Such Programing language doesn't exist",false);
        }
        progLangRepository.deleteById(id);
        return new Result("Programming language deleted",true);
    }

}
