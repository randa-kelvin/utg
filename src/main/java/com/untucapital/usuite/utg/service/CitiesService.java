package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.CitiesRequestDTO;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.CitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class CitiesService extends AbstractService<Cities> {

    private static final Logger log = LoggerFactory.getLogger(CitiesService.class);

    private final CitiesRepository citiesRepository;


    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveCities(CitiesRequestDTO request) {

        Cities cities = new Cities();
        BeanUtils.copyProperties(request, cities);

        citiesRepository.save(cities);
    }

    @Override
    protected JpaRepository<Cities, String> getRepository() {
        return citiesRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }
}
