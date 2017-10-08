package spittr.web;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"spittr.web"})
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//      }
      
      @Override
      public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
      }
      
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
      }
      
      @Bean
      public ResourceBundleMessageSource messageSource() {
          ResourceBundleMessageSource source = new ResourceBundleMessageSource();
          source.setBasename("ValidationMessages");
          source.setUseCodeAsDefaultMessage(true);
          return source;
      }
      
      @Bean(name = "validator")
      public LocalValidatorFactoryBean localValidatorFactoryBean() {
          LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();

          lvfb.setValidationMessageSource(messageSource());

          return lvfb;
      }

      @Override
      public org.springframework.validation.Validator getValidator() {
          return localValidatorFactoryBean();
      }
      
      // Tiles
//      @Bean
//      public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[] {
//            "/WEB-INF/layout/tiles.xml",
//            "/WEB-INF/views/**/tiles.xml"
//        });
//        tiles.setCheckRefresh(true);
//        return tiles;
//      }
      
//      @Bean
//      public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//      }
      
      @Bean
      public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
      }
      @Bean
      public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
      }

      @Bean
      public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
      }

}
