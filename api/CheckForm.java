package api;

import domain.*;
import domain.WorkMinutesDomain;

public class CheckForm {

    private MethodType methodType;
    private StartDomain start;
    private EndDomain end;
    private DateDomain date;
    private WorkMinutesDomain work;

    public CheckForm(String[] form) {

        ChangeForm changeForm = new ChangeForm();

        if ("input".equals(form[0])) {
            if (form.length < 3) {
                throw new RuntimeException("引数が足りません");
            } else if ("v".equals(form[2])) {
                this.start = changeForm.getStart("-start:0900");
                this.end = changeForm.getEnd("-end:1800");
            } else if ("am".equals(form[2])) {
                this.start = changeForm.getStart("-start:1200");
                this.end = changeForm.getEnd(form[3]);
            } else if ("pm".equals(form[3])) {
                this.start = changeForm.getStart(form[2]);
                this.end = changeForm.getEnd("-end:1200");
            } else {
                this.start = changeForm.getStart(form[2]);
                this.end = changeForm.getEnd(form[3]);
            }
            this.methodType = MethodType.INPUT;
            this.date = changeForm.getDate(form[1]);
            this.work = new WorkMinutesDomain(start, end, date);

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

    public WorkMinutesDomain getWork() {
        return work;
    }
}
