package schedule;

import java.time.LocalDate;

/**
 * @param month 1 - 12
 * @param day   1 - 31
 */
public record Holiday(int month, int day) {

    public boolean isInRange(LocalDate startDate, LocalDate endDate) {
        boolean afterStartDate = startDate.getMonthValue() < month || (startDate.getMonthValue() == month
                && startDate.getDayOfMonth() <= day);
        boolean beforeEndDate = endDate.getMonthValue() > month || (endDate.getYear() > startDate.getYear()) ||
                (endDate.getMonthValue() == month
                        && endDate.getDayOfMonth() >= day);
        return afterStartDate && beforeEndDate;
    }
}
