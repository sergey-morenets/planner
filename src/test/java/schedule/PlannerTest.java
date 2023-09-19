package schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setup() {
        planner = new Planner();
    }

    @Test
    void getWorkingDays_singleWeekNoWeekend_success() {
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 21));
        assertEquals(4, workingDays);
    }

    @Test
    void getWorkingDays_singleWeekWithNoWeekend_success() {
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 23));
        assertEquals(5, workingDays);
    }

    @Test
    void getWorkingDays_singleWeekWithStartWeekend_success() {
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 16), LocalDate.of(2023, 9, 23));
        assertEquals(5, workingDays);
    }

    @Test
    void getWorkingDays_multipleWeeks_success() {
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 10, 11));
        assertEquals(18, workingDays);
    }

    @Test
    void getWorkingDays_differentYears_success() {
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2024, 4, 5));
        assertEquals(145, workingDays);
    }

    @Test
    void getWorkingDays_holidaysInsideTheRange_success() {
        planner.addHoliday(new Holiday(9, 20));
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 21));
        assertEquals(3, workingDays);
    }

    @Test
    void getWorkingDays_holidaysOutsideTheRange_success() {
        planner.addHoliday(new Holiday(9, 25));
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 21));
        assertEquals(4, workingDays);
    }

    @Test
    void getWorkingDays_holidaysAsWeekendDate_success() {
        planner.addHoliday(new Holiday(9, 23));
        int workingDays = planner.getWorkingDays(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 23));
        assertEquals(5, workingDays);
    }

}
