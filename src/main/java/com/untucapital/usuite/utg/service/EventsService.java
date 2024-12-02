package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.dto.request.EventsRequestDTO;
import com.untucapital.usuite.utg.dto.response.EventsResponseDTO;
import com.untucapital.usuite.utg.model.Events;
import com.untucapital.usuite.utg.repository.EventsRepository;
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
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Transactional(value = "transactionManager")
    public List<EventsResponseDTO> getAllZones() {

        List<EventsResponseDTO> response = new ArrayList<>();
        List<Events> eventsList = eventsRepository.findAll();

        for(Events events : eventsList){

            EventsResponseDTO responseDTO = new EventsResponseDTO();
            BeanUtils.copyProperties(events, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<EventsResponseDTO> getZoneById(String id) {

        List<EventsResponseDTO> response = new ArrayList<EventsResponseDTO>();
        List<Events> eventsList = (List<Events>) eventsRepository.findEventsById(id);

        for(Events events : eventsList){

            EventsResponseDTO responseDTO = new EventsResponseDTO();
            BeanUtils.copyProperties(events, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveZones(EventsRequestDTO leadStatus) {

        Events events = new Events();
        BeanUtils.copyProperties(leadStatus, events);
        eventsRepository.save(events);
    }

    @Transactional(value = "transactionManager")
    public void deleteZones(String id) {
        eventsRepository.deleteById(id);
    }

}

