package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.pos.model.POSParameter;
import com.untucapital.usuite.utg.pos.repository.POSParameterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class POSParameterService {
    private final POSParameterRepository posParameterRepository;
    //save parameter
    @Transactional(value = "transactionManager")
    public POSParameter saveParameter(POSParameter posParameter){
        return posParameterRepository.save(posParameter);
    }
    //get parameter by id
    @Transactional(value = "transactionManager")
    public POSParameter getParameterById(Integer parameterId){
        return posParameterRepository.findById(parameterId).orElse(null);
    }
    //get all parameters
    @Transactional(value = "transactionManager")
    public List<POSParameter> getAllParameters(){
        return posParameterRepository.findAll();
    }
    //update parameter
    @Transactional(value = "transactionManager")
    public POSParameter updateParameter(POSParameter posParameter){
        POSParameter existingParameter = posParameterRepository.findById(posParameter.getId()).orElse(null);

        assert existingParameter != null;
        existingParameter.setTax(posParameter.getTax());
        existingParameter.setCumulative(posParameter.getCumulative());

        return posParameterRepository.save(existingParameter);
    }
    //delete parameter
    @Transactional(value = "transactionManager")
    public String deleteParameter(Integer parameterId){
        POSParameter exist = posParameterRepository.findById(parameterId).orElse(null);

        if(exist == null){
            return "Category does not exist";
        }

        posParameterRepository.delete(exist);
        return "Category deleted successfully";
    }
}
