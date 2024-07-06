import java.io.*;

public class BooksIssued extends Books{

    // defining attributes
    private int Procedure_ID;
    private String returned;

    // parametrized constructor
    BooksIssued(){}

    // defining the setters and getters
    public String isReturned() {
        return returned;
    }

    public int getProcedure_ID() {
        return Procedure_ID;
    }

    public void setProcedure_ID(int procedure_ID) {
        Procedure_ID = procedure_ID;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
    // defining a function that adds a record inside IssuedBooks text file
    public void Add_Record() throws IOException{

        // initializing fw_issued_book variable from FileWriter which opens the text file and write inside it
        // true indicates that the mode of writing is append.
        FileWriter fw_issued_book= new FileWriter("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\IssuedBooks.txt",true);

        // writing data inside the file using fw_issued_book variable which uses write function
        fw_issued_book.write("\n"+ getProcedure_ID()+ ","+ getBook_ID()+ ","+ getID()+ ","+ isReturned());

        // closing the file
        fw_issued_book.close();
    }
}