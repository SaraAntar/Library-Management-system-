import java.io.*;
import java.util.Scanner;

public class Books extends Students{

    // defining attributes
    private int Available_Quantity;
    private String Book_Name, Author_Name, Book_ID, Issued_Quantity;

    // parametrized constructor
    Books(String Book_ID, int Available_Quantity, String Issued_Quantity, String Book_Name, String Author_Name)
    {
        setAuthor_Name(Author_Name);
        setBook_Name(Book_Name);
        setAvailable_Quantity(Available_Quantity);
        setBook_ID(Book_ID);
        setIssued_Quantity(Issued_Quantity);
    }
    // non-parametrized constructor
    public Books() {
    }

    // defining the setters and getters
    public int getAvailable_Quantity() {
        return Available_Quantity;
    }

    public String getBook_ID() {
        return Book_ID;
    }

    public String getIssued_Quantity() {
        return Issued_Quantity;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_ID(String Book_ID) {
        this.Book_ID = Book_ID;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public void setAvailable_Quantity(int available_Quantity) {
        Available_Quantity = available_Quantity;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public void setIssued_Quantity(String issued_Quantity) {
        Issued_Quantity = issued_Quantity;
    }

    // defining a function that adds a new book inside Books text file
    public void Add_Book() throws IOException{

        // initializing fw_book variable from FileWriter which opens the text file and write inside it
        // true indicates that the mode of writing is append.
        FileWriter fw_book= new FileWriter("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt",true);

        // writing data inside the file using fw_book variable which uses write function
        fw_book.write("\n"+getBook_ID()+","+ getBook_Name()+","+ getAuthor_Name()+","+ getAvailable_Quantity()+","+ getIssued_Quantity());

        // closing the file
        fw_book.close();
        System.out.println("\nNew Book added successfully!\n");
    }

    // defining a function which deletes a book from the Books text file
    public void Delete_Book() throws IOException{

        // creating a new temporary file used to copy all the data in Books file except for the line we want to delete
        File temp_file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\temp_books.txt");
        // opening Books file
        File file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Books.txt");

        // BufferedReader is used to read from the Books file
        BufferedReader br= new BufferedReader(new FileReader(file));
        // PrintWriter is used to write into the temp_file
        PrintWriter pw= new PrintWriter(new FileWriter(temp_file));
        // Scanner is used to scan the Books file
        Scanner sc= new Scanner(br);

        // loop which iterates through the Books file line by line searching for the book required to be deleted by its ID
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // splitting the line separated by commas which is read from the file and storing its data into lineArray
            String[] lineArray = line.split(",");
            // if the ID of the current line is not equal to the Book ID we want to delete, then copy this line into the temp_file
            if (!lineArray[0].equals(getBook_ID())) {
                pw.println(line);
                pw.flush();
            }
            // else, skip the line whose ID matches the ID of the book we want to delete and don't copy it into temp_file
        }

        // closing temp_file and Books file
        sc.close();
        pw.close();
        br.close();

        System.out.println("\nBook with ID "+getBook_ID()+" is removed successfully!!\n");

        // Delete the original file
        file.delete();

        // Rename the new file (temp_file) to the name of the original file (Admins).
        temp_file.renameTo(file);
    }
    }