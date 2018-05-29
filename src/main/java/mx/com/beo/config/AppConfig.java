package mx.com.beo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 

 

@Configuration
public class AppConfig {
	
	/*
	 * Configuracion de Cors.
	 * 
	 */
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registro) {
            	registro.addMapping("/accesoCliente").allowedOrigins("*");
            	registro.addMapping("/cambioContrasena").allowedOrigins("*");
            }
        };
    }


	 
	
}
