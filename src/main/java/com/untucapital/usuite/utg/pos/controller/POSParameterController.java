package com.untucapital.usuite.utg.pos.controller;

import com.untucapital.usuite.utg.pos.dto.POSParameterDto;
import com.untucapital.usuite.utg.pos.model.POSParameter;
import com.untucapital.usuite.utg.pos.service.POSParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pos/parameter")
@Component("posParameterController")
@RequiredArgsConstructor
public class POSParameterController {
    private final POSParameterService posParameterService;

    //Save Parameter
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public POSParameter saveParameter(@RequestBody POSParameter posParameter) {
        return posParameterService.saveParameter(posParameter);
    }
    //Get Parameter By Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSParameter getParameterById(@PathVariable Integer id) {
        return posParameterService.getParameterById(id);
    }
    //Get all Parameter
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<POSParameter> getAllParameters() {
        return posParameterService.getAllParameters();
    }
    //Update Parameter
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public POSParameter updateCategory(@RequestBody POSParameter posParameter) {
        return posParameterService.updateParameter(posParameter);
    }
    //Delete Parameter
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteParameter(@PathVariable Integer id) {
        return posParameterService.deleteParameter(id);
    }

}
