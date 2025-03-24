import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

// Instance variables to store ticket details and person associated with the ticket
public class Ticket {
    char row;
    int seat;
    double price;
    Person person;

    // Constructor
    public Ticket (char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void setRow(char row) { // Setter method to set the row
        this.row = row;
    }

    public void setSeat(int seat) {  // Setter method to set the seat number
        this.seat = seat;
    }

    public void setPrice(double price) {  // Setter method to set the price
        this.price = price;
    }

    public void setPerson(Person person) {  // Setter method to set the person
        this.person = person;
    }

    public char getRow() {  // Getter method to get the row
        return row;
    }

    public int getSeat() {  // Getter method to get the seat number
        return seat;
    }

    public double getPrice() {  // Getter method to get the price
        return price;
    }

    public Person getPerson() {  // Getter method to get the person
        return person;
    }

    // Method to print the details
    public void printTicketDetails(){
        System.out.println("Row: " + getRow());
        System.out.println("Seat: " + getSeat());
        System.out.println("Price: £" + getPrice());
        person.printPersonDetails();
    }

    // Method to save ticket details to a text file
    public void save() {
        String fileName = getRow() + String.valueOf(getSeat()) + ".txt";  // Create a file name
        File file = new File (fileName);  // Create a new file object

        try {
            boolean file_created = file.createNewFile();  // Create a new file
            if (file_created) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter (file);

                // Write ticket detail to the file
                fileWriter.write("Ticket details: \n");
                fileWriter.write("Row: " + getRow() +"\n");
                fileWriter.write("Seat: " + getSeat() + "\n");
                fileWriter.write("Price: £" + getPrice() + "\n" );
                fileWriter.write("Name: " + person.getName() +"\n");
                fileWriter.write("Surname: " + person.getSurname() +"\n");
                fileWriter.write("Email: " + person.getEmail() +"\n");

                fileWriter.close();  // Close the file writer
            }
            else {
                if (file.exists()) {
                    System.out.println("File already exists.");
                }
                else {
                    System.out.println("Error with creating the file");
                }
            }
        }
        catch (IOException e) {  // To handle the exceptions
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
