package hello;

public class Animal{

    String name;
    int age;

    public Animal(String name, int age){
    this.name = name;
    this.age = age;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
}


class Dog extends Animal{

    public Dog(String name, int age){
        super(name, age);
        this.name = name;
        this.age = age;
    }

    public void bark(){

        System.out.println("whoof");
    }

}

class Cat extends Animal{

    public Cat(String name, int age){
        super(name, age);
        this.name = name;
        this.age = age;
    }

    public void meow(){
        System.out.println("meow");

    }
}