package it4kids.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	 registry.addViewController("/home").setViewName("home");
         registry.addViewController("/").setViewName("login");
         registry.addViewController("/hello").setViewName("hello");
         registry.addViewController("/login").setViewName("login");
    }

  /*  @Bean
   	public InternalResourceViewResolver viewResolver() {
   		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
   		resolver.setPrefix("/WEB-INF/jsp/");
   		resolver.setSuffix(".jsp");
   		return resolver;
   	}*/
    
    /*@Bean
	public FreeMarkerViewResolver getFmViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public InternalResourceViewResolver getJspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver;
	}
	
	@Bean
	  public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
	    FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
	    factory.setTemplateLoaderPath("classpath:templates");
	    factory.setDefaultEncoding("UTF-8");
	    FreeMarkerConfigurer result = new FreeMarkerConfigurer();
	    result.setConfiguration(factory.createConfiguration());
	    return result;
	  }	
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(getFmViewResolver());
		resolvers.add(getJspViewResolver());
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}*/
}
