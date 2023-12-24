package Academic;
import java.io.*;
import java.util.*;
import Course.*;



public class Academic_Database extends AbstractAcademic_Database {
    int flag=0;
    String choice;
    String name;
    public int exitStatus = 0;
    Scanner sc = new Scanner(System.in);

    ArrayList<String> Course;
    // public int exitStatus = 0;
    public Academic_Database(String name) {
        Manage_Courses mc = new Manage_Courses();
        Course = mc.toCheckMyCourse(name);
        this.name = name;
        System.out.println("Selection Menu");
        System.out.println("1\t Add Student Academic Report");
        // System.out.println("2\t View all quizzes");
        System.out.println("2\t Update Student Academic Report");
        System.out.println("3\t Delete Student Academic Report");
        System.out.println("4\t View Student Academic Report");
        System.out.println("0\t Exit");
        System.out.print("\nEnter your selection: ");
        this.choice = sc.nextLine();
        switch (this.choice) {
            case "1": add(); break;
            // case "2": viewallquizes(); break;
            // case "3": deletequiz(); break;
            case "2": update(); break;
            case "3": delete(); break;
            case "4": view(); break;
            case "0": exitStatus = -1; return;  
            default : System.out.println("\nWrong Selection\n");return;
        }
            
    }
    boolean findAcadCourseStudent(String rollNumber, String subject){
        boolean acadCourse =false;
        File file = new File("AcademicData.txt");
        try{
            Scanner inScanner = new Scanner(file);
            while(inScanner.hasNext()){
                String data = inScanner.nextLine();
                String row[] = data.split(",");
                if(row[0].equalsIgnoreCase(rollNumber) && row[1].equalsIgnoreCase(subject)){
                    acadCourse = true;
                    break;
                }
            }
            inScanner.close();
                
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return acadCourse;
    }

    @Override
     public void add() {
        File file = new File("AcademicData.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.print("\nEnter rollno : ");
            String rollNo = sc.nextLine();
            System.out.print("Enter subject : ");
            String subject = sc.nextLine();
            Manage_Courses mc = new Manage_Courses();
            ArrayList<String> studentCourse = mc.findCoursesStudent(rollNo);
            // ArrayList<String> acadCourse = new ArrayList<>();
            if(studentCourse.contains(subject) && findAcadCourseStudent(rollNo, subject)){

                System.out.println("Academic Data Already set in the course "+subject+" for "+rollNo+". Please select another one.");
                System.out.print("Do you want to update it?[Y/N]");
                String choi = sc.nextLine();
                if(choi.equalsIgnoreCase("y")){
                    bw.flush();
                    bw.close();
                    update(rollNo,subject);
                    return;
                }else{
                    return;
                }
            }else if(!studentCourse.contains(subject)){
                System.out.println("The student is not enrolled for the course.");
                
                System.out.print("Do you want to Enroll for that Course?[Y/N]:");
                String inp = sc.nextLine();
                if(inp.equalsIgnoreCase("y")){
                    Enroll en = new Enroll();
                    en.enrollForACourse(subject,rollNo);
                }
                return;
                /////////////////////////////////////////////////
            }
            System.out.print("Enter grade : ");
            String grade = sc.nextLine();
            bw.write(rollNo + "," + subject + "," + grade);
            bw.newLine();
            bw.flush();
            bw.close();
            System.out.println("Data has been successfully written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    
    public void update(String rollNoToUpdate, String courseName) {
        File file = new File("AcademicData.txt");

        try {
            // BufferedReader br = new BufferedReader(new FileReader(file));
            // ArrayList<String> records = new ArrayList<>();
            // String line;
            // while ((line = br.readLine()) != null) {
            //     records.add(line);
            // }
            // br.close();
            // ArrayList<String> Subjects = new ArrayList<>();
            // System.out.print("\nEnter Roll Number to update: ");
            // rollNoToUpdate = sc.nextLine();

            // boolean found = false;
            // for (int i = 0; i < records.size(); i++) {
            //     String record = records.get(i);
            //     String[] parts = record.split(",");
            //     if (parts.length == 3 && parts[0].equalsIgnoreCase(rollNoToUpdate)) {
            //         Subjects.add(parts[1]);
            //         // System.out.print("Enter new grade: ");
            //         // String newGrade = sc.nextLine();
            //         // records.set(i, parts[0] + "," + parts[1] + "," + newGrade);
            //         found = true;
            //     }
            // }
            // Scanner scanFile = new Scanner(file);
            // while (scanFile.hasNextLine()) {
            //     String data = scanFile.nextLine();
            //     String[] rowData = data.split(",");
            //     if(rowData[0].equalsIgnoreCase(rollNoToUpdate)){
            //         Subjects.add(rowData[1]);
            //         // System.out.println(rowData[1]);
            //         // found = true;
            //     }
            // }
            // scanFile.close();
            

            // if (!found) {
                // System.out.println("No record found for the given rollno.");
            // } else {
                File temp = new File("AcademicDataTemp.txt");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true))) {
                //     // for (String updatedRecord : records) {
                //     //     bw.write(updatedRecord);
                //     //     bw.newLine();
                //     // }
                //     System.out.println("\n\nCourses:");
                //     int index = 0;
                //     for (String s : Subjects) {
                //         System.out.println((index+1)+". "+s);
                //         index++;
                //     }
                //     System.out.print("\nEnter your selection(number): ");
                //     String choice = sc.nextLine();
                //     int selectedIndex = Integer.parseInt(choice);
                //     selectedIndex--;
                //     if(selectedIndex<0 || selectedIndex >= Course.size()){
                //         System.out.println("Invalid Selection!");
                //         return;
                //     }
                    // courseName = Subjects.get(selectedIndex);
                    System.out.println("You have chosen " + courseName);
                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine();
                    Scanner sc = new Scanner(file);
                    while (sc.hasNext()){
                        String sent = sc.nextLine();
                        String data[] = sent.split(",");
                        if(data[0].equalsIgnoreCase(rollNoToUpdate) && data[1].equalsIgnoreCase(courseName)){
                            sent = rollNoToUpdate + "," + courseName + "," + newGrade;
                        }
                        bw.write(sent);
                        bw.newLine();
                    }
                    sc.close();
                    bw.flush();
                    bw.close();
                    file.delete();
                    boolean idk = temp.renameTo(file);
                    System.out.println(idk);
                } catch (IOException e) {
                    System.out.println("Error deleting the file: " + e.getMessage());
                }
                System.out.println("Record has been successfully updated.");
            
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }
    }
    
   @Override
    public void update() {
        File file = new File("AcademicData.txt");

        try {
            // BufferedReader br = new BufferedReader(new FileReader(file));
            // ArrayList<String> records = new ArrayList<>();
            // String line;
            // while ((line = br.readLine()) != null) {
            //     records.add(line);
            // }
            // br.close();
            ArrayList<String> Subjects = new ArrayList<>();
            System.out.print("\nEnter Roll Number to update: ");
            String rollNoToUpdate = sc.nextLine();

            boolean found = false;
            // for (int i = 0; i < records.size(); i++) {
            //     String record = records.get(i);
            //     String[] parts = record.split(",");
            //     if (parts.length == 3 && parts[0].equalsIgnoreCase(rollNoToUpdate)) {
            //         Subjects.add(parts[1]);
            //         // System.out.print("Enter new grade: ");
            //         // String newGrade = sc.nextLine();
            //         // records.set(i, parts[0] + "," + parts[1] + "," + newGrade);
            //         found = true;
            //     }
            // }
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()) {
                String data = scanFile.nextLine();
                String[] rowData = data.split(",");
                if(rowData[0].equalsIgnoreCase(rollNoToUpdate)){
                    Subjects.add(rowData[1]);
                    // System.out.println(rowData[1]);
                    found = true;
                }
            }
            scanFile.close();
            

            if (!found) {
                System.out.println("No record found for the given rollno.");
            } else {
                File temp = new File("AcademicDataTemp.txt");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true))) {
                    // for (String updatedRecord : records) {
                    //     bw.write(updatedRecord);
                    //     bw.newLine();
                    // }
                    System.out.println("\n\nCourses:");
                    int index = 0;
                    for (String s : Subjects) {
                        System.out.println((index+1)+". "+s);
                        index++;
                    }
                    System.out.print("\nEnter your selection(number): ");
                    String choice = sc.nextLine();
                    int selectedIndex = Integer.parseInt(choice);
                    selectedIndex--;
                    // System.out.println(selectedIndex +"," + Subjects.size());
                    if(selectedIndex<0 || selectedIndex >= Subjects.size()){
                        System.out.println("Invalid Selection!");
                        return;
                    }
                    String courseName = Subjects.get(selectedIndex);
                    System.out.println("You have chosen " + courseName);
                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine();
                    Scanner sc = new Scanner(file);
                    while (sc.hasNext()){
                        String sent = sc.nextLine();
                        String data[] = sent.split(",");
                        if(data[0].equalsIgnoreCase(rollNoToUpdate) && data[1].equalsIgnoreCase(courseName)){
                            sent = rollNoToUpdate + "," + courseName + "," + newGrade;
                        }
                        bw.write(sent);
                        bw.newLine();
                    }
                    sc.close();
                    bw.flush();
                    bw.close();
                    file.delete();
                    boolean idk = temp.renameTo(file);
                    System.out.println(idk);
                } catch (IOException e) {
                    System.out.println("Error deleting the file: " + e.getMessage());
                }
                System.out.println("Record has been successfully updated.");
            }
        } catch (IOException e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }
    }
        
    @Override
    void view(){
        File file = new File("AcademicData.txt");
       
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                String rollNumber = data[0];
                String Course = data[1];
                String per = data[2];

                System.out.println("\n\nrollnumber: " + rollNumber);
                System.out.println("subject: "+ Course);
                System.out.println("Attendance percentaage: "+per+"\n");


            }
            System.out.println();
            sc.close();
        }catch(Exception e){
        System.out.println("Error in viewing the Result: " + e.getMessage());
    }
    }
 
    @Override
    void delete(){
        System.out.print("Enter the Roll Number whos Grade you want to delete: ");
        String rollNumber = sc.nextLine();
        File file = new File("AcademicData.txt");
        ArrayList<String> Courses = new ArrayList<>();
        try{
            Scanner inStream = new Scanner(file);
            while (inStream.hasNext()){
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if(data[0].equalsIgnoreCase(rollNumber)){
                    Courses.add(data[1]);
                }
            }
            inStream.close();
            if(Courses.size()==0){
                System.out.println("\n\nNo data for the Student.");
                return;
            }
            System.out.println("The Following are the Courses whos Grades can be Deleted");
            for(int i=0 ;i <Courses.size();i++){
                System.out.println((i+1)+"."+" "+Courses.get(i));
            }
            System.out.print("\nEnter your choice: ");
            String choice = sc.nextLine();
            int index = Integer.parseInt(choice);
            index--;
            if(index<0 || index >= Courses.size()){
                System.out.println("Invalid Choice");
                return;
            }
            String CourseDel = Courses.get(index);
            File temp = new File("AcademicDataTemp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp,true));
            Scanner inScanner = new Scanner(file);
            while (inScanner.hasNext()) {
                String line = inScanner.nextLine();
                String[] data = line.split(",");
                if(data[0].equalsIgnoreCase(rollNumber) && data[1].equalsIgnoreCase(CourseDel)){
                    continue;
                }
                else{
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
            inScanner.close();
            file.delete();
            temp.renameTo(file);
        }catch (IOException e){
            System.out.println("IO Error : "+e.getMessage());
        }catch(Exception e){
            System.out.print("Error Occured : " + e.getMessage());
        }

        
    }

    // public static void main(String[] args) {
    //     while(true)new Academic_Database("Ajay Patel");
    // }
}