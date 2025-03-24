import java.util.*;

public class PlaneManagement {
    // Scanner object to get the user inputs
    private static Scanner userInput = new Scanner(System.in);
    private static Ticket [] soldTickets = new Ticket[52];  // Array to store the sold tickets
    private static int numSoldTickets = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application"); // Welcome massage

        // Ragged array to Manage the seating
        int[][] seatManagement = new int[4][];
        seatManagement[0] = new int[14];
        seatManagement[1] = new int[12];
        seatManagement[2] = new int[12];
        seatManagement[3] = new int[14];

        boolean go = true;

        // Loop for the Main Menu
        while (go) {
            displayMenu();

            if (userInput.hasNextInt()) { // Check if the user input is an integer
                int choice = userInput.nextInt();
                switch (choice) {
                    case 1:
                        buy_seat(seatManagement); // Method call to buy a seat
                        break;
                    case 2:
                        cancel_seat(seatManagement); // Method call to cancel a seat
                        break;
                    case 3:
                        find_first_available(seatManagement); // Method call to find the first available seat
                        break;
                    case 4:
                        show_seating_plan(seatManagement);  // Method call to show the seating plan
                        break;
                    case 5:
                        if (numSoldTickets != 0) {
                            print_ticket_info(soldTickets); // Method call for print the ticket information
                        }
                        else {
                            System.out.println("No tickets are booked. Please book a seat first.");
                        }
                        break;
                    case 6:
                        search_ticket(soldTickets, seatManagement); // Method call to search for a ticket
                        break;
                    case 0:
                        go = false; // To exit the loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again."); // Displayed for an invalid option
                        break;
                }

            }
            else {
                System.out.println("Invalid input. Please enter a valid option."); // Displayed for a non-integer input
                userInput.next();
            }
        }
    }

    // Method to display the Main Menu
    private static void displayMenu() {
        System.out.println("*************************************************");
        System.out.println("*                 MENU OPTIONS                  *");
        System.out.println("*************************************************");
        System.out.println("     1) Buy a seat                               ");
        System.out.println("     2) Cancel a seat                            ");
        System.out.println("     3) Find first available seat                ");
        System.out.println("     4) Show seating plan                        ");
        System.out.println("     5) Print tickets information and total sales");
        System.out.println("     6) Search tickets                           ");
        System.out.println("     0) Quit                                     ");
        System.out.println("*************************************************");
        System.out.println("Please enter an option: ");
    }

    // Method to buy a seat
    private static void buy_seat(int[][] seatArray) {
        char rowLetter;
        do {  // Loop until the valid row is entered
            System.out.println("Enter the seat you need to book.");
            System.out.println("Please enter the row (A - D)");
            rowLetter = userInput.next().toUpperCase().charAt(0);
        } while (!(rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C' || rowLetter == 'D'));

        // Convert row letter to number
        int rowNumber = convertRowLetterToNumber(rowLetter);

        int seatNumber = 0;  // Default seat number
        do {   // Loop until the valid seat number is entered
            try {
                System.out.println("Please enter the seat number (1 - 14)");
                seatNumber = userInput.nextInt();
            }
            catch (InputMismatchException e) {   // To handle the exceptions
                System.out.println("Invalid input. Enter a number.");
                userInput.nextLine();
                continue;
            }
        }while (seatNumber < 1 || seatNumber > seatArray [rowNumber].length);

        // Check if the seat is available
        if (seatArray [rowNumber][seatNumber - 1] == 0) {
            System.out.println("Seat booked successfully.");
            numSoldTickets += 1;
            seatArray [rowNumber][seatNumber - 1] = 1;

            // Calculate the price of the seat
            int seatPrice = seatPrice(seatNumber);

            // Prompts to get the personal information
            System.out.println("Enter your name: " );
            String name = userInput.next();

            System.out.println("Enter your surname: " );
            String surname = userInput.next();

            String email;
            boolean validEmail = false;

            do { // Loop until the valid email is entered.
                System.out.println("Enter your email: ");
                email = userInput.next();
                if (email.contains("@") && email.contains(".")) {  // Check if the user entered a valid email
                    validEmail = true;
                } else {
                    System.out.println("Invalid email format. Please enter a valid email.");
                }
            } while (!validEmail);

            Person person = new Person (name, surname, email);  // Create a Person object
            Ticket ticket = new Ticket (rowLetter, seatNumber, seatPrice, person); // Create a Ticket object

            // Store the ticket in the soldTicket array
            for (int i = 0; i < soldTickets.length; i++) {
                if (soldTickets[i] == null) {
                    soldTickets[i] = ticket;
                    System.out.println("Seat booking completed.");
                    break;
                }
            }

            ticket.save(); // Save the ticket information in a text file
        }

        else {
            System.out.println("Seat not available");
        }

    }

    // Method to convert the row letter to number
    private static int convertRowLetterToNumber(char rowLetter) {
        int rowNum = 4;  // Default value
        switch (rowLetter) {
            case 'A':
                rowNum = 0;
                break;
            case 'B':
                rowNum = 1;
                break;
            case 'C':
                rowNum = 2;
                break;
            case 'D':
                rowNum = 3;
                break;
            default:
                System.out.println("Enter a valid row letter");
                break;
        }
        return rowNum;
    }

    // Method to cancel a seat
    private static void cancel_seat(int[][] seatArray) {
        char rowLetter;
        do {  // Loop until the valid row is entered
            System.out.println("Enter the seat you need to cancel.");
            System.out.println("Please enter the row number (A - D)");
            rowLetter = userInput.next().toUpperCase().charAt(0);
        } while (!(rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C' || rowLetter == 'D'));

        // Convert row letter to number
        int rowNumber = convertRowLetterToNumber(rowLetter);

        int seatNumber = 0;   // Default seat number
        do {   // Loop until the valid seat number is entered
            try {
                System.out.println("Please enter the seat number (1 - 14)");
                seatNumber = userInput.nextInt();
            }
            catch (InputMismatchException e) {   // To handle the exceptions
                System.out.println("Invalid input. Enter a number.");
                userInput.nextLine();
                continue;
            }
        } while (seatNumber < 1 || seatNumber > seatArray [rowNumber].length);

        if (seatArray [rowNumber][seatNumber - 1] == 1) {  // Check if the seat is booked
            System.out.println("Seat cancelled successfully.");
            seatArray [rowNumber][seatNumber - 1] = 0;  // Cancel the seat

            for (int i = 0; i < soldTickets.length; i++) {
                Ticket ticket = soldTickets[i];  // Creating temporary ticket
                // Check if the ticket object exists and if it corresponds to the specified row and seat
                if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                    soldTickets [i] = null;
                    System.out.println("Cancellation done.");
                    break;

                }
            }
        }

        else {
            System.out.println("Seat not available");
        }
    }

    // Method to find the first available seat
    private static void find_first_available(int[][] seatArray) {
        boolean seatFound = false;
        for (int i = 0 ; i < seatArray.length; i++  ) {  // Loop through each row of the SeatArray
            for (int j = 0 ; j < seatArray [i].length; j++  ) {  // Loop through each seat of the row
                if (seatArray [i][j] == 0) {  // Check if the seat is available
                    // Print the seat location
                    System.out.println("Seat found in: " + convertnumberTorowLetter(i)+ (j+1));
                    seatFound = true;
                    break;
                }
            }
            if (seatFound) {   // If the seat is found break out of the loop
               break;
            }

        }

        if (!seatFound) {
            System.out.println("No seats are available");
        }

    }

    // Method to convert the row number to its row letter
    private static char convertnumberTorowLetter(int i) {
        char rowLetter = 'E';  // Default row letter
        switch (i) {
            case 0:
                rowLetter = 'A';
                break;
            case 1:
                rowLetter = 'B';
                break;
            case 2:
                rowLetter = 'C';
                break;
            case 3:
                rowLetter = 'D';
                break;
            default:
                System.out.println("Enter a valid row letter");  // Displayed if an invalid row is provided
                break;
        }
        return rowLetter;
    }

    // Method to display the seating plan
    private static void show_seating_plan(int[][] seatArray){
        System.out.println("Seating Plan :");
        for (int i = 0 ; i < seatArray.length; i++  ) {
            for (int j = 0; j < seatArray [i].length; j++) {
                // Print 'O' for available seats and 'x' for booked seats
                if (seatArray [i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("x ");
                }
            }

            System.out.println();

        }
    }

    // Method to calculate the price of a seat
    private static int seatPrice(int number) {
        int price;
        if (number <= 5){
           price = 200;
           return price;
        }
        else if (number <= 9) {
            price = 150;
            return price;
        }
        else {
            price = 180;
            return price;
        }
    }

    // Method to print the ticket information
    private static void print_ticket_info (Ticket [] array) {
        double total = 0.0;  // Default total
        for (int i = 0; i < array.length; i++) {
            Ticket ticket = array[i];
            if (array [i] != null) {
                ticket.printTicketDetails();
                total += ticket.getPrice();
            }
        }
        System.out.println("Your total amount is: £" + total);   // Print the total amount

    }

    // Method to search for a ticket
    private static void search_ticket (Ticket [] array,int[][] seatArray) {
        char rowLetter;
        do {   // Loop until the valid row is entered
            System.out.println("Enter the seat you need to search.");
            System.out.println("Please enter the row number (A - D)");
            rowLetter = userInput.next().toUpperCase().charAt(0);
        } while (!(rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C' || rowLetter == 'D'));

        // Convert row letter to number
        int rowNumber = convertRowLetterToNumber(rowLetter);

        int seatNumber = 0;   // Default seat number
        do {   // Loop until the valid seat number is entered
            try {
                System.out.println("Please enter the seat number (1 - 14)");
                seatNumber = userInput.nextInt();
            }
            catch (InputMismatchException e) {   // To handle the exceptions
                System.out.println("Invalid input. Enter a number.");
                userInput.nextLine();
                continue;
            }
        } while (seatNumber < 1 || seatNumber > seatArray [rowNumber].length);

        boolean seatAvailable = true;
        for (int i = 0; i < array.length; i++) {
            Ticket ticket = array[i];

            // Check if the ticket object exists and if it corresponds to the specified row and seat
            if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                System.out.println("Already booked.");
                ticket.printTicketDetails();  // Print the ticket information
                seatAvailable = false;
                break;
            }
        }
        if (seatAvailable) {
            System.out.println("This seat is available."); // Print if the seat is available
        }


    }
}
