package jp.co.biglobe.isp.kintai;

import jp.co.biglobe.isp.kintai.api.controller.InputController;
import jp.co.biglobe.isp.kintai.api.controller.TotalController;
import jp.co.biglobe.isp.kintai.application.ArgsValidator;
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
        var argsValidator = new ArgsValidator();
        switch (argsValidator.valid(args)) {
            case INPUT -> inputController.run(args);
            case TOTAL -> System.out.println(totalController.run(args));
            default -> {}
        }
    }
}
