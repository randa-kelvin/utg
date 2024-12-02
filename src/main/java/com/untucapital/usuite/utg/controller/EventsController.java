package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.EventsRequestDTO;
import com.untucapital.usuite.utg.dto.response.EventsResponseDTO;
import com.untucapital.usuite.utg.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    EventsService eventsService;

    @GetMapping()
    public List<EventsResponseDTO> list() {
        return eventsService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventsResponseDTO> get(@PathVariable String id) {
        try {
            EventsResponseDTO events = (EventsResponseDTO) eventsService.getZoneById(id);
            return new ResponseEntity<EventsResponseDTO>(events, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<EventsResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEvents")
    public void add(@RequestBody EventsRequestDTO events) {
        eventsService.saveZones(events);
    }

    @DeleteMapping("/deleteEvents/{id}")
    public void delete(@PathVariable String id) {
        eventsService.deleteZones(id);
    }
}
