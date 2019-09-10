package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceMonth;
import kintai.domain.AttendanceRepository;
import kintai.domain.Attendances;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final Path path;

    AttendanceCsvMapper mapper = new AttendanceCsvMapper();

    public AttendanceRepositoryCsv(Path path) {
        this.path = path;
    }

    @Override
    public void saveAttendance(Attendance attendance) {
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(mapper.toCsvRecord(attendance));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public Attendances findAttendancesByMonth(AttendanceMonth month) {
        try {
            List<String> lines = Files.readAllLines(path);
            Map<LocalDate, Attendance> map = new HashMap<>();
            for (String line : lines) {
                Attendance attendance = mapper.fromCsvRecord(line);
                map.put(attendance.getAttendanceDate(), attendance);
            }
            return new Attendances(new ArrayList<>(map.values()));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
