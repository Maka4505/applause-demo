package com.mahlik.demo.controller;

import com.mahlik.demo.dto.DropdownListItem;
import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.service.TesterSearchCriteriaService;
import com.mahlik.demo.service.TesterSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TesterSearchController {

    private final TesterSearchService testerSearchService;
    private final TesterSearchCriteriaService testerSearchCriteriaService;

    public TesterSearchController(
            TesterSearchService testerSearchService,
            TesterSearchCriteriaService testerSearchCriteriaService
    ) {
        this.testerSearchService = testerSearchService;
        this.testerSearchCriteriaService = testerSearchCriteriaService;
    }


    // It is worth to consider using Cache when amount of data increases and number of concurrent users is greater than one :)


    // Searches testers when at least one criteria is set
    // If the user was not interested in criteria it is treated as `ALL` selection
    // (e.g. the user does not care about the testers country, only the device he uses)
    // When the number of criteria increases it will allow for less clicking
    // (no need to click dropdowns arrow and then selecting `ALL` checkbox for every criteria)
    @GetMapping("/testersWithExperience")
    @ResponseBody
    public List<TesterWithExperience> getTestersWithExperience(
            @RequestParam(required = false) Optional<Set<String>> countries,
            @RequestParam(required = false) Optional<Set<Long>> devices
    ) {
        return testerSearchService.getTestersWithExperienceByProvidedCriteria(countries, devices);
    }

    @GetMapping("/availableDevices")
    @ResponseBody
    public List<DropdownListItem> getAvailableDevices() {
        return testerSearchCriteriaService.getAvailableDeviceOptions();
    }

    @GetMapping("/availableCountries")
    @ResponseBody
    public List<DropdownListItem> getAvailableCountries() {
        return testerSearchCriteriaService.getAvailableCountryOptions();
    }

}
