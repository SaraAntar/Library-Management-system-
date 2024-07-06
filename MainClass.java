import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

public class MainClass {
    public static void main(String [] args) throws IOException {

        // opening files and scanning them to be accessed throughout the program
        File f1= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Admins.txt");
        Scanner a= new Scanner(f1);
        File f2= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Librarians.txt");
        Scanner l= new Scanner(f2);
        File f3= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Students.txt");
        Scanner s= new Scanner(f3);
        File f4= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt");
        Scanner b= new Scanner(f4);
        File f5= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\IssuedBooks.txt");
        Scanner ib= new Scanner(f5);
        Scanner sc= new Scanner(System.in);

        // defining a variable to be used in switch case
        int option;

        // creating objects from classes in order to access their methods
        Students students= new Students();
        librarian librarian= new librarian();
        Admin admin= new Admin();
        Books books= new Books();
        BooksIssued booksIssued= new BooksIssued();

        System.out.println();
        System.out.println("                               *********************************************************\n");
        System.out.println("                               *                                                       *\n");
        System.out.println("                               *                Welcome to Library System              *\n");
        System.out.println("                               *                                                       *\n");
        System.out.println("                               *********************************************************\n");

        do{
            System.out.println("\n1) Admin\n2) Librarian\n");
            System.out.print("Choose one of the options (click 0 if you want to exit): ");
            // taking an input from the user and assigning it into the options variable
            option= sc.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.print("\nPlease enter the username: ");
                    String username = sc.next();
                    admin.setName(username);
                    System.out.print("Please enter your password: ");
                    String password = sc.next();
                    admin.setPassword(password);

                    // defining and initializing flags to help us know whether the if condition was true or not inside the loop
                    int flag1 = 0, flag2 = 0;
                    // loop that reads from Admins file and iterate line by line to search for the specific username
                    while (a.hasNextLine()) {
                        // storing each line in line variable
                        String line = a.nextLine();
                        // splitting the line of data at commas and storing those data in lineArray2
                        String[] lineArray = line.split(",");

                        // condition to check whether the username of the admin exists in the file using equals function
                        if (lineArray[1].equalsIgnoreCase(admin.getName())) {
                            flag1 = 1;
                            // if the admin's username is found, check that the password entered is correct
                            if (!(lineArray[2].equals(admin.getPassword()))) {
                                flag2 = 1;
                            }
                            break;
                        }
                    }

                    // if flag1 remains 0 as it is, this means that the username wasn't found in the file
                    if (flag1 == 0) {
                        System.out.println("\nUsername Invalid, Please try again\n");
                    }
                    // if flag2 becomes equal to 1, this means that the user entered a wrong password
                    else if (flag2 == 1) {
                        System.out.println("\nPassword Invalid\n");
                    }
                    // otherwise, the user entered the correct username and password, then the admin will be provided
                    // with a list of options to choose from
                    else {
                        int choice;
                        do{

                            System.out.println();
                            System.out.println("                             ******************************************************************\n");
                            System.out.println("                             *                         Admin View                             *\n");
                            System.out.println("                             *                                                                *\n");
                            System.out.println("                             *         1: Add a student                                       *\n");
                            System.out.println("                             *         2: Remove a student                                    *\n");
                            System.out.println("                             *         3: Add a librarian                                     *\n");
                            System.out.println("                             *         4: Remove a librarian                                  *\n");
                            System.out.println("                             *         5: Add another admin                                   *\n");
                            System.out.println("                             *         6: Remove another admin                                *\n");
                            System.out.println("                             *                                                                *\n");
                            System.out.println("                             ******************************************************************\n");

                        System.out.print("\nChoose one of the options (click 0 if you want to log out): ");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {

                                System.out.print("Please enter your Name: ");
                                String student_name = sc.next();
                                // condition to check whether the student name contains only characters or not using matches function
                                if (!student_name.matches("[a-zA-Z]+")) {
                                    System.out.println("Please enter a correct name without numbers or symbols");
                                    break;
                                }

                                // receiving data from the user and storing them in variables
                                System.out.print("Please enter your ID: ");
                                String ID = sc.next();
                                System.out.print("Please enter your Birthday: ");
                                String birthday = sc.next();
                                System.out.print("Please enter your Email: ");
                                String email = sc.next();
                                System.out.print("Please enter your mobile_number: ");
                                String mobile_number = sc.next();

                                // calling function isValid() to check whether the mobile number entered by the user is valid or not
                                if (!isValid(mobile_number)) {
                                    System.out.println("Please enter a valid number with 11 digits");
                                    break;
                                }

                                // using try and catch so that if the file couldn't open in the Students class,
                                // catch would handle the error by IOException
                                // IOException is a Java exception that occurs when an IO operation fails.
                                try{
                                    // creating object from class student
                                    Students students1 = new Students(ID, mobile_number, student_name, birthday, email);
                                    // calling Add_Student() method from Students class using students1 object to add
                                    // a new student in the Students text file
                                    students1.Add_Student();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 2 -> {
                                // asking the user to enter the ID of the student to remove from the file
                                System.out.println("Please enter the ID of the student you want to remove: ");
                                String ID = sc.next();
                                try{
                                    // calling the ID setter from the class to store the ID
                                    students.setID(ID);
                                    // calling Remove_Student() method from the Students class using students object to
                                    // remove the specific student from the Students text file
                                    students.Remove_Student();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 3 -> {
                                System.out.print("Please enter your Name: ");
                                String librarian_name = sc.next();

                                // condition to check whether the student name contains only characters or not using matches function
                                if (!librarian_name.matches("[a-zA-Z]+")) {
                                    System.out.println("Please enter a correct name without numbers or symbols");
                                    break;
                                }
                                System.out.print("Please enter your new Password: ");
                                String librarian_pass = sc.next();
                                System.out.print("Please enter your ID: ");
                                String ID = sc.next();

                                try{
                                    // creating librarian1 object from Librarian class
                                    librarian librarian1 = new librarian(ID, librarian_name, librarian_pass);
                                    // calling Add_Librarian() method from Librarian class using librarian1 object to add
                                    // a new librarian in the Students text file
                                    librarian1.Add_Librarian();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 4 -> {

                                System.out.println("Please enter the ID of the librarian you want to remove: ");
                                String ID = sc.next();
                                try{
                                    librarian.setID(ID);
                                    // calling Remove_Librarian() method from the librarian class using students object to
                                    // remove the specific librarian from the Librarians text file
                                    librarian.Remove_Librarian();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 5 -> {
                                System.out.print("Please enter your Name: ");
                                String admin_name = sc.next();
                                // condition to check whether the student name contains only characters or not using matches function
                                if (!admin_name.matches("[a-zA-Z]+")){
                                    System.out.println("Please enter a correct name without numbers or symbols");
                                    break;
                                }
                                System.out.print("Please enter your new Password: ");
                                String admin_pass = sc.next();
                                System.out.print("Please enter your ID: ");
                                String ID = sc.next();

                                try{
                                    // creating admin1 object from Admin class
                                    Admin admin1 = new Admin(ID, admin_name, admin_pass);
                                    // calling Add_Admin() method from admin class using admin1 object to add
                                    // a new admin in the Admins text file
                                    admin1.Add_Admin();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 6 -> {
                                System.out.println("Please enter the ID of the admin you want to remove: ");
                                String ID = sc.next();
                                try{
                                    admin.setID(ID);
                                    // calling Remove_Admin() method from the Admin class using admins object to
                                    // remove the specific admin from the admin text file
                                    admin.Remove_Admin();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }
                            // if the user entered 0, he will log out.
                            case 0 -> {}
                        }
                    } while (choice!=0);
                }
                }

                case 2 -> {
                    System.out.println("\nPlease enter your username: ");
                    String lib_username = sc.next();
                    librarian.setName(lib_username);
                    System.out.println("Please enter your password: ");
                    String lib_password = sc.next();
                    librarian.setPassword(lib_password);

                    // defining and initializing flags to help us know whether the if condition was true or not inside the loop
                    int flag1 = 0, flag2 = 0;
                    // loop that reads from Librarians file and iterate line by line to search for the specific username
                    while (l.hasNextLine()) {
                        // storing each line in line variable
                        String line = l.nextLine();
                        // splitting the line of data at commas and storing those data in lineArray
                        String[] lineArray = line.split(",");
                        // condition to check whether the username of the librarian exists in the file using equals function
                        if (lineArray[1].equalsIgnoreCase(librarian.getName())) {
                            flag1 = 1;
                            // if the librarian's username is found, check that the password entered is correct
                            if (!(lineArray[2].equals(librarian.getPassword()))) {
                                flag2 = 1;
                            }
                            break;
                        }
                    }

                    // if flag1 remains 0 as it is, this means that the username wasn't found in the file
                    if (flag1 == 0) {
                        System.out.println("\nUsername Invalid, Please try again\n");
                    }
                    // if flag2 becomes equal to 1, this means that the user entered a wrong password
                    else if (flag2 == 1) {
                        System.out.println("\nPassword Invalid\n");
                    }
                    // otherwise, the user entered the correct username and password, then the admin will be provided
                    // with a list of options to choose from
                    else {
                        int choice;
                        do{

                            System.out.println();
                            System.out.println("                             ******************************************************************\n");
                            System.out.println("                             *                         Librarian View                         *\n");
                            System.out.println("                             *                                                                *\n");
                            System.out.println("                             *         1: Add a book                                          *\n");
                            System.out.println("                             *         2: Delete a book                                       *\n");
                            System.out.println("                             *         3: Issue a book                                        *\n");
                            System.out.println("                             *         4: Return a book                                       *\n");
                            System.out.println("                             *         5: View books                                          *\n");
                            System.out.println("                             *         6: View issued books                                   *\n");
                            System.out.println("                             *         7: Log out                                             *\n");
                            System.out.println("                             *         8: Search for a book                                   *\n");
                            System.out.println("                             *                                                                *\n");
                            System.out.println("                             ******************************************************************\n");

                            System.out.print("\nChoose one of the options: ");
                            choice = sc.nextInt();

                            switch (choice) {

                            case 1 -> {
                                String x= sc.nextLine();
                                System.out.print("Please enter Book Name: ");
                                String book_name = sc.nextLine();
                                System.out.print("Please enter Book ID: ");
                                String book_ID = sc.nextLine();
                                System.out.print("Please enter Author Name: ");
                                String author_name = sc.nextLine();
                                System.out.print("Please enter Available Quantity: ");
                                int available_quantity = sc.nextInt();
                                System.out.print("Please enter Issued Quantity: ");
                                String issued_quantity = sc.next();

                                try{
                                    // creating books1 object from Book class
                                    Books books1 = new Books(book_ID, available_quantity, issued_quantity, book_name, author_name);
                                    // calling Add_Book() method from Books class using books1 object to add
                                    // a new book in the Books text file
                                    books1.Add_Book();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 2 -> {
                                System.out.println("Please enter the ID of the book you want to delete: ");
                                String ID = sc.next();
                                try{
                                    // calling the ID setter from the class to store the ID
                                    books.setBook_ID(ID);
                                    // calling Delete_Book() method from the Books class using books object to
                                    // delete the specific book from the Books text file
                                    books.Delete_Book();
                                }
                                catch (IOException error){
                                    System.out.println(error);
                                }
                            }

                            case 3 -> {
                                System.out.println("Please enter the student's name: ");
                                String Name = sc.next();
                                // calling the name setter from the Students class to store the student's name
                                booksIssued.setName(Name);
                                // initializing FLAG to indicate whether the condition inside the while loop is true or not
                                // to know whether the student was found in the Students file or not
                                int FLAG = 0;
                                // loop that iterates through the students text file line by line
                                while (s.hasNextLine()) {
                                    String line = s.nextLine();
                                    // splitting the line whose data are separated by commas and storing those data in lineArray
                                    String[] lineArray = line.split(",");
                                    // check whether the student's name exits in the Students File
                                    if (Objects.equals(lineArray[1], booksIssued.getName())) {
                                        FLAG = 1;
                                        booksIssued.setID(lineArray[0]);
                                    }
                                }

                                // if FLAG remains 0 this means that the student is not in the Students file
                                if (FLAG == 0) {
                                    System.out.println("\nStudent is not found in the Student's file" +
                                            "\nPlease add the student first");
                                }
                                else {
                                    System.out.println("Please enter the book ID: ");
                                    String ID = sc.next();
                                    booksIssued.setBook_ID(ID);

                                    int FLAG1 = 0;
                                    // loop that iterates through the Books file and reads it line by line
                                    while (b.hasNextLine()) {
                                        String line = b.nextLine();
                                        // splitting the line whose data are separated by commas and storing those data in lineArray
                                        String[] lineArray = line.split(",");

                                        // condition to search for the book in the Books file
                                        if (Objects.equals(lineArray[0], booksIssued.getBook_ID())) {
                                            FLAG1 = 1;
                                            // accessing the third and fourth elements in the array which represent the
                                            // available_quantity and issued_quantity of the book and converting them from
                                            // String type into integer type using Integer.parseInt() function to change their values
                                            int available_quantity = Integer.parseInt(lineArray[3]);
                                            int issued_quantity = Integer.parseInt(lineArray[4]);

                                            // a condition to check whether the quantity of the book is greater than 1
                                            // so that we can issue that book
                                            if (available_quantity > 0) {
                                                // decreasing the available_quantity and increasing issued_quantity of the book
                                                available_quantity--;
                                                issued_quantity++;
                                                // after manipulating the values of available_quantity and issued_quantity,
                                                // we convert them again into String type using Integer.toString() function
                                                // and storing them back into the lineArray
                                                lineArray[3] = Integer.toString(available_quantity);
                                                lineArray[4] = Integer.toString(issued_quantity);

                                                // StringBuilder class can be used when you want to modify a string without creating a new object
                                                StringBuilder sb = new StringBuilder();
                                                // enhanced for loop to create a string of elements separated by commas from
                                                // the data stored in the lineArray using append() function
                                                for (String word : lineArray) {
                                                    sb.append(word).append(",");
                                                }
                                                // storing the new string of elements separated by the commas in new_line variable
                                                // and deleting the last element in the string which is a comma using deleteCharAt() function
                                                String new_line = sb.deleteCharAt(sb.length() - 1).toString();

                                                // Charset -> Character encoding tells computers how to interpret digital data into letters, numbers and symbols.
                                                // Charset UTF-8 is to address ASCII's shortcomings and can translate almost every language in the world.
                                                Charset charset = StandardCharsets.UTF_8;

                                                // opening file we are going to update a line in
                                                String content = new String(Files.readAllBytes(
                                                        Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt")), charset);
                                                // updating the line with the new_line containing the new available_quantity and issued_quantity
                                                content = content.replaceAll(line, new_line);
                                                // storing this new_line in Books text file
                                                Files.write(Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt"), content.getBytes(charset));

                                                System.out.println("Please enter the procedure ID: ");
                                                int procedure_id = sc.nextInt();
                                                try {
                                                    // calling the setters of the procedure_id and returned attributes to set their values
                                                    booksIssued.setProcedure_ID(procedure_id);
                                                    booksIssued.setReturned("False");
                                                    // calling Add_Record() method from BooksIssued class to add a new line
                                                    // in the IssuedBooks text file
                                                    booksIssued.Add_Record();
                                                    System.out.println("\nBook with ID " + booksIssued.getBook_ID() + " is issued successfully!!");
                                                    break;
                                                }
                                                catch (IOException error) {
                                                    System.out.println(error);
                                                }
                                            }
                                            // this means that all quantity is issued, so it's zero and the student
                                            // can't borrow it at this time
                                            else {
                                                System.out.println("\nThe book with ID " + books.getBook_ID() + " is not available");
                                            }
                                        }
                                    }
                                    // if FLAG1 remains 0, this means that the book is not present in the file
                                    if (FLAG1 == 0) {
                                        System.out.println("\nBook is not found in the Book's file" +
                                                "\nPlease try again");
                                    }
                                }
                            }

                            case 4 -> {
                                System.out.println("Please enter the Book's ID: ");
                                String Book_ID = sc.next();
                                books.setBook_ID(Book_ID);
                                System.out.println("Please enter the Student's ID: ");
                                String student_id = sc.next();
                                // calling the setID() method from the Books class to set the student's ID
                                books.setID(student_id);

                                // loop that iterates through the Books file and reads it line by line
                                while (b.hasNextLine()) {
                                    String line = b.nextLine();
                                    // splitting the line whose data are separated by commas and storing those data in lineArray
                                    String[] lineArray = line.split(",");

                                    // condition to search for the book in the Books file
                                    if (Objects.equals(lineArray[0], books.getBook_ID())) {

                                            // accessing the third and fourth elements in the array which represent the
                                            // available_quantity and issued_quantity of the book and converting them from
                                            // String type into integer type using Integer.parseInt() function to change their values
                                            int available_quantity = Integer.parseInt(lineArray[3]);
                                            int issued_quantity = Integer.parseInt(lineArray[4]);

                                            // increasing the available_quantity and decreasing issued_quantity of the book
                                            available_quantity++;
                                            issued_quantity--;
                                            // after manipulating the values of available_quantity and issued_quantity,
                                            // we convert them again into String type using Integer.toString() function
                                            // and storing them back into the lineArray
                                            lineArray[3] = Integer.toString(available_quantity);
                                            lineArray[4] = Integer.toString(issued_quantity);

                                            // StringBuilder class can be used when you want to modify a string without creating a new object
                                            StringBuilder sb = new StringBuilder();
                                            // enhanced for loop to create a string of elements separated by commas from
                                            // the data stored in the lineArray using append() function
                                            for (String word : lineArray) {
                                                sb.append(word).append(",");
                                            }
                                            // storing the new string of elements separated by the commas in new_line variable
                                            // and deleting the last element in the string which is a comma using deleteCharAt() function
                                            String new_line = sb.deleteCharAt(sb.length() - 1).toString();

                                            // Charset -> Character encoding tells computers how to interpret digital data into letters, numbers and symbols.
                                            // Charset UTF-8 is to address ASCII's shortcomings and can translate almost every language in the world.
                                            Charset charset = StandardCharsets.UTF_8;

                                            // opening file we are going to update a line in
                                            String content = new String(Files.readAllBytes(
                                                    Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt")), charset);
                                            // updating the line with the new_line containing the new available_quantity and issued_quantity
                                            content = content.replaceAll(line, new_line);
                                            // storing this new_line in Books text file
                                            Files.write(Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt"), content.getBytes(charset));

                                    }
                                }
                                // loop to iterate through the issued books text file
                                while (ib.hasNextLine()) {
                                    String line = ib.nextLine();
                                    String[] lineArray = line.split(",");

                                    if (Objects.equals(lineArray[1], books.getBook_ID())) {
                                        if (Objects.equals(lineArray[2], books.getID())) {
                                            // when we find the ID of the book we want to return, change the returned mode into True
                                            lineArray[3] = "True";

                                            StringBuilder stringBuilder = new StringBuilder();
                                            for (String word : lineArray) {
                                                stringBuilder.append(word).append(",");
                                            }
                                            String new_line = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();

                                            Charset charset = StandardCharsets.UTF_8;
                                            String content1 = new String(Files.readAllBytes(
                                                    Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\IssuedBooks.txt")), charset);
                                            content1 = content1.replaceAll(line, new_line);
                                            Files.write(Path.of("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\IssuedBooks.txt"), content1.getBytes(charset));

                                            System.out.println("\nBook with ID " + books.getBook_ID() + " is returned successfully!!");
                                            break;
                                        }
                                    }
                                }
                            }
                            case 5 -> {
                                System.out.println("\n\t\t\t~ Displaying the books in the library: ~\n");
                                // looping through the Books text file line by line
                                while (b.hasNextLine()) {
                                    String line = b.nextLine();
                                    // printing all of the file to be displayed to the user
                                    System.out.println(line + "\n");
                                }
                            }
                            case 6 -> {
                                System.out.println("\n\t\t\t~ Displaying the issued books in the library: ~\n");
                                // looping through the IssuedBooks text file line by line
                                while (ib.hasNextLine()) {
                                    String line = ib.nextLine();
                                    // printing all of the file to be displayed to the user
                                    System.out.println(line + "\n");
                                }
                            }
                            case 7 -> {
                                System.out.println("Are you sure you want to log out? (Yes/No): ");
                                // asking the user to enter yes or no in case of logging out and storing the answer in y_n variable
                                String y_n= sc.next();
                                // in case the suer wrote yes to log out he will be asked to click 0 to confirm
                                // we use equalsIgnoreCase() function because it's unknown whether the user will enter Yes or yes or YES
                                // so, it's used to ignore lower/upper cases
                                if (y_n.equalsIgnoreCase("YES")){
                                    System.out.println("Please click 0 to log out: ");
                                    choice= sc.nextInt();
                                }
                            }
                            case 8 -> {
                                System.out.println("Please enter the ID of the book: ");
                                String Book_ID = sc.next();
                                // calling setBook_ID() method from Books class to set the book ID
                                books.setBook_ID(Book_ID);

                                int flag = 0;
                                // loop to iterate through the Books file to search for the book
                                while (b.hasNextLine()) {
                                    String line = b.nextLine();
                                    String[] lineArray = line.split(",");
                                    // condition to check whether the ID of the book is equal to the ID of the book in
                                    // the specific line we are reading currently
                                    if (Objects.equals(lineArray[0], books.getBook_ID())) {
                                        flag = 1;
                                        // print the data of the specific book we are searching for
                                        System.out.println("\n" + line);
                                        break;
                                    }
                                }
                                // if the flag remained 0, this means that the book isn't found in the file
                                if (flag == 0) {
                                    System.out.println("Book with ID " + books.getBook_ID() + " is not found!");
                                }
                            }
                        }

                    } while (choice!=0);
                }
            }
                case 0 -> {}

                default -> System.out.println("\nPlease enter a right option\n");
            }
            // closing the scanner when finished using it
        }
        while(option!=0);
        sc.close();
    }

    // function that checks whether the mobile number entered by the user is valid or not
    public static boolean isValid(String number)
    {
        // valid number should start with 0 then 1, and then another 9 digits from 0 to 9, so the total should be 11 digits
        Pattern pattern = Pattern.compile("(0)?[1][0-9]{9}");

        //Pattern class contains matcher() method to find the matching between the given number and regular pattern
        Matcher matcher = pattern.matcher(number);
        // return true if the number is valid, false otherwise
        return (matcher.find() && matcher.group().equals(number));
    }
}