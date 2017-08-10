package pl.sscode.onepass.repository.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by sscode on 2017-08-10.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {
}
