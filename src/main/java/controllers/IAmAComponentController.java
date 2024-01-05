package controllers;

import org.springframework.stereotype.Component;

@Component
public class IAmAComponentController {
}
//We are telling to spring to create an instance of this class and to add it to the content.
//No need to add it in AppConfig, it is already in the context (if @ComponentScan annotation in AppConfig is present).