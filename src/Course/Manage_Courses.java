package Course;

import java.util.*;
import java.io.*;

public class Manage_Courses {
    Scanner sc = new Scanner(System.in);
    String facultyName;
    public int exitStatus = 0;
    public Manage_Courses(){};
    ArrayList<String> CoursesToAdd = new ArrayList<>();
    public Manage_Courses(String facultyName){
        this.facultyName=facultyName;
        System.out.println("\n\nWelcome to the course management");
        String choice;
        System.out.println("\n\nSelection Menu\n");
        System.out.println("1\t Add a Course");
        System.out.println("2\t Remove a Course");
        System.out.println("3\t Display all Courses");
        System.out.println("4\t Add student in a Course");
        System.out.println("5\t Remove Student from a Course");
        System.out.println("6\t To View all the Courses a Student is Enrolled In");
        System.out.println("7\t View My Course");
        System.out.println("0\t Exit");
        // System.out.println("7\t Add Secondary Faculties to a Course");
        // System.out.println("8\t Remove Seconday Faulty from a Course");
        System.out.print("\nEnter your choice: ");
        choice = sc.nextLine();
        switch (choice){
            case "0" : exitStatus = -1; return;
            case "1" : addCourse(); break;
            case "2" : removeCourse(); break;
            case "3" : displayAllCourses(); break;
            case "4" : addStudentInACourse(); break;
            case "5" : removeStudentFromACourse(); break;
            case "6" : viewStudentsEnrolledInCourse(); break;
            case "7" : viewMyCourse(); break;
            // case "7":
            // addSecondaryFacultyToACourse();
            // break;
            // case "8":
            // removeSecondaryFacultyFromACourse();
            // break;
            default  : System.out.println("\nInvalid Choice!\n");
        }
    }
    private void addCourse() {
        System.out.print("\nEnter the name of the course you want to add: ");
        String course = sc.nextLine();
        File file = new File("StudentCourse.txt");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                System.out.println("Error creating file!" + e);
            }
        }
        
        try (Scanner inStream = new Scanner(file)) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String data[] = line.split(",");
                if (data[0].equalsIgnoreCase(course)) {
                    System.out.println("\nThis course already exists!\n");
                    inStream.close();
                    return;
                }
            }
            inStream.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String Instructor;
            String roomno;
            System.out.print("Enter the name of the Instructor: ");
            Instructor = sc.nextLine();
            System.out.print("Enter the Room number for this Course: ");
            roomno = sc.nextLine();

            bw.write(course + "," + Instructor + "," + roomno);
            bw.newLine();
            System.out.println("\nCourse Added.\n");
            bw.close();
        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    private void removeCourse(){
        System.out.print("\nEnter the name of the course you want to delete: ");
        String course = sc.nextLine();
        File file = new File("StudentCourse.txt");
        if (!file.exists()) {
            System.out.println("No Courses in the database!");
            return;
        }
        boolean found = false;
        
        try{
            File file1 = new File("StudentCourseTemp.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter temp = new BufferedWriter(new FileWriter(file1));
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (!data[0].equalsIgnoreCase(course)) {
                    temp.write(line);
                    temp.newLine();
                }else{
                    found = true;
                }
            }
            if(found){
                System.out.println("\nCourse Deleted.");
            }else{
                System.out.println("\nThe Course does not exist!");
            }
            temp.flush();
            temp.close();
            inStream.close();
            file.delete();
            file1.renameTo(file);

        }catch(IOException e){
            System.out.println("Error deleting the course: " + e.getMessage());
        }

    }
    int displayAllCourses(){
        File file = new File("StudentCourse.txt");
        if(!file.exists()){
            System.out.println("No courses available!");
            return 0;
        }
        int i = 0;
        try{
            Scanner inStream = new Scanner(file);
            CoursesToAdd.clear();
            while(inStream.hasNextLine()){
                System.out.println();
                String line = inStream.nextLine();
                String[] data = line.split(",");
                System.out.print((i+1) + ". " + "Course Name : "+data[0]);
                System.out.println(" , Instructor : "+data[1]+" , Room Number : "+data[2]);
                CoursesToAdd.add(data[0]);
                i++;
            }
            inStream.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred."+e.toString());
        }
        // Collections.sort(CoursesToAdd);
        return i;
    }
    
    boolean toCheckCourse(String course){
        boolean check = false;
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (course.equalsIgnoreCase(data[0])) {
                    check=true;
                    break;
                }   
            }
            inStream.close();
        }catch(IOException e){
            System.out.println("Error viewing the course: " + e.getMessage());
        }

        return check;

    }

    ArrayList<String> toCheckStudent( String courseName){
        ArrayList<String> Students = new ArrayList<>();
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (courseName.equalsIgnoreCase(data[0])) {
                    for(int i = 3;i<data.length;i++){
                        Students.add(data[i]);
                    }
                }   
            }
            inStream.close();
        }catch(IOException e){
            System.out.println("Error viewing the student: " + e.getMessage());
        }

        Collections.sort(Students);
        return Students;
    }

    boolean toCheckStudent(String rollNumber,String courseName){
        boolean check = false;
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (courseName.equalsIgnoreCase(data[0])) {
                    for(int i = 3;i<data.length;i++){
                        if(rollNumber.equalsIgnoreCase(data[i].trim())) {
                            check=true;
                            break;
                        }
                    }
                }   
            }
            inStream.close();
        }catch(IOException e){
            System.out.println("Error viewing the student: " + e.getMessage());
        }


        return check;
    }
    void addStudent(String rollNumber,String courseName){
        try{
            File file = new File("StudentCourse.txt");
            File file1 = new File("StudentCourseTemp.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true))) {
                while(inStream.hasNextLine()) {
                    String line = inStream.nextLine();
                    String[] data = line.split(",");
                    if (courseName.equalsIgnoreCase(data[0])) {
                        String lineTemp = line + "," + rollNumber;
                        bw.write(lineTemp);
                    }else{
                        bw.write(line);
                    }
                    bw.newLine();   
                }
                inStream.close();
                bw.flush();
                bw.close();
                file.delete();
                file1.renameTo(file);
            }catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }catch(IOException e){
            System.out.println("Error deleting the course: " + e.getMessage());
        }
        System.out.println("\nStudent successfully added to the Course.\n");
    }
    void removeStudent(String rollNumber, String courseName){
        try{
            File file = new File("StudentCourse.txt");
            File file1 = new File("StudentCourseTemp.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true))) {
                while(inStream.hasNextLine()) {
                    String line = inStream.nextLine();
                    String[] data = line.split(",");
                    if (courseName.equalsIgnoreCase(data[0])) {
                        // String lineTemp = line + "," + rollNumber;
                        // bw.write(lineTemp);
                        String lineTemp = data[0];
                        lineTemp += "," + data[1] + "," + data[2];
                        for(int i=3;i<data.length;i++){
                            if(data[i].equalsIgnoreCase(rollNumber)){
                                continue;
                            }else{
                                lineTemp += "," + data[i];
                            }
                        }
                        bw.write(lineTemp);
                    }else{
                        bw.write(line);
                    }
                    bw.newLine();   
                }
                inStream.close();
                bw.flush();
                bw.close();
                file.delete();
                file1.renameTo(file);
            }catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }catch(IOException e){
            System.out.println("Error deleting the course: " + e.getMessage());
        }
        System.out.println("\nStudent successfully removed from the Course.\n");
    }
    private void addStudentInACourse(){
        System.out.println("\nCourses\n");
        int i = displayAllCourses();
        // displayAllCourses();
        System.out.print("\nEnter the number of the course for the students: ");
        String course = sc.nextLine();
        int index = Integer.parseInt(course);
        index--;
        if(index<0 || index >= i){
            System.out.println("\nInvalid choice.");
            System.out.print("Do you want to exit[Y/N]: ");
            String c = sc.nextLine();
            if(c.equalsIgnoreCase("y")){
                return;
            }
            addStudentInACourse();
            return;
        }else{
            course = CoursesToAdd.get(index);
        }
        
        // boolean checkCourse = toCheckCourse(course);
        
        // if(!checkCourse) {
        //     System.out.println("\nThe Course does not exist!");
        //     return;
        // }

        while (true) {
            System.out.print("Enter the Student's Roll Number(Or enter 0 to exit): ");
            String rollNumber = sc.nextLine();
            if (rollNumber.equals("0")) {
                break;
            }
            
            boolean checkStudent = toCheckStudent(rollNumber, course);
            if(checkStudent){
                System.out.println("\nThe Student is already enrolled in this course!\n");
                continue;
            }
            addStudent(rollNumber,course);
        }
    }
    private void removeStudentFromACourse(){
        System.out.println("\nCourses\n");
        int i = displayAllCourses();
        System.out.print("\n\nEnter the number of the course for the student: ");
        String course = sc.nextLine();
        int index = Integer.parseInt(course);
        index--;
        if(index<0 || index >= i){
            System.out.println("\nInvalid choice.");
            System.out.print("Do you want to exit[Y/N]: ");
            String c = sc.nextLine();
            if(c.equalsIgnoreCase("y")){
                return;
            }
            removeStudentFromACourse();
            return;
        }else{
            course = CoursesToAdd.get(index);
        }
        while (true) {
            ArrayList<String> Students = toCheckStudent(course);
            if(Students.isEmpty()){
                System.out.println("\nNo students are currently enrolled in this course.\n");
                return;
            }
            System.out.println("\nThe Students enrolled in " + course +" are: ");
            for(int ii=0;ii<Students.size();ii++){
                System.out.println((ii+1)+". "+Students.get(ii));
            }
            System.out.print("\nEnter the Number of the student to be removed from the course or enter 0 to exit: ");
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("0")){
                break;
            }
            try{
                int num = Integer.parseInt(choice);
                num--;
                if(num>=0 && num < Students.size()) {
                    String rollNumber = Students.get(num);
                    removeStudent(rollNumber, course);

                    break;
                }
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("\nInvalid choice!");
                continue;
            }
            catch(NumberFormatException e){
                System.out.println("\nInvalid choice!");
                continue;
            }
        }
    }
        // System.out.print("\n\nEnter the Student's Roll Number: ");
        // String rollNumber = sc.nextLine();
        // boolean checkStudent = toCheckStudent(rollNumber, course);
        // if(!checkStudent){
        //     System.out.println("\nThe Student is not enrolled in this course!");
        //     return;
        // }
        // removeStudent(rollNumber,course);

    

    public ArrayList<String> toCheckMyCourse(String facultyName){
        ArrayList<String> Course = new ArrayList<>();
        try{
            File file = new File("StudentCourse.txt");
            // BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner inStream = new Scanner(file);
            // System.out.println();
            while(inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                // System.out.println();
                if(facultyName.equalsIgnoreCase(data[1])){
                    Course.add(data[0]);
                }
            }
            inStream.close();
        }catch(IOException e){
            System.out.println("Error Checking the course: " + e.getMessage());
        }

        return Course;

    }
    public ArrayList<String> findCoursesStudent(String rollNumber){
        ArrayList<String> Courses = new ArrayList<>();
        try{
            File file = new File("StudentCourse.txt");
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()){
                String line = inStream.nextLine();
                String[] data = line.split(",");
                for(int i = 3;i<data.length;i++){
                    if(rollNumber.equalsIgnoreCase(data[i])){
                        Courses.add(data[0]);
                        break;
                    }
                }
            }
            inStream.close();
        }catch(Exception e){
            System.out.println("Error Reading from the file: "+e.getMessage());
        }
        return Courses;
    }
    private void viewStudentsEnrolledInCourse(){
        System.out.print("Enter the Roll Number of the Student you want to view the enrolled courses of :");
        String rollNumber = sc.nextLine();
        ArrayList<String> Courses = findCoursesStudent(rollNumber);
        if(!Courses.isEmpty()){
            System.out.println("\nThe student is Enrolled in these courses:\n");
        }
        else{
            System.out.println("\nThe student is not Enrolled in any of the courses.");
            return;
        }
        int i = 1;
        for (String s : Courses) {
            System.out.println(i+". "+s);
            i++;
        }
    }
    private void viewMyCourse(){
        ArrayList<String> Course = toCheckMyCourse(facultyName);
        // if (!check) {
        //     System.out.println("\nNo courses assigned yet.\n\n");
        // }else{
        //     System.out.println();
        // }
        if(Course.isEmpty()){
            System.out.println("\nNo courses assigned yet.\n\n");
        }else{
            System.out.println("\nWelcome "+ facultyName +", Courses you manage is/are :");
            for(int i=0 ;i < Course.size() ; i++){
                System.out.println((i + 1)+". "+Course.get(i));
            }
            System.out.println("\n");

        }
    }
    
    // private void addSecondaryFacultyToACourse(){
    //     System.out.print("Enter the course name for which you want to add Seconday Faculty: ");
    //     String course = sc.nextLine();
    //     System.out.print("Enter the secondary faculty's name number: ");
    //     String name = sc.nextLine();

    // }   
    // private void removeSecondaryFacultyFromACourse(){

    // }

    // public static void main(String[] args) {
    //     while (true) {
    //         new Manage_Courses("Ajay Patel");
    //     }

    // }
}


