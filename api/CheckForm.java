package api;

import domain.WorkMinutesBiglobeDomain;
import domain.japan.DateDomain;
import domain.japan.EndDomain;
import domain.japan.StartDomain;
import domain.japan.WorkMinutesInterface;

public class CheckForm {

    private MethodType methodType;
    private StartDomain start;
    private EndDomain end;
    private DateDomain date;
    private WorkMinutesInterface work;

    public CheckForm(String[] form) {

        ChangeForm changeForm = new ChangeForm();

        if ("input".equals(form[0])) {
            this.methodType = MethodType.INPUT;

            if (form.length < 3) {
                throw new RuntimeException("引数が足りません");
            }

            checkInput(form, changeForm);
            this.work = new WorkMinutesBiglobeDomain(start, end, date);
        } else if ("total".equals(form[0])) {
            if (form.length < 2) {
                throw new RuntimeException("引数が足りません");
            }

            this.methodType = MethodType.TOTAL;
            this.date = changeForm.getDate(form[1]);

        } else {
            throw new RuntimeException("methodTypeが不正です");

        }
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public StartDomain getStart() {
        return start;
    }

    public EndDomain getEnd() {
        return end;
    }

    public DateDomain getDate() {
        return date;
    }

    public WorkMinutesInterface getWork() {
        return work;
    }

    private void checkInput(String[] form, ChangeForm changeForm) {

        for (String fo : form) {

            switch (fo) {
                case "v":
                    this.start = changeForm.getStart("-start:0900");
                    this.end = changeForm.getEnd("-end:1800");
                    break;
                case "am":
                    this.start = changeForm.getStart("-start:1200");
                    break;
                case "pm":
                    this.end = changeForm.getEnd("-end:1200");
                    break;
            }

            if (fo.startsWith("-end:")) {
                this.end = changeForm.getEnd(fo);
            }
            if (fo.startsWith("-date:")) {
                this.date = changeForm.getDate(fo);
            }
            if (fo.startsWith("-start:")) {
                this.start = changeForm.getStart(fo);
            }

        }

    }
}
