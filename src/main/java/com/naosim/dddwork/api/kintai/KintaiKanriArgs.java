//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.api.kintai;

import com.naosim.dddwork.api.form.MethodTypeForm;
import com.naosim.dddwork.api.form.WorkDateForm;
import com.naosim.dddwork.api.form.WorkTimeFromForm;
import com.naosim.dddwork.api.form.WorkTimeToForm;
import com.naosim.dddwork.domain.word.MethodType;
import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput;
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

class KintaiKanriArgs {
    @NotNull
    @Valid
    private MethodTypeForm methodTypeForm;
    @NotNull
    @Valid
    private WorkDateForm workDateForm;
    private WorkTimeFromForm workTimeFromForm = new WorkTimeFromForm((String) null);
    private WorkTimeToForm workTimeToForm = new WorkTimeToForm((String) null);

    KintaiKanriArgs(String[] args) {
        //int METHOD_TYPE_INPUT_ARGS_LENGTH = true;
        //int METHOD_TYPE_TOTAL_ARGS_LENGTH = true;
        if (args != null && args.length > 0) {
            if (args.length > 0) {
                if (MethodType.INPUT.getApiValue().equals(args[0].toLowerCase())) {
                    if (args.length != 4) {
                        throw new IllegalArgumentException("引数が足りません");
                    }

                    this.doValidation(MethodType.INPUT.getApiValue(), args);
                    this.methodTypeForm = new MethodTypeForm(args[0]);
                    this.workDateForm = new WorkDateForm(args[1]);
                    this.workTimeFromForm = new WorkTimeFromForm(args[2]);
                    this.workTimeToForm = new WorkTimeToForm(args[3]);
                    if (this.workTimeFromForm.getValueObject().convertLocalTime().compareTo(this.workTimeToForm.getValueObject().convertLocalTime()) > 0) {
                        throw new IllegalArgumentException("出勤時間が退勤時間より後になっています");
                    }
                } else {
                    if (!MethodType.TOTAL.getApiValue().equals(args[0].toLowerCase())) {
                        throw new IllegalArgumentException("methodTypeが不正です");
                    }

                    if (args.length != 1) {
                        throw new IllegalArgumentException("引数が足りません");
                    }

                    this.doValidation(MethodType.TOTAL.getApiValue(), args);
                    this.methodTypeForm = new MethodTypeForm(args[0]);
                }
            }

        } else {
            throw new IllegalArgumentException("引数が足りません");
        }
    }

    KintaiKanriInputServiceInput makeKintaiKanriInputServiceInput() {
        return new KintaiKanriInputServiceInput(this.methodTypeForm.getValueObject(), this.workDateForm.getValueObject(), this.workTimeFromForm.getValueObject(), this.workTimeToForm.getValueObject());
    }

    KintaiKanriTotalServiceInput makeKintaiKanriTotalServiceInput() {
        return new KintaiKanriTotalServiceInput(this.methodTypeForm.getValueObject());
    }

    private void doValidation(String apiValue, String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<MethodTypeForm>> result1 = validator.validate(new MethodTypeForm(args[0]), new Class[0]);
        if (result1.size() > 0) {
            throw new ValidationException(((ConstraintViolation) result1.iterator().next()).getMessage());
        } else {
            if (apiValue.equals(MethodType.INPUT.getApiValue())) {
                Set<ConstraintViolation<WorkDateForm>> result2 = validator.validate(new WorkDateForm(args[1]), new Class[0]);
                if (result2.size() > 0) {
                    throw new ValidationException(((ConstraintViolation) result2.iterator().next()).getMessage());
                }

                Set<ConstraintViolation<WorkTimeFromForm>> result3 = validator.validate(new WorkTimeFromForm(args[2]), new Class[0]);
                if (result3.size() > 0) {
                    throw new ValidationException(((ConstraintViolation) result3.iterator().next()).getMessage());
                }

                Set<ConstraintViolation<WorkTimeToForm>> result4 = validator.validate(new WorkTimeToForm(args[3]), new Class[0]);
                if (result4.size() > 0) {
                    throw new ValidationException(((ConstraintViolation) result4.iterator().next()).getMessage());
                }
            }

        }
    }

    public MethodTypeForm getMethodTypeForm() {
        return this.methodTypeForm;
    }
}
