import java.io.*;
import java.util.Scanner;

public class Admin {

    // defining attributes
    private String Name, Password, ID;

    // parametrized constructor
    Admin(String ID, String Name, String Password) {
        setID(ID);
        setName(Name);
        setPassword(Password);
    }

    // non-parametrized constructor
    Admin() {
    }

    // defining the setters and getters
    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {

        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    // defining a function that adds a new admin inside Admins text file
    public void Add_Admin() throws IOException {
        // initializing fw_admin variable from FileWriter which opens the text file and write inside it
        // true indicates that the mode of writing is append.
        FileWriter fw_admin = new FileWriter("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Admins.txt", true);

        // writing data inside the file using fw_admin variable which uses write function
        fw_admin.write("\n" + getID() + "," + getName() + "," + getPassword());

        // closing the file
        fw_admin.close();
        System.out.println("\nNew admin added successfully!");
    }

    // defining a function which deletes an admin from the Admins text file
    public void Remove_Admin() throws IOException {
        // creating a new temporary file used to copy all the data in Admins file except for the line we want to delete
        File temp_file = new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\temp_admins.txt");
        // opening Admins file
        File file = new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Admins.txt");

        // BufferedReader is used to read from the Admins file
        BufferedReader br = new BufferedReader(new FileReader(file));
        // PrintWriter is used to write into the temp_file
        PrintWriter pw = new PrintWriter(new FileWriter(temp_file));
        // Scanner is used to scan the Admins file
        Scanner sc = new Scanner(br);

        // loop which iterates through the Admins file line by line searching for the admin required to be deleted by their ID
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // splitting the line separated by commas which is read from the file and storing its data into lineArray
            String[] lineArray = line.split(",");
            // if the ID of the current line is not equal to the admin ID we want to delete, then copy this line into the temp_file
            if (!lineArray[0].equals(getID())) {
                pw.println(line);
                pw.flush();
            }
            // else, skip the line whose ID matches the ID of the admin we want to delete and don't copy it into temp_file
        }

        // closing temp_file and Admins file
        pw.close();
        br.close();
        sc.close();
        System.out.println("\nAdmin with ID " + getID() + " is removed successfully!!\n");

        // Delete the original file
        file.delete();

        // Rename the new file (temp_file) to the name of the original file (Admins).
        temp_file.renameTo(file);
    }
}