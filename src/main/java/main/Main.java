package main;

import config.AppConfig;
import controllers.GreetingsController;
import controllers.IAmAComponentController;
import controllers.WelcomeController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        //If AppConfig is empty, so we have an empty context.
        //In this case is not empty, there is a WelcomeController (bean) instance.

        WelcomeController welcome = context.getBean(WelcomeController.class);
        //Spring search for a bean of type WelcomeController inside the context
        System.out.println(welcome);

        WelcomeController bienvenue = context.getBean(WelcomeController.class);
        System.out.println(bienvenue);
        //welcome and bienvenue refers to the same bean in the context. As we have only one instance of type WelcomeController

        System.out.println("Are welcome and bienvenue equals? " + welcome.equals(bienvenue));
        //As they refer to the same instance, we expect a true here
        assert welcome.equals(bienvenue);

        GreetingsController ciao = context.getBean("ciaoController", GreetingsController.class);
        //Spring search for a bean of type GreetingsController and called ciaoController inside the context
        System.out.println(ciao);

        GreetingsController hola = context.getBean("holaController", GreetingsController.class);
        //Spring search for a bean of type GreetingsController and called holaController inside the context
        System.out.println(hola);

        System.out.println("Are hola and ciao equals? " + hola.equals(ciao));
        //hola and ciao refer to different instances of GreetingsController, we expect a false here
        assert !hola.equals(ciao);

        IAmAComponentController component = context.getBean("IAmAComponentController",IAmAComponentController.class);
        //With @Component in the class and @ComponentScan in AppConfig, the instance is automatically added to context
        System.out.println(component);
        //So we expect no errors here
    }
}