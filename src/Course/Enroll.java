package Course;

import java.util.*;
import java.io.*;

public class Enroll {
    Scanner sc = new Scanner(System.in);
    String rollNumber;
    public int exitStatus = 0; 
    public Enroll(){};
    public Enroll(String rollNumber){
        this.rollNumber=rollNumber;
        System.out.println("Welcome to the course management");
        String choice;
        System.out.println("\n\nSelection Menu\n");
        System.out.println("1\t View Enrolled Courses");
        System.out.println("2\t Enroll for a Course"); 
        System.out.println("3\t Disenroll a Course");
        System.out.println("4\t View Courses I can Enroll");
        System.out.println("0\t Exit");
        System.out.print("\nEnter your choice: ");
        choice = sc.nextLine();
        switch (choice){
            case "0" : exitStatus = -1; return; 
            case "1" : viewEnrolledCourses(); break;
            case "2" : enrollForACourse(); break;
            case "3" : disenrollACourse(); break;
            case "4" : viewCoursesICanEnroll(); break;
            default : System.out.println("Invalid Choice!");
        }
    }
    boolean toCheckMyCourse(String rollNumber){
        boolean check = false;
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            System.out.println();
            int check1 = 0;
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                // System.out.println();
                
                for(int i = 3;i<data.length;i++){
                    if(rollNumber.equalsIgnoreCase(data[i].trim())){
                        check = true;
                        if(check1==0){
                            System.out.print("You are enrolled in "+data[0]);
                        }else{
                            System.out.print(", "+data[0]);
                        }
                        check1++;
                    }
                }
                
            }
            System.out.println();
            inStream.close();
        }catch(IOException e){
            System.out.println("Error Checking the course: " + e.getMessage());
        }

        return check;
    }
    private void viewEnrolledCourses(){
        boolean check = toCheckMyCourse(rollNumber);
        if (!check) {
            System.out.println("\nNo courses enrolled yet.\n\n");
        }else{
            System.out.println();
        }
    }
    public void enrollForACourse(String courseName, String rollNumber1){
        // boolean check = toCheckMyCourse(rollNumber);
        
        // System.out.print("Enter the course you want to Enroll for: ");
        // String courseName = sc.nextLine();
        Manage_Courses add = new Manage_Courses();
        boolean checkCourse = add.toCheckCourse(courseName);
        if(!checkCourse) {
            System.out.println("\nThe Course does not exist!");
            return;
        }
        boolean checkStudent = add.toCheckStudent(rollNumber1, courseName);
        if(checkStudent){
            System.out.println("\nThe Student is already enrolled in this course!");
            return;
        }
        add.addStudent(rollNumber1, courseName);
        
    }
    private void enrollForACourse(){
        // boolean check = toCheckMyCourse(rollNumber);
        
        System.out.print("Enter the course you want to Enroll for: ");
        String courseName = sc.nextLine();
        Manage_Courses add = new Manage_Courses();
        boolean checkCourse = add.toCheckCourse(courseName);
        if(!checkCourse) {
            System.out.println("\nThe Course does not exist!");
            return;
        }
        boolean checkStudent = add.toCheckStudent(rollNumber, courseName);
        if(checkStudent){
            System.out.println("\nThe Student is already enrolled in this course!");
            return;
        }
        add.addStudent(rollNumber, courseName);
        
    }
    private void disenrollACourse(){
        // boolean check = toCheckMyCourse(rollNumber);
        
        System.out.print("Enter the course you want to Dis-enroll from: ");
        String courseName = sc.nextLine();
        Manage_Courses remove = new Manage_Courses();
        boolean checkCourse = remove.toCheckCourse(courseName);
        if(!checkCourse) {
            System.out.println("\nThe Course does not exist!");
            return;
        }
        boolean checkStudent = remove.toCheckStudent(rollNumber, courseName);
        if(!checkStudent){
            System.out.println("\nThe Student is not enrolled in this course!");
            return;
        }
        remove.removeStudent(rollNumber,courseName);
        
    }

    private void viewCoursesICanEnroll(){
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            System.out.println();
            int check1 = 0;
            boolean present = false;
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                // System.out.println();
                int flag = 0;
                for(int i = 3;i<data.length;i++){
                    if(rollNumber.equalsIgnoreCase(data[i].trim())){
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    if(check1==0){
                        present = true;
                        System.out.print("You are not enrolled in "+data[0]);
                    }else{
                        System.out.print(", "+data[0]);
                    }
                    check1++;
                }
                
            }
            inStream.close();
            if(!present){
                System.out.println("You are enrolled in all the possible Courses.");
            }else{
                System.out.print("\n\nDo you want to Enroll in any of the courses?[Y/N]:");
                String choice = sc.nextLine();
                if(choice.equalsIgnoreCase("y")){
                    enrollForACourse();
                }else{
                    System.out.println("Okay! You can continue with your current courses.");
                }
            }
            System.out.println();
        }catch(IOException e){
            System.out.println("Error Checking the course: " + e.getMessage());
        }

    }
}
