package hello;
import org.joda.time.LocalTime;
import java.util.*;
public class HelloWorld{
    public static void main(String[] args){
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        List <Animal> list = animalInfo();

        for(int i = 0; i < list.size(); i++) {
            System.out.println("Name: " + list.get(i).getName() + " " + "Age: " + list.get(i).getAge());
        }



    }



    public static List<Animal> animalInfo() {
        List<Animal> animalList = new ArrayList<Animal>();
        Animal dog = new Dog("Dog", 2);
        Animal cat = new Cat("Cat", 6);
        animalList.add(dog);
        animalList.add(cat);

        return (animalList);
    }

}