package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.Targets;
import com.untucapital.usuite.utg.model.Zones;
import com.untucapital.usuite.utg.repository.TargetsRepository;
import com.untucapital.usuite.utg.repository.ZonesRepository;
import com.untucapital.usuite.utg.service.TargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/targets")
public class TargetsController {
    @Autowired
    TargetsService targetsService;
    @Autowired
    TargetsRepository targetsRepository;
    @GetMapping()
    public List<Targets> list() {
        return targetsService.getAllTargets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Targets> get(@PathVariable String id) {
        try {
            Targets targets = (Targets) targetsService.getTargetById(id);
            return new ResponseEntity<Targets>(targets, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Targets>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("getTargetById/{id}")
//    public ResponseEntity<Targets> getTargetsById(@PathVariable("id") String id) {
//        return new ResponseEntity<Targets>(targetsService.getTarget(id), HttpStatus.OK);
//    }

    @GetMapping("getTargetById/{id}")
    public ResponseEntity<Targets> getTargetById(@PathVariable("id") String id) {
        Targets target = targetsService.getTargetById(id);
        if (target != null) {
            return new ResponseEntity<>(target, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatetargets(@PathVariable String id, @RequestBody Targets targets) {
        Targets updatedtargets = targetsRepository.findTargetsById(id);

        if (updatedtargets != null) {
            updatedtargets.setBranch(targets.getBranch());
            updatedtargets.setTarget(targets.getTarget());

            targetsRepository.save(updatedtargets);
            return new ResponseEntity<>("Target successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Target not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addTarget")
    public void add(@RequestBody Targets targets) {
        targetsService.saveTargets(targets);
    }


    @DeleteMapping("/deleteTargets/{id}")
    public void delete(@PathVariable String id) {
        targetsService.deleteTargets(id);
    }
}
