package api;

import domain.*;

public class CheckForm {

    private MethodType methodType;
    private StartDomain start;
    private EndDomain end;
    private DateDomain date;
    private WorkMinutesDomain work;

    public CheckForm(String[] form) {

        ChengeForm chengeForm = new ChengeForm();

        if (MethodType.INPUT.equals(form[0])) {
            if (form.length < 4) {
                throw new RuntimeException("引数が足りません");
            }

            this.methodType = MethodType.INPUT;
            this.date = chengeForm.getDate(form[1]);
            this.start = chengeForm.getStart(form[2]);
            this.end = chengeForm.getEnd(form[3]);
            this.work = new WorkMinutesDomain(start, end);


        } else if (MethodType.TOTAL.equals(form[0])) {
            if (form.length < 2) {
                throw new RuntimeException("引数が足りません");
            }

            this.methodType = MethodType.TOTAL;
            this.date = chengeForm.getDate(form[1]);

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
