package schedule.reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import schedule.Holiday;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GsonHolidayReader implements HolidayReader {

    private final Gson gson = new Gson();

    @Override
    public List<Holiday> read(String path) {
        try {
            return gson.fromJson(Files.readString(Paths.get(path)),
                    new TypeToken<List<Holiday>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
