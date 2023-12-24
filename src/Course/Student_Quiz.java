package Course;

import java.io.*;
import java.util.*;

public class Student_Quiz {
    String choice;
    String rollNumber;
    public int exitStatus = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> Courses;
    // public int exitStatus = 0;
    public Student_Quiz(String rollNumber) {
        
        this.rollNumber = rollNumber;
        System.out.println("Selection Menu");
        System.out.println("1\t Attempt a Quiz: ");
        System.out.println("2\t View Grades: ");
        System.out.println("0\t Exit");
        System.out.print("\nEnter your selection: ");
        this.choice = sc.nextLine();
        Manage_Courses mc = new Manage_Courses();
        this.Courses = mc.findCoursesStudent(rollNumber);
        switch (this.choice) {
            case "1": attemptAQuiz(); break;
            case "2": viewGrades(); break;
            case "0": exitStatus = -1; return;  
            default : System.out.println("\nWrong Selection\n");return;
        }
    }
    private void attemptAQuiz() {
        
        ArrayList<String> Quiz = new ArrayList<>();
        if(Courses.isEmpty()){
            System.out.println("\nYou are not enrolled in any Course.\n");
            return;
        }else{
            // System.out.println("\nChoose one of the following Quizes to Attempt.\n");
        }
        for (int i=0 ;i < Courses.size();i++) {
            try{
                File file = new File("QuizDatabase.txt");
                Scanner inStream = new Scanner(file);
                while (inStream.hasNext()) {
                    String line = inStream.nextLine();
                    String[] data = line.split(",");
                    if (data[0].equalsIgnoreCase(Courses.get(i))) {
                        boolean flag = false;
                        for(int j=2;j<data.length;j++){
                            if(data[j].equalsIgnoreCase(rollNumber)){
                                flag = true;
                                break;
                            }
                        }
                        if(!flag){
                            Quiz.add(data[1]);
                        }
                    }
                }
                inStream.close();
            }catch(Exception e){
                System.out.println("\nError Reading the File!" + e.getMessage());
            }
        }
        int empty = 0;
        if(Quiz.isEmpty()){
            System.out.println("\nNo quizzes available for the enrolled courses.\n");
            empty = 1;
            return;
        }
        if(empty == 0){
            System.out.println("\nChoose one of the following Quizes to Attempt.\n");
        }
        for(int i = 0; i<Quiz.size();i++){
            System.out.println((i+1)+". "+Quiz.get(i));
        }
        System.out.println();
        System.out.print("\nSelect a quiz to attempt by entering its number or '0' to exit: "); 
        String choice = sc.nextLine();
        int index = Integer.parseInt(choice);
        // System.out.println(choice + "," + index);
        if(index<=0 || index > Quiz.size()){
            return;
        }
        index--;
        String quizName = Quiz.get(index);
        String originalQuizName = quizName;
        quizName += ".txt";
        try{
            File file = new File(quizName);
            Scanner inStream = new Scanner(file);
            int score = 0;
            if(inStream.hasNext()){
                inStream.nextLine();
            }
            while (inStream.hasNext()) {
                String line = inStream.nextLine();
                String data[] = line.split(",");
                System.out.println(data[0]+" : ");
                System.out.println("a. "+data[1]);
                System.out.println("b. "+data[2]);
                System.out.println("c. "+data[3]);
                System.out.println("d. "+data[4]);
                System.out.print("Enter your answer: ");
                String ans = sc.nextLine();
                String correctAns = data[5];
                if(ans.equalsIgnoreCase(correctAns)){
                    score++;
                }
            }
            inStream.close();
            System.out.println("Your Score is " +score);
            File file1 = new File("QuizDatabase.txt");
            File temp = new File("QuizDatabaseTemp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp,true));
            Scanner sc1 = new Scanner(file1);
            while (sc1.hasNext()){
                String line = sc1.nextLine();
                String data[] = line.split(",");
                if(originalQuizName.equals(data[1])){
                    line += "," + rollNumber + "," + score;
                    bw.write(line);
                    bw.newLine();
                }else{
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
            sc1.close();
            file1.delete();
            temp.renameTo(file1);
        }catch(Exception e){
            System.out.println("\nError making changes to the File: " + e.getMessage());
        }
    }
    private void viewGrades(){
        ArrayList<String> Grades = new ArrayList<>();
        ArrayList<String> Course = new ArrayList<>();
        ArrayList<String> QuizName = new ArrayList<>();
        ArrayList<String> maxMarks = new ArrayList<>();
        try{
            File file = new File ("QuizDatabase.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String line = scan.nextLine();
                String data []= line.split(",");
                for(int i=3;i<data.length;i++){
                    if(data[i].equalsIgnoreCase(rollNumber)){
                        Grades.add(data[i+1]);
                        Course.add(data[0]);
                        QuizName.add(data[1]);
                        maxMarks.add(data[2]);
                        break;
                    }
                }
            }
            scan.close();
            if(Grades.isEmpty()){
                System.out.println("\nNo Grades Found.\n");
            }else{
                System.out.print("\nRoll Number \t Subject \t Quiz Name \t Score \t Maximum Score\n");
                for(int i = 0; i < Grades.size(); i++){
                    String formattedLine = String.format("%-17s %-13s %-16s %-8s %-14s", rollNumber, Course.get(i), QuizName.get(i), Grades.get(i), maxMarks.get(i));
                    System.out.println(formattedLine);
                }
                System.out.println();
                    
            }
        }catch(Exception e){
            System.out.println("Error viewing the Grades: " + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     while (true)new Student_Quiz("22BCE308");
    // }

}
