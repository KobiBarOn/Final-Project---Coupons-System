package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import app.core.exceptions.CouponSystemException;
import app.core.filters.LoginFilter;
import app.core.session.SessionContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CouponsSpringRestApplication {

	public static void main(String[] args) throws CouponSystemException {

		SpringApplication.run(CouponsSpringRestApplication.class, args);

	}

	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistration(SessionContext sessionContext) {
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		LoginFilter loginFilter = new LoginFilter(sessionContext);
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}
}
