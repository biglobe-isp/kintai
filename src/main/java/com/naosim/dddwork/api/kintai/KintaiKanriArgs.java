package com.naosim.dddwork.api.kintai;

import com.naosim.dddwork.api.form.MethodTypeForm;
import com.naosim.dddwork.api.form.WorkDateForm;
import com.naosim.dddwork.api.form.WorkTimeFromForm;
import com.naosim.dddwork.api.form.WorkTimeToForm;
import com.naosim.dddwork.domain.word.MethodType;
import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput;
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 勤怠管理API　引数管理
 */
class KintaiKanriArgs {

    /**
     * methodType
     */
    @Getter
    @Setter
    @NotNull
    @Valid
    private MethodTypeForm methodTypeForm;

    /**
     * 対象年月日
     */
    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkDateForm workDateForm;

    /**
     * 出勤時間
     */
    @Getter
    @Setter
    private WorkTimeFromForm workTimeFromForm = new WorkTimeFromForm(null);

    /**
     * 退勤時間
     */
    @Getter
    @Setter
    private WorkTimeToForm workTimeToForm = new WorkTimeToForm(null);

    /**
     * コンストラクタ
     */
    KintaiKanriArgs(String[] args) {

        // argsをチェックしつつ初期化しつつ
        final int METHOD_TYPE_INPUT_ARGS_LENGTH = 4;
        final int METHOD_TYPE_TOTAL_ARGS_LENGTH = 1;

        if (args == null || args.length <= 0) {
            throw new IllegalArgumentException("引数が足りません");
        } else if (args.length > 0) {
            if (MethodType.INPUT.getApiValue().equals(args[0].toLowerCase())) {
                if (args.length != METHOD_TYPE_INPUT_ARGS_LENGTH) {
                    throw new IllegalArgumentException("引数が足りません");
                } else {
                    doValidation(MethodType.INPUT.getApiValue(), args);
                    this.methodTypeForm = new MethodTypeForm(args[0]);
                    this.workDateForm = new WorkDateForm(args[1]);
                    this.workTimeFromForm = new WorkTimeFromForm(args[2]);
                    this.workTimeToForm = new WorkTimeToForm(args[3]);

                    if (this.workTimeFromForm.getValueObject().convertLocalTime()
                            .compareTo(this.workTimeToForm.getValueObject().convertLocalTime()) > 0) {
                        throw new IllegalArgumentException("出勤時間が退勤時間より後になっています");
                    }

                }
            } else if (MethodType.TOTAL.getApiValue().equals(args[0].toLowerCase())) {
                if (args.length != METHOD_TYPE_TOTAL_ARGS_LENGTH) {
                    throw new IllegalArgumentException("引数が足りません");
                } else {
                    doValidation(MethodType.TOTAL.getApiValue(), args);
                    this.methodTypeForm = new MethodTypeForm(args[0]);
                }
            } else {
                throw new IllegalArgumentException("methodTypeが不正です");
            }
        }
    }

    /**
     * 勤務管理登録Service用Entity
     */
    KintaiKanriInputServiceInput makeKintaiKanriInputServiceInput() {
        return new KintaiKanriInputServiceInput(
                this.methodTypeForm.getValueObject(),
                this.workDateForm.getValueObject(),
                this.workTimeFromForm.getValueObject(),
                this.workTimeToForm.getValueObject()
        );
    }

    /**
     * 勤務管理集計Service用Entity
     */
    KintaiKanriTotalServiceInput makeKintaiKanriTotalServiceInput() {
        return new KintaiKanriTotalServiceInput(
                this.methodTypeForm.getValueObject()
        );
    }

    /**
     * Validation
     */
    private void doValidation(String apiValue, String[] args) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<MethodTypeForm>> result1 = validator.validate(new MethodTypeForm(args[0]));
        if (result1.size() > 0) throw new ValidationException(result1.iterator().next().getMessage());

        if (apiValue.equals(MethodType.INPUT.getApiValue())) {
            Set<ConstraintViolation<WorkDateForm>> result2 = validator.validate(new WorkDateForm(args[1]));
            if (result2.size() > 0) throw new ValidationException(result2.iterator().next().getMessage());

            Set<ConstraintViolation<WorkTimeFromForm>> result3 = validator.validate(new WorkTimeFromForm(args[2]));
            if (result3.size() > 0) throw new ValidationException(result3.iterator().next().getMessage());

            Set<ConstraintViolation<WorkTimeToForm>> result4 = validator.validate(new WorkTimeToForm(args[3]));
            if (result4.size() > 0) throw new ValidationException(result4.iterator().next().getMessage());
        }
    }
}
