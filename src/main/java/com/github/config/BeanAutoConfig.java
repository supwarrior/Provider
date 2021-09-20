package com.github.config;

import com.github.javabean.register.AutoConfigureRegister;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
@Import(AutoConfigureRegister.class)
public class BeanAutoConfig {
}
