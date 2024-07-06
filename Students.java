import java.io.*;
import java.util.Scanner;

public class Students{

    // defining attributes
    private String Name, Birthday, Email,Mobile_Number, ID;

    // parametrized constructor
    Students(String ID, String Mobile_Number, String Name, String Birthday, String Email) {
        setBirthday(Birthday);
        setEmail(Email);
        setMobile_Number(Mobile_Number);
        setName(Name);
        setID(ID);
    }

    // non-parametrized constructor
    public Students() {
    }

    // defining the setters and getters
    public void setName(String name) {
        Name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }

    public String getID() {
        return ID;
    }

    public String getBirthday() {
        return Birthday;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    // defining a function that adds a new student inside Students text file
    public void Add_Student() throws IOException {

        // initializing fw_student variable from FileWriter which opens the text file and write inside it
        // true indicates that the mode of writing is append.
        FileWriter fw_student= new FileWriter("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Students.txt",true);

        // writing data inside the file using fw_student variable which uses write function
        fw_student.write("\n"+getID()+","+getName()+"," + getBirthday()+","+ getEmail()+","+ getMobile_Number());

        // closing the file
        fw_student.close();
        System.out.println("\nNew student added successfully!\n");
    }

    // defining a function which deletes a student from the Students text file
    public void Remove_Student() throws IOException {

        // creating a new temporary file used to copy all the data in Students file except for the line we want to delete
        File temp_file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\temp_students.txt");
        // opening Students file
        File file= new File("C:\\Users\\lenovo\\IdeaProjects\\Assignment 1\\Students.txt");

        // BufferedReader is used to read from the Students file
        BufferedReader br= new BufferedReader(new FileReader(file));
        // PrintWriter is used to write into the temp_file
        PrintWriter pw= new PrintWriter(new FileWriter(temp_file));
        // Scanner is used to scan the Students file
        Scanner sc= new Scanner(br);

        // loop which iterates through the Students file line by line searching for the student required to be deleted by their ID
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // splitting the line separated by commas which is read from the file and storing its data into lineArray
            String[] lineArray = line.split(",");
            // if the ID of the current line is not equal to the Student ID we want to delete, then copy this line into the temp_file
            if (!lineArray[0].equals(getID())) {
                pw.println(line);
                pw.flush();
            }
            // else, skip the line whose ID matches the ID of the student we want to delete and don't copy it into temp_file
        }

        // closing temp_file and Students file
        sc.close();
        pw.close();
        br.close();

        System.out.println("\nStudent with ID "+getID()+" is removed successfully!!\n");

        // Delete the original file
        file.delete();

        // Rename the new file (temp_file) to the name of the original file (Admins).
        temp_file.renameTo(file);
}
}