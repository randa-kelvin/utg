package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.dto.request.ZonesRequestDTO;
import com.untucapital.usuite.utg.dto.response.ZonesResponseDTO;
import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.repository.ZonesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ZonesService {

    @Autowired
    private ZonesRepository zonesRepository;

    @Transactional(value = "transactionManager")
    public List<Zones> getAllZones() {
        return zonesRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public List<ZonesResponseDTO> getZoneById(String id) {

        List<ZonesResponseDTO> response = new ArrayList<ZonesResponseDTO>();
        List<Zones> zonesList = (List<Zones>) zonesRepository.findZonesById(id);

        for (Zones zone : zonesList) {
            ZonesResponseDTO responseDTO = new ZonesResponseDTO();
            BeanUtils.copyProperties(zone, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveZones(ZonesRequestDTO request) {

        Zones zones = new Zones();
        BeanUtils.copyProperties(request, zones);
        zonesRepository.save(zones);
    }

    @Transactional(value = "transactionManager")
    public void deleteZones(String id) {
        zonesRepository.deleteById(id);
    }
}

