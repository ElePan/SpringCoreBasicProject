package config;

import controllers.GreetingsController;
import controllers.WelcomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//Annotation to tell spring that class is a config one
@ComponentScan(basePackages = "controllers")
//Annotations to tell spring we have components that he need to add to the config
public class AppConfig {

    @Bean
    WelcomeController welcomeController(){
        return new WelcomeController(); //this instance will be added to the context
    }

    @Bean
    GreetingsController holaController(){
        return new GreetingsController(); //this instance will be added to the context
    }
    @Bean
    GreetingsController ciaoController(){
        return new GreetingsController(); //this instance will be added to the context
    }

}
//You are declaring a config class in this file
