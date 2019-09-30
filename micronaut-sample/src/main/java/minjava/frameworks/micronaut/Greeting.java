package minjava.frameworks.micronaut;

public class Greeting {

    private final String name;
    private final String message;


    public Greeting(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
