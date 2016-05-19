package com.example.maya.infinitescrollviewwithrxjava.RecycleViewCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maya on 4/11/2016.
 */
public class Person {
    public String name;
    public String age;

    public Person() {


    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;

    }
    public List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    public void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
    }
}

