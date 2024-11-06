
package com.example.model;


public class Person {
    private int id;
    private String name;
    private String contactInformation;
    private String address;

    public Person(){
        
    }
    
    public Person(int id, String name, String contactInformation, String address) {
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", contactInformation=" + contactInformation + ", address=" + address + '}';
    }
    
}

