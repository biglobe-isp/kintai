package com.naosim.dddwork.kintai_management.datasource.input;

import com.naosim.dddwork.kintai_management.domain.duty.input.WorkingTimeRegistrationInput;
import com.naosim.dddwork.kintai_management.domain.duty.input.WorkingTimeRegistrationRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 */
@Repository
public class WorkingTimeRegistrationRepositoryFile implements WorkingTimeRegistrationRepository {

    @Override
    public void registWorkingTime(WorkingTimeRegistrationInput workingTimeRegistrationInput) {

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workingTimeRegistrationInput.getRegistrationDate().getFormatValue(),
                    workingTimeRegistrationInput.getWorkingStartTime().getValue(),
                    workingTimeRegistrationInput.getWorkingEndTime().getValue(),
                    workingTimeRegistrationInput.getWorkingTime().getValue(),
                    workingTimeRegistrationInput.getOverWorkingTime().getValue(),
                    LocalDateTime.now().toString())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
