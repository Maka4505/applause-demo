package com.mahlik.demo.service;

import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.entity.Tester;
import com.mahlik.demo.repository.BugRepository;
import com.mahlik.demo.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
public class TesterSearchService {

    private final TesterRepository testerRepository;
    private final BugRepository bugRepository;
    private final Comparator<TesterWithExperience> sortDescByExperience = (TesterWithExperience t1, TesterWithExperience t2)
            -> -Integer.compare(t1.getExperience(), t2.getExperience());

    public TesterSearchService(
            TesterRepository testerRepository,
            BugRepository bugRepository
    ) {
        this.testerRepository = testerRepository;
        this.bugRepository = bugRepository;
    }

    public List<TesterWithExperience> getTestersWithExperienceByProvidedCriteria(Optional<Set<String>> countries, Optional<Set<Long>> devicesIds) {
        List<Tester> testers = emptyList();

        if (countries.isPresent() && devicesIds.isPresent()) {
            testers = testerRepository.findAllTestersByDevices_IdInAndCountryIn(devicesIds.get(), countries.get());
        } else if (devicesIds.isPresent()) {
            testers = testerRepository.findAllTestersByDevices_IdIn(devicesIds.get());
        } else if (countries.isPresent()) {
            testers = testerRepository.findAllTestersByCountryIn(countries.get());
        }

        return prepareTestersWithExperience(testers, devicesIds);
    }


    private List<TesterWithExperience> prepareTestersWithExperience(List<Tester> testers, Optional<Set<Long>> devicesIds) {
        return testers.stream()
                .map(tester -> new TesterWithExperience(
                        getTesterFullName(tester),
                        getTesterExperience(tester, devicesIds))
                )
                .sorted(sortDescByExperience)
                .collect(toList());
    }

    private String getTesterFullName(Tester tester) {
        return tester.getFirstName() + " " + tester.getLastName();
    }

    private int getTesterExperience(Tester tester, Optional<Set<Long>> devicesIds) {
        return devicesIds.map(deviceIds -> bugRepository.countAllByTesterIdAndDeviceIdIn(
                tester.getId(),
                deviceIds
        )).orElseGet(() -> bugRepository.countAllByTesterId(
                tester.getId()
        ));
    }

}
