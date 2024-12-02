package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.CitiesRequestDTO;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.CitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "cities")
public class CitiesController extends AbstractController<Cities> {

    private static final Logger log = LoggerFactory.getLogger(CitiesController.class);

    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    //build save branch REST API
    @PostMapping("/City")
    public void add(@RequestBody CitiesRequestDTO request) {

        citiesService.saveCities(request);
    }

//    @DeleteMapping("/deleteCity/{id}")
//    public void deleteCity(@PathVariable String id) {
//        citiesRepository.deleteById(id);
//    }

    @Override
    protected AbstractService<Cities> getService() {
        return citiesService;
    }
}
