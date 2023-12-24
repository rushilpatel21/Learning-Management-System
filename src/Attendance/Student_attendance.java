package Attendance;
import java.io.*;
import java.util.*;
import Course.*;

public class Student_attendance{
    String choice;
    String rollNumber;
    public int exitStatus = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> Courses;
    // public int exitStatus = 0;
    public Student_attendance(String rollNumber) {
        
        this.rollNumber = rollNumber;
        System.out.println("Selection Menu");
        System.out.println("1\t View "+ rollNumber + "'s Report: ");
        System.out.println("0\t Exit");
        System.out.print("\nEnter your selection: ");
        this.choice = sc.nextLine();
        Manage_Courses mc = new Manage_Courses();
        this.Courses = mc.findCoursesStudent(rollNumber);
        switch (this.choice) {
            
            case "1": viewGrades(); break;
            case "0": exitStatus = -1; return;  
            default : System.out.println("\nWrong Selection\n");return;
        }
    }

    private void viewGrades(){
        ArrayList<String> Grades = new ArrayList<>();
        ArrayList<String> Course = new ArrayList<>();
       
        try{
            File file = new File ("AttendanceData.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String line = scan.nextLine();
                String data []= line.split(",");
                
                    if(data[0].equalsIgnoreCase(rollNumber)){
                        Grades.add(data[2]);
                        Course.add(data[1]);
                    }
                    
                
            }
            scan.close();
            if(Grades.isEmpty()){
                System.out.println("\nNo Grades Found.\n");
            }else{
                System.out.println("\nRoll Number \t Subject \tAttendance\n");
                for(int i = 0; i < Grades.size(); i++){
                    String formattedLine = String.format("%-17s %-13s %-8s", rollNumber, Course.get(i), Grades.get(i));
                    System.out.println(formattedLine);
                }
                System.out.println();
                    
            }
        }catch(Exception e){
            System.out.println("Error viewing the Attendance: " + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     while (true)new Student_attendance("22BCE308");
    // }

}