/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kintai;

import kintai.api.AttendanceApi;
import kintai.datasource.AttendanceMapperCsv;
import kintai.datasource.AttendanceMapperCsvImpl;
import kintai.datasource.AttendanceRepositoryCsv;
import kintai.datasource.util.FileCreator;
import kintai.datasource.util.FileCreatorImpl;
import kintai.domain.AttendanceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    @Bean
    public static AttendanceApi attendanceApi(AttendanceRepository attendanceRepository) {
        return new AttendanceApi(attendanceRepository);
    }

    @Bean
    public static AttendanceRepository attendanceRepository(AttendanceMapperCsv attendanceMapperCsv) {
        return new AttendanceRepositoryCsv(attendanceMapperCsv);
    }

    @Bean
    public static AttendanceMapperCsv attendanceMapperCsv() {
        return new AttendanceMapperCsvImpl(fileCreator());
    }

    @Bean
    public static FileCreator fileCreator() {
        return new FileCreatorImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        AttendanceApi api = new AttendanceApi(
                attendanceRepository(attendanceMapperCsv()));
        System.out.println(api.get());
    }
}
