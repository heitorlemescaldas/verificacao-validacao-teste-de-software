package br.edu.ifsp.testing.class06.exercises;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String city, String state, String zip) {
        if(!isValid(street, city, state, zip))
            throw new IllegalArgumentException("Endereço de entrega inválido.");
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    private boolean isValid(String street, String city, String state, String zip) {
        if(street == null || street.isBlank()) return false;
        if(city == null || city.isBlank()) return false;
        if(state == null || state.isBlank()) return false;
        if(zip == null || zip.isBlank()) return false;
        return true;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
