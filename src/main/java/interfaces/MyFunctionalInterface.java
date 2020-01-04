package interfaces;

import entities.Person;

@FunctionalInterface
public interface MyFunctionalInterface {

    void run(Person p1,Person p2);

    default void run(){
        System.out.println("Default method example");
    }

    default void run(Person p){
        System.out.println("Another default method that prints name " + p.getName());
    }

    static void hello(){
        System.out.println("Static method exmaple");
    }
}
