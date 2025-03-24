public class Person {
    // Instance variables to store name, surname, and email
    String name;
    String surname;
    String email;

    // Constructor
    public Person (String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Setter method to set the name
    public void setName(String name) {
        this.name = name;
    }

    // Setter method to set the surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Setter method to set the email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method to get the name
    public String getName() {
        return name;
    }

    // Getter method to get the surname
    public String getSurname() {
        return surname;
    }

    // Getter method to get the email
    public String getEmail() {
        return email;
    }

    // Method to print details of the person
    public void printPersonDetails() {
        System.out.println("Name:" + getName());
        System.out.println("Surname:" + getSurname());
        System.out.println("Email:" + getEmail());
    }

}
