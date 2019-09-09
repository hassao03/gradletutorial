package hello;
import org.joda.time.LocalTime;
import java.util.*;
public class HelloWorld{
    public static void main(String[] args){
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        Animal result = animalInfo();
        System.out.println("Name: " + result.getName() + " " + "Age: " + result.getAge());

        for(int i =0; i < result; i++){
            System.out.println("Name: " + result.getName() + " " + "Age: " + result.getAge());
        }


    }

    public static Animal animalInfo() {
        Animal animal = new Animal("Has", 4);
        //String name = animal.setName("Dog");
        //int age = animal.setAge(2);
        String name = "Dog";
        int age = 2;

        return new Animal(name, age);
    }

}