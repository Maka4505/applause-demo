package service;

import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.entity.Device;
import com.mahlik.demo.entity.Tester;
import com.mahlik.demo.repository.BugRepository;
import com.mahlik.demo.repository.TesterRepository;
import com.mahlik.demo.service.TesterSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static helper.TesterSearchTestsHelper.assertTesterWithExperienceHasCorrectValues;
import static helper.TesterSearchTestsHelper.assertTestersWithExperienceAreInCorrectOrder;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TesterSearchServiceTest {

    @InjectMocks
    TesterSearchService testerSearchService;

    @Mock
    TesterRepository testerRepository;

    @Mock
    BugRepository bugRepository;

    Optional<Set<String>> countriesOptional = Optional.of(new HashSet<String>() {{
        add("GB");
        add("US");
    }});
    Optional<Set<Long>> deviceIdsOptional = Optional.of(new HashSet<Long>() {{
        add(1L);
        add(2L);
        add(3L);
    }});
    List<Device> devices = Arrays.asList(
            new Device().setId(1L).setDescription("iPhone 4"),
            new Device().setId(2L).setDescription("iPhone 4S"),
            new Device().setId(3L).setDescription("iPhone 5")
    );
    List<Tester> testers = asList(
            createTester(1L, "GB", "Jan", "Kowalski",
                    devices.get(0),
                    devices.get(1)
            ),
            createTester(2L, "US", "Janko", "Walski",
                    devices.get(0),
                    devices.get(2)
            )
    );

    @Test
    public void testGetTestersWithExperienceByProvidedCriteria_noCriteriaSet_noResultsFound() {
        // given

        // when
        List<TesterWithExperience> testersWithExperience = testerSearchService.getTestersWithExperienceByProvidedCriteria(
                Optional.empty(),
                Optional.empty()
        );
        // then
        assertThat(testersWithExperience).isEmpty();
    }

    @Test
    public void testGetTestersWithExperienceByProvidedCriteria_onlyDeviceIdsSet_returnsCorrectTestersInOrder() {
        // given
        Set<Long> devicesIds = deviceIdsOptional.orElse(emptySet());
        when(testerRepository.findDistinctTestersByDevices_IdIn(devicesIds)).thenReturn(testers);
        when(bugRepository.countDistinctByTesterIdAndDeviceIdIn(1L, devicesIds)).thenReturn(5);
        when(bugRepository.countDistinctByTesterIdAndDeviceIdIn(2L, devicesIds)).thenReturn(7);
        // when
        List<TesterWithExperience> testersWithExperience = testerSearchService.getTestersWithExperienceByProvidedCriteria(
                Optional.empty(),
                deviceIdsOptional
        );
        // then
        assertThat(testersWithExperience.size()).isEqualTo(2);
        assertTestersWithExperienceAreInCorrectOrder(testersWithExperience);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(0), "Janko Walski", 7);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(1), "Jan Kowalski", 5);
    }

    @Test
    public void testGetTestersWithExperienceByProvidedCriteria_onlyCountriesSet_returnsCorrectTestersInOrder() {
        // given
        Set<String> countries = countriesOptional.orElse(emptySet());
        when(testerRepository.findDistinctTestersByCountryIn(countries)).thenReturn(testers);
        when(bugRepository.countDistinctByTesterId(1L)).thenReturn(5);
        when(bugRepository.countDistinctByTesterId(2L)).thenReturn(7);
        // when
        List<TesterWithExperience> testersWithExperience = testerSearchService.getTestersWithExperienceByProvidedCriteria(
                countriesOptional,
                Optional.empty()
        );
        // then
        assertThat(testersWithExperience.size()).isEqualTo(2);
        assertTestersWithExperienceAreInCorrectOrder(testersWithExperience);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(0), "Janko Walski", 7);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(1), "Jan Kowalski", 5);
    }

    @Test
    public void testGetTestersWithExperienceByProvidedCriteria_allCriteriaSet_returnsCorrectTestersInOrder() {
        // given
        Set<String> countries = countriesOptional.orElse(emptySet());
        Set<Long> devicesIds = deviceIdsOptional.orElse(emptySet());
        when(testerRepository.findDistinctTestersByCountryInAndDevices_IdIn(countries, devicesIds)).thenReturn(testers);
        when(bugRepository.countDistinctByTesterIdAndDeviceIdIn(1L, devicesIds)).thenReturn(5);
        when(bugRepository.countDistinctByTesterIdAndDeviceIdIn(2L, devicesIds)).thenReturn(7);
        // when
        List<TesterWithExperience> testersWithExperience = testerSearchService.getTestersWithExperienceByProvidedCriteria(
                countriesOptional,
                deviceIdsOptional
        );
        // then
        assertThat(testersWithExperience.size()).isEqualTo(2);
        assertTestersWithExperienceAreInCorrectOrder(testersWithExperience);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(0), "Janko Walski", 7);
        assertTesterWithExperienceHasCorrectValues(testersWithExperience.get(1), "Jan Kowalski", 5);
    }

    private Tester createTester(long id, String country, String firstName, String lastName, Device... devices) {
        return new Tester()
                .setId(id)
                .setCountry(country)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDevices(new HashSet<>(asList(devices)));
    }

}
