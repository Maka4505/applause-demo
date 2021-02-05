package com.mahlik.demo.controller;

import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.service.TesterSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TesterController {

    private final TesterSearchService testerSearchService;

    public TesterController(TesterSearchService testerSearchService) {
        this.testerSearchService = testerSearchService;
    }

    @GetMapping("/testersWithExperience")
    @ResponseBody
    public List<TesterWithExperience> getTestersWithExperience(
            @RequestParam Set<String> countries,
            @RequestParam Set<Long> devices
    ) {
        return testerSearchService.getTestersWithExperienceByCountriesAndDevices(countries, devices);
    }

}
