package org.example.laboratorio;

@Component
public class HelloController {
    @getMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}