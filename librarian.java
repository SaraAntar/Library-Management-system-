import java.io.*;
import java.util.Scanner;

public class librarian {

    // defining attributes
    private String Name, Password, ID;

    // non-parametrized constructor
    librarian() {
    }
    // parametrized constructor
    librarian(String ID, String Name, String Password) {
        setID(ID);
        setName(Name);
        setPassword(Password);
    }

    // defining the setters and getters
    public String getID() {
        return ID;
    }

    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    // defining a function that adds a new librarian inside Librarians text file
    public void Add_Librarian() throws IOException {

        // initializing fw_librarian variable from FileWriter which opens the text file and write inside it
        // true indicates that the mode of writing is append.
        FileWriter fw_librarian= new FileWriter("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Librarians.txt",true);

        // writing data inside the file using fw_librarian variable which uses write function
        fw_librarian.write("\n"+getID()+","+getName()+","+getPassword());

        // closing the file
        fw_librarian.close();
        System.out.println("\nNew librarian added successfully!");
    }

    // defining a function which deletes a librarian from the Librarians text file
    public void Remove_Librarian() throws IOException{

        // creating a new temporary file used to copy all the data in Librarians file except for the line we want to delete
        File temp_file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\temp_librarians.txt");
// opening Librarians file
        File file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Librarians.txt");

// BufferedReader is used to read from the Librarians file
        BufferedReader br= new BufferedReader(new FileReader(file));
// PrintWriter is used to write into the temp_file
        PrintWriter pw= new PrintWriter(new FileWriter(temp_file));
// Scanner is used to scan the Librarians file
        Scanner sc= new Scanner(br);

// loop which iterates through the Librarians file line by line searching for the librarian required to be deleted by their ID
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // splitting the line separated by commas which is read from the file and storing its data into lineArray
            String[] lineArray = line.split(",");
            // if the ID of the current line is not equal to the librarian ID we want to delete, then copy this line into the temp_file
            if (!lineArray[0].equals(getID())) {
                pw.println(line);
                pw.flush();
            }
            // else, skip the line whose ID matches the ID of the librarian we want to delete and don't copy it into temp_file
        }

// closing temp_file and Librarians file
        sc.close();
        pw.close();
        br.close();
        System.out.println("\nLibrarian with ID "+getID()+" is removed successfully!!\n");

// Delete the original file
        file.delete();

// Rename the new file (temp_file) to the name of the original file (Admins).
        temp_file.renameTo(file);

    }
}