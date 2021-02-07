package integration;

import com.mahlik.demo.ApplauseDemoApplication;
import com.mahlik.demo.controller.TesterSearchController;
import com.mahlik.demo.dto.TesterWithExperience;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static helper.TesterSearchTestsHelper.assertTesterWithExperienceHasCorrectValues;
import static helper.TesterSearchTestsHelper.assertTestersWithExperienceAreInCorrectOrder;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplauseDemoApplication.class)
@WebAppConfiguration
public class TesterSearchIntegrationTest {

    @Autowired
    private TesterSearchController testerSearchController;

    @Test
    public void getTestersWithExperience_countryUS_deviceHTCOne() {
        List<TesterWithExperience> testersWithExperience = testerSearchController.getTestersWithExperience(
                prepareOptionalSet("US"),
                prepareOptionalSet(9L)
        );
        assertThat(testersWithExperience.size()).isEqualTo(1);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(0),
                2L,
                "Michael Lubavin",
                17);
    }

    @Test
    public void getTestersWithExperience_countryJP_deviceIPhone4S() {
        List<TesterWithExperience> testersWithExperience = testerSearchController.getTestersWithExperience(
                prepareOptionalSet("JP"),
                prepareOptionalSet(2L)
        );
        assertThat(testersWithExperience).isEmpty();
    }

    @Test
    public void getTestersWithExperience_noCriteriaSelected() {
        List<TesterWithExperience> testersWithExperience = testerSearchController.getTestersWithExperience(
                Optional.empty(),
                Optional.empty()
        );
        assertThat(testersWithExperience).isEmpty();
    }

    @Test
    public void getTestersWithExperience_countryUS() {
        List<TesterWithExperience> testersWithExperience = testerSearchController.getTestersWithExperience(
                prepareOptionalSet("US"),
                Optional.empty()
        );
        assertThat(testersWithExperience.size()).isEqualTo(3);
        assertTestersWithExperienceAreInCorrectOrder(testersWithExperience);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(0),
                4L,
                "Taybin Rutkin",
                125);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(1),
                1L,
                "Miguel Bautista",
                114);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(2),
                2L,
                "Michael Lubavin",
                99);
    }

    @Test
    public void getTestersWithExperience_deviceNexus4() {
        List<TesterWithExperience> testersWithExperience = testerSearchController.getTestersWithExperience(
                Optional.empty(),
                prepareOptionalSet(6L)
        );
        assertThat(testersWithExperience.size()).isEqualTo(6);
        assertTestersWithExperienceAreInCorrectOrder(testersWithExperience);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(0),
                9L,
                "Darshini Thiagarajan",
                28);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(1),
                3L,
                "Leonard Sutton",
                27);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(2),
                7L,
                "Lucas Lowry",
                25);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(3),
                8L,
                "Sean Wellington",
                23);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(4),
                5L,
                "Mingquan Zheng",
                13);
        assertTesterWithExperienceHasCorrectValues(
                testersWithExperience.get(5),
                2L,
                "Michael Lubavin",
                11);
    }

    @SafeVarargs
    private final <T> Optional<Set<T>> prepareOptionalSet(T... criteriaOptions) {
        return Optional.of(new HashSet<>(asList(criteriaOptions)));
    }

}
