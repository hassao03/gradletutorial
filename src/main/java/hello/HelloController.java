package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/Animal")
    public List<Animal> getAnimal(){

        List <Animal> list = animalInfo();
        String name = "";
        int age =0;


        for(int i = 0; i < list.size(); i++) {

            name = name + "\n" + list.get(i).getName();
            age += list.get(i).getAge();


        }

        return list;
    }




    /*@RequestMapping("/name")
    public Animal Name(@RequestParam(value = "name", defaultValue = "No name given") String name){


        List <Animal> list = animalInfo();
        Animal animal = new Animal("Name Of animal", 0);
        String checkName="";


        for(int i = 0; i < list.size(); i++) {

            if(list.contains(name)) {
                checkName = list.get(i).getName();
                System.out.println(checkName);

            }

        }

        return animal;
    }*/

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/Animal/{name}")
    public Animal getAnimal(@PathVariable String name){

        List <Animal> list = animalInfo();

        return list.getAnimal(name);
    }*/

    public static List<Animal> animalInfo() {
        List<Animal> animalList = new ArrayList<Animal>();
        Animal dog = new Dog("Dog", 2);
        Animal cat = new Cat("Cat", 6);
        Animal lion = new Cat("lion", 9);
        animalList.add(dog);
        animalList.add(cat);
        animalList.add(lion);

        return (animalList);
    }

}
