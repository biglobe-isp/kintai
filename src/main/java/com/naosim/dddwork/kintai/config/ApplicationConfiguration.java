package com.naosim.dddwork.kintai.config;

import com.naosim.dddwork.kintai.controller.ControllerComponentScanConfiguration;
import com.naosim.dddwork.kintai.service.ServiceComponentScanConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ControllerComponentScanConfiguration.class,
        ServiceComponentScanConfiguration.class
})
public class ApplicationConfiguration {
}
