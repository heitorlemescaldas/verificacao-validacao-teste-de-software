package br.edu.ifsp.testing.class04;

public class Voter {
    public boolean isOptionalVoter(int age){
        if(age < 16) throw new IllegalArgumentException("Age must greater than 16.");
        return age < 18 || age >= 70;
    }
}
