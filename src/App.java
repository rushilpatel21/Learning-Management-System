import java.util.*;
import Database_Alumni.*;
import Database_User.*;
import Course.*;
import Attendance.*;
import Academic.*;

public class App {

    static int category = 0; // 1 for student and 2 for Professor/Faculty
    static String name; //Rushil
    static String rollNumber;
    int r = 100;
    // static String rollnum;
    static void Intro(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Student Management Portal.");
        System.out.println();
        System.out.println("   Selection Menu");
        System.out.println("1 \tSign in");
        System.out.println("2 \tSign up");
        System.out.println("0 \tExit");
        System.out.print("Enter the value: ");
        String num = sc.nextLine();

         

        if(num.equalsIgnoreCase("1")){
            Signin s = new Signin();
            category = s.category;
            name = s.name;
            rollNumber = s.email; // email is used as roll number. (just the name email is used but it's roll number)
        }else if(num.equalsIgnoreCase("2")){
            Signup s = new Signup();
            category = s.category;
            name = s.name;
            rollNumber = s.email; // email is used as roll number. (just the name email is used but it's roll number)
        }else if(num.equalsIgnoreCase("0")){
            category = -1;
            // sc.close();
            return;
        }else{
            System.out.println("Invalid selection, please try again!");
            category = 0;
            // Intro();
        }
        // sc.close();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            Intro();
            if(category == 1){
                
                System.out.println("\n\tWelcome Student\n");
                boolean l = true;
                while (l) {
                    System.out.println("Selection Menu");
                    System.out.println("1 \tView Attendance Report");
                    System.out.println("2 \tView Academic Report");
                    System.out.println("3 \tAccess Quiz");
                    System.out.println("4 \tManage Courses");
                    // System.out.println("5 \tDisenroll A Course");
                    System.out.println("5 \tView Alumni Database");
                    System.out.println("6 \tChange Password");
                    System.out.println("0 \tLog out");
                    System.out.print("\nEnter the value: ");
                    String num = sc.nextLine();
                    int exitStatus = 0;
                    switch (num) {
                        case "1" : 
                            System.out.print("\n\nAttendance Report ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0){
                                Student_attendance sa = new Student_attendance(rollNumber);
                                exitStatus = sa.exitStatus;
                            }
                            break;
                        case "2" : 
                            System.out.print("\n\nAcademic Report ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0){
                                Student_academic sa = new Student_academic(rollNumber);
                                exitStatus = sa.exitStatus;
                            }
                            break;
                        case "3" : 
                            // new Student_Quiz(rollNumber);
                            System.out.print("\n\nAccessing Quiz ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0) {
                                Student_Quiz sq = new Student_Quiz(rollNumber);
                                exitStatus = sq.exitStatus;
                            }
                            break;
                        case "4" : 
                            System.out.print("\n\nManaging Courses ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0) {  
                                Enroll en = new Enroll(rollNumber);
                                exitStatus = en.exitStatus;
                            }
                            break;
                        case "5" : 
                            System.out.print("\n\nViewing Alumni Records ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0) {
                                Alumni_Database ad = new Alumni_Database(0);
                                exitStatus = ad.exitStatus;
                            }
                            
                            break;
                        case "6" : 
                            System.out.print("\n\nChanging Password ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            new Change_Password(rollNumber,2);
                            break;
                        case "0" : l = false; break;                   
                        default:
                            break;
                    }
                    
                }
                System.out.print("\nLogging Out ");
                // Thread.sleep(1500);
                for(int i=0;i<5;i++){
                    Thread.sleep(333);
                    System.out.print(". ");
                }
                System.out.println();

                // category = 0;
            }else if(category == 2){

                System.out.println("\n\tWelcome Faculty\n");
                boolean l = true;
                while (l) {
                    System.out.println("Selection Menu");
                    System.out.println("1 \tGenerate Attendance Report");
                    System.out.println("2 \tGenerate Academic Report");
                    System.out.println("3 \tManage Quizes");
                    System.out.println("4 \tManage Courses");
                    // System.out.println("5 \tAdd/Remove Students from a course");
                    System.out.println("5 \tAccess Alumni Database");
                    System.out.println("6 \tChange Password");
                    System.out.println("0 \tLog out");
                    System.out.print("\nEnter the value: ");
                    String num = sc.nextLine();
                    int exitStatus = 0;
                    switch (num) {
                        case "1" : 
                            System.out.print("\n\nAttendance Report ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            // Thread.sleep(1000);
                            while (exitStatus == 0){
                                Attendance_Database sa = new Attendance_Database(name);
                                exitStatus = sa.exitStatus;
                            }
                            break;
                        case "2" : 
                            System.out.print("\n\nAcademic Report ");
                            // Thread.sleep(1000);
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            while (exitStatus == 0){
                                Academic_Database sa = new Academic_Database(name);
                                exitStatus = sa.exitStatus;
                            }
                            break;
                        case "3" : 
                            // new Quiz(name);
                            System.out.print("\n\nAccessing Quiz ");
                            // Thread.sleep(1000);
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            while (exitStatus == 0) {
                                Quiz q = new Quiz(name);
                                exitStatus = q.exitStatus;
                            }
                            break;
                        case "4" : 
                            System.out.print("\n\nManaging Courses ");
                            // Thread.sleep(1000);
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            while (exitStatus == 0) {    
                                Manage_Courses mc = new Manage_Courses(name);
                                exitStatus = mc.exitStatus;
                            }
                            break;
                        case "5" :
                            System.out.print("\n\nViewing Alumni Records ");
                            // Thread.sleep(1000);
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            while (exitStatus == 0) {
                                Alumni_Database ad = new Alumni_Database(1);
                                exitStatus = ad.exitStatus;
                            } 
                            break;
                        case "6":
                            // Thread.sleep(1000);
                            System.out.print("\n\nChanging Password ");
                            for(int i=0;i<3;i++){
                                Thread.sleep(333);
                                System.out.print(". ");
                            }
                            System.out.println();
                            new Change_Password(rollNumber,1);
                            break;
                        case "0" : l = false; break;                   
                        default:
                            break;
                    }
                    
                }
                System.out.print("\nLogging Out ");
                // Thread.sleep(1500);
                for(int i=0;i<5;i++){
                    Thread.sleep(333);
                    System.out.print(". ");
                }
                System.out.println();


                // category = 0;
            }else if(category == 0){

                System.out.println("\n");
                // Intro();

            }else{
                break;
            }
            category = 0;
        }
        sc.close();
    }
}
