package CGPA_Calculator;

import java.util.ArrayList;

public class Student extends User{
    private Long PRN;
    int year;
    String name, branch;
    ArrayList semesters = new ArrayList<Semester>();

    Student(String uname, String pass){
        super(uname, pass);
        System.out.println("Enter your PRN:");
        PRN = this.scanner.nextLong();
        System.out.println("Enter your Year of study: ");
        year = this.scanner.nextInt();
        System.out.println("Enter your Name: ");
        name = this.scanner.nextLine();
        System.out.println("Enter your Branch: ");
        branch = this.scanner.nextLine();        
    }
    
}
