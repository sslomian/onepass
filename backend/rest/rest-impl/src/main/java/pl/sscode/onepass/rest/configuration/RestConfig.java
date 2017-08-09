package pl.sscode.onepass.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sscode on 2017-06-18.
 */
@Configuration
@ComponentScan(basePackages = {"pl.sscode.onepass.rest.impl"})
public class RestConfig {
}
