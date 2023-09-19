package schedule.reader;

import schedule.Holiday;

import java.util.List;

public interface HolidayReader {

    List<Holiday> read(String path);
}
