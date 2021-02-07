package controller;

import com.mahlik.demo.controller.TesterSearchController;
import com.mahlik.demo.dto.DropdownListItem;
import com.mahlik.demo.dto.TesterWithExperience;
import com.mahlik.demo.service.TesterSearchCriteriaService;
import com.mahlik.demo.service.TesterSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static helper.TesterSearchTestsHelper.assertDropdownListItemHasCorrectValues;
import static helper.TesterSearchTestsHelper.assertTesterWithExperienceHasCorrectValues;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TesterSearchControllerTest {

    @InjectMocks
    TesterSearchController testerSearchController;

    @Mock
    TesterSearchService testerSearchService;

    @Mock
    TesterSearchCriteriaService testerSearchCriteriaService;

    @Test
    public void testGetTestersWithExperience() {
        // given
        Optional<Set<String>> countries = Optional.of(new HashSet<String>() {{
            add("GB");
            add("US");
        }});
        Optional<Set<Long>> deviceIds = Optional.of(new HashSet<Long>() {{
            add(1L);
            add(2L);
        }});
        when(testerSearchService.getTestersWithExperienceByProvidedCriteria(countries, deviceIds)).thenReturn(Arrays.asList(
                new TesterWithExperience(1L,"Tomcio Paluch", 48),
                new TesterWithExperience(2L,"Janko Walski", 36)
        ));
        // when
        List<TesterWithExperience> testers = testerSearchController.getTestersWithExperience(countries, deviceIds);
        // then
        assertThat(testers.size()).isEqualTo(2);
        assertThat(testers.get(0).getExperience()).isGreaterThan(testers.get(1).getExperience());
        assertTesterWithExperienceHasCorrectValues(
                testers.get(0),
                1L,
                "Tomcio Paluch",
                48);
        assertTesterWithExperienceHasCorrectValues(
                testers.get(1),
                2L,
                "Janko Walski",
                36);
    }

    @Test
    public void testGetAvailableCountryOptions() {
        // given
        when(testerSearchCriteriaService.getAvailableCountryOptions()).thenReturn(Arrays.asList(
                new DropdownListItem(1L, "GB"),
                new DropdownListItem(2L, "US")
        ));
        // when
        List<DropdownListItem> countries = testerSearchController.getAvailableCountries();
        // then
        assertThat(countries.size()).isEqualTo(2);
        assertDropdownListItemHasCorrectValues(
                countries.get(0),
                1L,
                "GB");
        assertDropdownListItemHasCorrectValues(
                countries.get(1),
                2L,
                "US");
    }

    @Test
    public void testGetAvailableDeviceOptions() {
        // given
        when(testerSearchCriteriaService.getAvailableDeviceOptions()).thenReturn(Arrays.asList(
                new DropdownListItem(1L, "iPhone 4"),
                new DropdownListItem(2L, "iPhone 4S")
        ));
        // when
        List<DropdownListItem> countries = testerSearchController.getAvailableDevices();
        // then
        assertThat(countries.size()).isEqualTo(2);
        assertDropdownListItemHasCorrectValues(
                countries.get(0),
                1L,
                "iPhone 4");
        assertDropdownListItemHasCorrectValues(
                countries.get(1),
                2L,
                "iPhone 4S");
    }


}
