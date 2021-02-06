package com.mahlik.demo.controller;

import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.service.TesterSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TesterSearchController {

    private final TesterSearchService testerSearchService;

    public TesterSearchController(TesterSearchService testerSearchService) {
        this.testerSearchService = testerSearchService;
    }

    @GetMapping("/testersWithExperience")
    @ResponseBody
    public List<TesterWithExperience> getTestersWithExperience(
            @RequestParam(required = false) Optional<Set<String>> countries,
            @RequestParam(required = false) Optional<Set<Long>> devices
    ) {
        return testerSearchService.getTestersWithExperienceByProvidedCriteria(countries, devices);
    }

}
