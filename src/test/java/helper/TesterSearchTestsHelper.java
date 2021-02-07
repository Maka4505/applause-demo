package helper;

import com.mahlik.demo.dto.DropdownListItem;
import com.mahlik.demo.dto.TesterWithExperience;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TesterSearchTestsHelper {

    public static void assertTesterWithExperienceHasCorrectValues(
            TesterWithExperience testerWithExperience,
            String fullName,
            int experience
    ) {
        assertThat(testerWithExperience.getFullName()).isEqualTo(fullName);
        assertThat(testerWithExperience.getExperience()).isEqualTo(experience);
    }

    public static void assertTestersWithExperienceAreInCorrectOrder(
            List<TesterWithExperience> testersWithExperience
    ) {
        for (int i = 0; i < testersWithExperience.size() - 1; i++) {
            assertThat(
                    testersWithExperience.get(i).getExperience()
            ).isGreaterThan(
                    testersWithExperience.get(i + 1).getExperience()
            );
        }
    }

    public static void assertDropdownListItemHasCorrectValues(
            DropdownListItem dropdownListItem,
            long expectedId,
            String expectedLabel
    ) {
        assertThat(dropdownListItem.getId()).isEqualTo(expectedId);
        assertThat(dropdownListItem.getLabel()).isEqualTo(expectedLabel);
    }

}
