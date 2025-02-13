package br.edu.ifsp.testing.class02.slides;

public class DriverLicense {
    public int calculateValidity(int age){
        if(age < 0) throw new IllegalArgumentException("Age must not be negative: " + age);
        if(age < 18) return 0;
        if(age < 50) return 10;
        if(age < 70) return 5;
        return 3;
    }

//    //Silly implementation
//    public int calculateValidity(int age){
//        if(age == 0) return 0;
//        // ...
//        if(age == 17) return 0;
//        if(age == 18) return 10;
//        // ...
//        if(age == 49) return 10;
//        if(age == 50) return 5;
//        // ...
//        if(age == 69) return 5;
//        return 3;
//    }
}
