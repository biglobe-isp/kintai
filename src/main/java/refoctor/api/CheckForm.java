package refoctor.api;

import refoctor.domain.japan.DateDomain;
import refoctor.domain.japan.EndTime;
import refoctor.domain.japan.StartTime;
import refoctor.domain.japan.WorkTime;

public class CheckForm {

    private MethodType methodType;
    private StartTime startTime;
    private EndTime endTime;
    private DateDomain dateDomain;
    private WorkTime workTime;

    public CheckForm(String[] form) {

        ChangeForm changeForm = new ChangeForm();

        if("input".equals(form[0])) {
            this.methodType = MethodType.input;

            if(form.length < 3) {
                throw new RuntimeException("引数が足りません");
            }

            CheckInput(form, changeForm);
                this.workTime = new WorkTime(startTime, endTime, dateDomain);

        }else if("total".equals(form[0])){
            if (form.length < 2){
                throw new RuntimeException("引数が足りません");
            }

            this.methodType = MethodType.total;
            this.dateDomain = changeForm.getDateDomain(form[1]);

        }else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public DateDomain getDateDomain() {
        return dateDomain;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }
}

    private void CheckInput(String[] form, ChangeForm changeForm) {

        for (String fo : form) {

            switch (fo) {
                case "v":
                    this.startTime = changeForm.getStart("-start:0900");
                    this.endTime = changeForm.getEnd("-end:1800");
                    break;
                case "am":
                    this.startTime = changeForm.getStart("-start:1200");
                    break;
                case "pm":
                    this.endTime = changeForm.getEnd("-end:1200");
                    break;
            }

            if (fo.startsWith("-end:")) {
                this.endTime = changeForm.getEnd(fo);
            }
            if (fo.startsWith("-date:")) {
                this.dateDomain = changeForm.getDateDomain(fo);
            }
            if (fo.startsWith("-start:")) {
                this.startTime = changeForm.getStart(fo);
            }

        }

    }
