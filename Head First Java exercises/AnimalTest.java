import java.util.*;
class AnimalTest{
    public static void main(String[] args){
        new AnimalTest().go();
    }
    private void go(){
        //Animal[] animals = {new Dog(), new Cat(), new Dog()};
        //Dog[] dogs = {new Dog(),new Dog(),new Dog()};
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Dog());
        dogs.add(new Dog());
        dogs.add(new Dog());
        dogs.add(new Dog());
        takeAnimal(animals);
        takeAnimal(dogs);
    }
    private void takeAnimal(ArrayList<? extends Animal> animal){
        for(Animal a : animal){
            a.eat();
        }
    }

}
class Animal{
    public void eat(){
        System.out.println("eating!");
    }
}
class Dog extends Animal{
    public void bark(){
        System.out.println("barking!");
    }
}
class Cat extends Animal{
    public void meow(){
        System.out.println("meowing!");
    }
}