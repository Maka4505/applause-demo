package com.mahlik.demo.service;

import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.entity.Tester;
import com.mahlik.demo.repository.BugRepository;
import com.mahlik.demo.repository.DeviceRepository;
import com.mahlik.demo.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class TesterSearchService {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;
    private final BugRepository bugRepository;

    public TesterSearchService(
            TesterRepository testerRepository,
            DeviceRepository deviceRepository,
            BugRepository bugRepository
    ) {
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
        this.bugRepository = bugRepository;
    }

    public List<TesterWithExperience> getTestersWithExperienceByCountriesAndDevices(Set<String> countries, Set<Long> devicesIds) {
        List<Tester> testers = testerRepository.findAllTestersByDevices_IdInAndCountryIn(devicesIds, countries);
        System.out.println("\n\ngetTestersWithExperienceByCountriesAndDevices\n");
//        countries.forEach(System.out::println);
//        devices.forEach(System.out::println);
//        testers.forEach(System.out::println);
        return testers.stream().map(tester -> {
            bugRepository.findAllByTesterIdAndDeviceIdIn(tester.getId(), devicesIds).forEach(System.out::println);//REMOVE --------------------------
            return new TesterWithExperience(
                    getTesterFullName(tester),
                    getTesterExperience(tester, devicesIds));
        }).collect(toList());
    }

    private String getTesterFullName(Tester tester) {
        return tester.getFirstName() + " " + tester.getLastName();
    }

    private int getTesterExperience(Tester tester, Set<Long> devices) {
        return bugRepository.countAllByTesterIdAndDeviceIdIn(
                tester.getId(),
                devices
        );
    }

}
