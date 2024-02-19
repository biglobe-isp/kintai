package com.kintai.datasource.repository;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceGetRepository;
import com.kintai.exception.ValidatorException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CsvAttendanceGetRepository implements IAttendanceGetRepository {
    private final IAttendanceFactory iAttendanceFactory;

    public CsvAttendanceGetRepository(IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceFactory = iAttendanceFactory;
    }
    @Override
    public List<Attendance> get() throws IOException, ValidatorException, ParseException {
        BufferedReader reader = readToFile();
        List<Attendance> attendanceList = getAttendanceList(reader);
        List<Attendance> sortedAttendanceList = sortAttendanceList(attendanceList);
        List<Attendance> excludeDuplicationAttendanceList = excludeDuplicationAttendance(sortedAttendanceList);
        return excludeDuplicationAttendanceList;
    }

    protected BufferedReader readToFile() throws IOException {
        File file = new File("data.csv");
        if(!file.exists()) {
            throw new IOException("data.csvファイルが存在しません。");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return br;
    }

    protected List<Attendance> getAttendanceList(BufferedReader br) throws IOException, ValidatorException, ParseException {
        List<Attendance> attendanceList = new ArrayList<>();
        String line = br.readLine();
        while(StringUtils.hasText(line)) {
            String[] columns = line.split(",");
            AttendanceFactoryDto attendanceFactoryDto = new AttendanceFactoryDto(columns[1],columns[2], columns[3]);
            attendanceFactoryDto.setTotalMonth(columns[0]);
            attendanceFactoryDto.setWorkMinutes(columns[4]);
            attendanceFactoryDto.setOverWorkMinute(columns[5]);
            attendanceFactoryDto.setLocalDateTime(columns[6]);
            attendanceList.add(iAttendanceFactory.makeAttendance(attendanceFactoryDto));
            line = br.readLine();
        }
        return attendanceList;
    }

    protected List<Attendance> sortAttendanceList(List<Attendance> attendanceList) {
        // 登録日時の降順でソート。同じ勤務日で再登録ができるため、集計する際は登録日が最新のデータで集計するため。
        return attendanceList.stream().sorted((Comparator.comparing(Attendance::getLocalDateTime)).reversed()).collect(Collectors.toList());
    }

    protected List<Attendance> excludeDuplicationAttendance(List<Attendance> attendanceList) throws IOException, ValidatorException {
        // 勤務日が重複するデータは古いデータを除外
        Set<String> unique = new HashSet<>();
        attendanceList.removeIf(attendance -> !unique.add(attendance.getWorkDate().getWorkDate()));
        return attendanceList;
    }
}
