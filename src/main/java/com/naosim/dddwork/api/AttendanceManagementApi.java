package com.naosim.dddwork.api;

import com.naosim.dddwork.api.form.ClosingHoursFrom;
import com.naosim.dddwork.api.form.ProcessingDivisionForm;
import com.naosim.dddwork.api.form.StartingHoursFrom;
import com.naosim.dddwork.api.form.WorkDayFrom;
import com.naosim.dddwork.api.form.WorkingMonthFrom;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursResult;
import com.naosim.dddwork.service.TotalWorkingHoursService;
import com.naosim.dddwork.service.WorkTimeRegistrationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class AttendanceManagementApi {
    @Autowired
    private WorkTimeRegistrationService workTimeRegistrationService;

    @Autowired
    private TotalWorkingHoursService totalWorkingHoursService;

    public void main(String[] args) {
        try {

            ProcessingDivision processingDivision = new ProcessingDivisionForm(args[0]).getValueObject();

            // Input
            if (processingDivision.isInput()) {

                String[] argv = getParameterString(args);

                workTimeRegistrationService.input(
                        new WorkTimeRegistrationRequest(
                                new WorkDayFrom(argv[0]).getValueObject(),
                                new StartingHoursFrom(argv[1]).getValueObject(),
                                new ClosingHoursFrom(argv[2]).getValueObject()
                        ).makeWorkTimeRegistrationApplication()
                );
            }

            // Total
            if (processingDivision.isTotal()) {
                TotalWorkingHoursResult totalWorkingHoursResult = totalWorkingHoursService.refer(
                        new TotalWorkingHoursRequest(
                                new WorkingMonthFrom(args[1]).getValueObject()
                        ).makeTotalWorkingHoursApplication()
                );
                TotalWorkingHoursResultOutput totalWorkingHoursOutput = new TotalWorkingHoursResultOutput(totalWorkingHoursResult);
                totalWorkingHoursOutput.print();
            }
        } catch (Exception exception) {
            System.err.println("エラー:" + exception.getMessage());
        }
    }

    private String[] getParameterString(String[] args) {
        String[] paramString = new String[3];

        paramString[0] = args[1];
        paramString[1] = args[2];
        paramString[2] = args[3];

        if (args[1].startsWith("-date:")) {
            paramString[0] = args[1].replace("-date:", "");
        }
        if (args[2].startsWith("-start:")) {
            paramString[1] = args[2].replace("-start:", "");
        }
        if (args[3].startsWith("-end:")) {
            paramString[2] = args[3].replace("-end:", "");
        }
        if (paramString[1].equals("v")) {
            paramString[1] = "0900";
            paramString[2] = "1800";
        }
        if (paramString[1].equals("am")) {
            paramString[1] = "0900";
        }
        if (paramString[1].equals("pm")) {
            paramString[1] = "1800";
        }
        return paramString;
    }
}
