package schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Task
 * Please implement a maven library using the Java programming language. The library should have the following:
 * 1. A method to calculate the number of workdays between two given dates (inclusive)
 * 2. A method to set/add holidays and other non-working days
 * 3. Ability to read/write holidays from/to a local JSON file (keep in mind that later we may want to
 * store the holidays somewhere else, for example a database)
 * The completed assignment should be sent back in a zip file (please include pom and java files only).
 * <p>
 * Example
 * Given
 * a. July 1st, 2022 is a holiday
 * b. July 2nd, 2022 is a weekend
 * c. July 3rd, 2022 is a weekend
 * The number of workdays between June 27, 2022 and July 4th, 2022 is 5.
 * Evaluation points
 * 1. Object oriented design
 * 2. Code maintainability
 * 3. Unit tests
 */
public class Planner {

    private List<Holiday> holidays;

    public void setHoliday(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    public void addHoliday(Holiday holiday) {
        if (holidays == null) {
            holidays = new ArrayList<>();
        }
        holidays.add(holiday);
    }

    public int getWorkingDays(final LocalDate startDate, final LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date: " + startDate + " should be before end date: " + endDate);
        }

        LocalDate firstDate = isWeekend(startDate.getDayOfWeek()) ? dayAfterWeekend(startDate) : startDate;
        LocalDate secondDate = isWeekend(endDate.getDayOfWeek()) ? dayBeforeWeekend(endDate) : endDate;
        int totalDays = (int) ChronoUnit.DAYS.between(firstDate, secondDate.plusDays(1));

        int weeks = totalDays / 7;
        totalDays -= weeks * 2;

        if (holidays != null && !holidays.isEmpty()) {
            int years = Period.between(firstDate, secondDate.plusDays(1)).getYears();
            totalDays -= (int) holidays.stream().filter(item -> item.isInRange(firstDate, secondDate)).count() * (years + 1);
        }

        return totalDays;
    }

    private boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private LocalDate dayAfterWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.plusDays(2);
        } else {
            return date.plusDays(1);
        }
    }

    private LocalDate dayBeforeWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else {
            return date.minusDays(2);
        }
    }
}
