package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.repository.IndustryRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.IndustryService;
import com.untucapital.usuite.utg.untu_capital.response.GlobalImageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "industries")
public class IndustryController extends AbstractController<Industry> {
    @Autowired
    IndustryRepository industryRepository;
    private static final Logger log = LoggerFactory.getLogger(IndustryController.class);

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping("getIndustryById/{id}")
    public ResponseEntity<Industry> getIndustryById(@PathVariable("id") String id) {
        Industry industry = industryService.getIndustryById(id);
        if (industry != null) {
            return new ResponseEntity<>(industry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> industrytargets(@PathVariable String id, @RequestBody Industry industry) {
        Industry updatedindustry = industryRepository.findIndustriesById(id);

        if (updatedindustry != null) {
            updatedindustry.setName(industry.getName());
            updatedindustry.setSubSector(industry.getSubSector());

            industryRepository.save(updatedindustry);
            return new ResponseEntity<>("Industries successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Industries not found.", HttpStatus.NOT_FOUND);
        }
    }
    //Add Industry and Related industry image
    @PostMapping("/save")
    ResponseEntity<GlobalImageResponse> addIndustry(@RequestParam(name = "file", required = false) MultipartFile file,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("code") int code
    ){
        String fileName = industryService.addIndustry(file,name,code);
        GlobalImageResponse globalImageResponse = new GlobalImageResponse(fileName);
        return ResponseEntity.ok().body(globalImageResponse);
    }
    @Override
    protected AbstractService<Industry> getService() {
        return industryService;
    }
}
