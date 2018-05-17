package br.senai.sp.informatica.senaipatrimonio.config;

import br.senai.sp.informatica.senaipatrimonio.interceptor.AutenticacaoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration 
@EnableWebMvc
@ComponentScan("br.senai.sp.informatica.senaipatrimonio")
@Import(PersistenceConfig.class)
public class WebConfig implements WebMvcConfigurer {


	
	/**
	 * Configuração de acesso as paginas jsp
	 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        registry.viewResolver(resolver);
    }


    /**
     * Define a pasta de resource (jsp, css) da aplicação
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    /**
     * Configuração das Urls que vão ser Interceptadas
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAutenticacaoInterceptor()).addPathPatterns("/**");
    }

    
    /**
     * Configuração do Interceptor
     * @return AutenticacaoInterceptor
     */
    @Bean
    public AutenticacaoInterceptor getAutenticacaoInterceptor() {
        return new AutenticacaoInterceptor();
    }

    /**
     * Configure o MultipartResolver (classe que gerencia o protocolo multipart)
     *
     * @return MultipartResolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();


        resolver.setMaxUploadSize(5 * 1024 * 1024);

        return resolver;
    }

}
