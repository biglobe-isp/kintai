package jp.co.biglobe.isp.kintai;

import jp.co.biglobe.isp.kintai.api.InputController;
import jp.co.biglobe.isp.kintai.application.Method;
import jp.co.biglobe.isp.kintai.api.TotalController;
import jp.co.biglobe.isp.kintai.application.ArgsValidator;
import jp.co.biglobe.isp.kintai.application.OutputMonthlyAccumulatedWorkMinutes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@RequiredArgsConstructor
public class KintaiApplication implements CommandLineRunner {
    private final InputController inputController;
    private final TotalController totalController;

	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(KintaiApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);
		SpringApplication.run(KintaiApplication.class, args);
	}

    @Override
    public void run(String... args) {
        Method method = Method.findByType(args[0]);
        var argsValidator = new ArgsValidator();
        switch (method) {
            case INPUT:
                argsValidator.isValidInputArgs(args);
                inputController.run(args);
                break;

            case TOTAL:
                processTotal(args);
                break;

            default:
                throw new RuntimeException("引数のMethodが存在しません");
        }
    }

    private void processTotal(String... args) {
        var argsValidator = new ArgsValidator();

        argsValidator.isValidTotalArgs(args);
        var monthlyAccumulatedWorkMinutes = totalController.run(args);

        System.out.println(OutputMonthlyAccumulatedWorkMinutes.toStringMonthlyAccumulatedWorkRecordMinutes(monthlyAccumulatedWorkMinutes));
        System.out.println(OutputMonthlyAccumulatedWorkMinutes.toStringMonthlyAccumulatedOverTimeMinutes(monthlyAccumulatedWorkMinutes));
    }
}
