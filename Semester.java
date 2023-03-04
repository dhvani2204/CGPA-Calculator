package CGPA_Calculator;

import java.util.ArrayList;
import java.util.Vector;

public class Semester {
    int year;
    String branch;
    Vector<Integer> credits = new Vector<Integer>();
    Vector<Integer> gpas = new Vector<Integer>();
    ArrayList subjects = new ArrayList<Subject>();
}
