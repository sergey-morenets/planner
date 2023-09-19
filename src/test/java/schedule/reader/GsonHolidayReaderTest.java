package schedule.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schedule.Holiday;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GsonHolidayReaderTest {

    private HolidayReader holidayReader;

    @BeforeEach
    void setup() {
        holidayReader = new GsonHolidayReader();
    }

    @Test
    void read_validPath_success() {
        List<Holiday> holidays = holidayReader.read("src/test/resources/json/holidays.json");
        assertEquals(2, holidays.size());
        Holiday holiday = holidays.get(0);
        assertEquals(2, holiday.day());
        assertEquals(3, holiday.month());
    }

    @Test
    void read_invalidPath_error() {
        assertThrows(RuntimeException.class, () -> holidayReader.read("src/test/resources/json/invalid.json"));
    }
}
