package pl.sscode.onepass.repository.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.sscode.onepass.repository.api.config.JpaConfig;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sscode on 2017-06-15.
 */
public class RepositoryConfigTest {
    @Test
    public void bootstrapApp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        assertThat(context, is(notNullValue()));
        assertThat(context.getBean("userRepository"), is(notNullValue()));
    }
}
