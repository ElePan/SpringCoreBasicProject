# Starting with Spring Core

### Prerequisits
- Java 17
- enable -ea in your IDE (or use it when launching java) to use `assertion`
Steps in intellij: open "run/build configuration", go to "modify options" -> "add VM options", set "-ea" in this space.

## Key concepts

### IoC
Inversion of control -> DI dependency injection, AoP aspects oriented programs (o interceptors).
It is spring that is managing your app (the way you say to it using for example annotations).

### Context
Spring (external dependency) doesn´t know automatically what classes you are going to create.
You need to tell him what objects he needs to manage, and we are telling it with the Context.

`var context = new AnnotationConfigApplicationContext();` -> hidden in springboot (not in this project)

Some Options:
ClassPathXmlApplicationContext -> Beans, Classes etc with XML files, quite uncomfortable. Used in Spring3.
Now we prefer the annotation based option.

AnnotationConfigApplicationContext -> Annotation based (it accept a config class as parameter as
`AnnotationConfigApplicationContext(AppConfig.class)`)
If there is no parameter (`AnnotationConfigApplicationContext()`) it is an empty context (or with components if present in the project).

In spring context we have object instances with a unique identifier that we can use in the application.

### Annotation
Annotation (Java5) it add metadata to tell spring what we want the element to do.

It is like a label that explain the type of element.

### Beans
= it is a simple object instance (Initialization of a class).

There is a method, with Bean annotation, that return the new instance.
The name of the method is the spring unique identifier in the context (welcomeController in our case).
Guideline recommend to call the method with the controller class name.
```
    @Bean
    WelcomeController welcomeController(){
        return new WelcomeController(); //this instance will be added to the context
    }
```
When we run the context the first time the instance welcomeController() of type WelcomeController is created.

You can create more than one instance of the same class with different names:
```
    @Bean
    WelcomeController welcomeController(){
        return new WelcomeController(); //this instance will be added to the context
    }
    
    @Bean
    WelcomeController benvenutoController(){
        return new WelcomeController(); //this instance will be added to the context
    }
```
Managed by garbage collector, not needed to manually destroy the bean/instance.
If you create a `singleton` bean, the instance is maintained during all application life.

### Configuration classes
To create a config class in spring you need to use the @Configuration annotation.

Everything you declare in config class it is reflected in the way context that use it looks like.
Example: [AppConfig](src/main/java/config/AppConfig.java)

If the Config class is empty we still have an empty context:
```
    @Configuration
    public class AppConfig {
    }
```
If the Config class is not empty, and it is used in the context `AnnotationConfigApplicationContext(AppConfig.class)`,
the instances created in there are part of the context:
```
    @Configuration
    public class AppConfig {
        @Bean
        WelcomeController welcomeController(){
            return new WelcomeController(); //this instance will be added to the context
        }
    }
```
In this particular case we have an instance called welcomeController() in the context.

### Context and beans use
In main and in amy place of the application where you have the context you can retrieve the bean/instance:
```
    var context = new AnnotationConfigApplicationContext(AppConfig.class);
    WelcomeController welcome = context.getBean(WelcomeController.class);
```
If there is no bean of type WelcomeController we will have an NoSuchBeanDefinitionException.
if there is one instance existing, the instance is given.
if there is more then one instance existing, application will raise an error as it don´t know what instance you need.
-> to fix this case you can use the getBean with the needed instance name:
```
    var context = new AnnotationConfigApplicationContext(AppConfig.class);
    GreetingsController ciao = context.getBean("ciaoController", GreetingsController.class);
    GreetingsController hola = context.getBean("holaController", GreetingsController.class);
```

### Component 
To define a component in spring you need to use the stereotype @Component annotation in the class definition.
By doing that we are telling to spring to create an instance of this class and to add it to the content.
Example: [IAmAComponentController](src/main/java/controllers/IAmAComponentController.java)

This mean that if I have IAmAComponentController and AnnotationConfigApplicationContext(), the context is not empty 
anymore, we have the IAmAComponentController instance in there.

Spring is not searching automatically for components, you need to add annotations @ComponentScan in config to tell it:
```
@Configuration
@ComponentScan(basePackages = "controllers")
public class AppConfig {
}
```

In this case only ONE instance with default name (IAmAComponentController in this case) is created.
You can not create more instances of the same class using this annotation, use beans instead.
