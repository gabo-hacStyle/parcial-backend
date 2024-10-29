package demo.securityapp.services;

import demo.securityapp.models.TypeCarEntity;
import demo.securityapp.repository.TypeCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCarServices {
    @Autowired
    private TypeCarRepository typeCarRepository;

    public List<TypeCarEntity> getAllTypeCars(){
        return typeCarRepository.findAll();
    }

}
