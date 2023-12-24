package Course;

import java.io.*;
import java.util.*;

public class Quiz {
    String choice;
    String name;
    public int exitStatus = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> Course;
    // public int exitStatus = 0;
    public Quiz(String name) {
        Manage_Courses mc = new Manage_Courses();
        Course = mc.toCheckMyCourse(name);
        this.name = name;
        System.out.println("Selection Menu");
        System.out.println("1\t Create a Quiz");
        // System.out.println("2\t View all quizzes");
        System.out.println("2\t View Result of the Quiz");
        System.out.println("3\t Delete a Quiz");
        // System.out.println("4\t View Student Score in a Quiz");
        System.out.println("0\t Exit");
        System.out.print("\nEnter your selection: ");
        this.choice = sc.nextLine();
        switch (this.choice) {
            case "1": createQuiz(); break;
            // case "2": viewallquizes(); break;
            // case "3": deletequiz(); break;
            case "2": viewAttemptedBy(); break;
            case "3": deleteQuiz(); break;
            // case "4": viewScoreOfStudent(); break;
            case "0": exitStatus = -1; return;  
            default : System.out.println("\nWrong Selection\n");return;
        }
            
    }
    private void createQuiz(){
        if(Course.isEmpty()){
            System.out.println("You do not manage any course!");
            return;
        }
        System.out.println("\nWelcome "+name+", You can create quiz in the following courses:");
        for(int i=0 ;i < Course.size() ; i++){
            System.out.println((i + 1)+". "+Course.get(i));
        }
        System.out.print("\nChoose course to create quiz from(Enter the number corresponding to the Course Name): ");
        String number = sc.nextLine();
        int n = Integer.parseInt(number);
        // System.out.println(number+" " + n+ " " + Course.size());
        if(n > Course.size() || n <= 0){
            System.out.println("\nInvalid Input! Please enter correct number.");
            createQuiz();
            return;
        }else{
            System.out.println("\nCreating Quiz in "+Course.get(n-1));
            System.out.print("\nEnter the name for the Quiz: ");
            String questionName = sc.nextLine();
            System.out.print("Enter the Maximum Marks for the Quiz: ");
            String maxMarks = sc.nextLine();
            String fileName = questionName + ".txt";
            if(fileName.equalsIgnoreCase("QuizDatabase.txt") || fileName.equalsIgnoreCase("QuizDatabase")){
                System.out.println("\nPlease choose another name!");
                createQuiz();
                return;
            }
            // questionName += ".txt";
            try{
                File file = new File(fileName);
                File file1 = new File("QuizDatabase.txt");
                if(!file.exists()){
                    file.createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    BufferedWriter temp = new BufferedWriter(new FileWriter(file1,true));
                    temp.write(Course.get(n-1) + "," + questionName + "," + maxMarks);
                    temp.newLine();
                    temp.flush();
                    temp.close();
                    bw.write(questionName + "," + Course.get(n-1));
                    bw.newLine();
                    System.out.print("Enter how many questions do you want to put in the quiz: ");
                    // int numQues = sc.nextInt();
                    String numOfQuestion = sc.nextLine();
                    int numQues = Integer.parseInt(numOfQuestion);
                    System.out.println();
                    for(int j=0 ;j<numQues ;j++ ){
                        System.out.print("Enter the Question Number"+(j+1)+" :");
                        String question = sc.nextLine();
                        System.out.print("Enter Option A:");
                        String optionA = sc.nextLine();
                        System.out.print("Enter Option B:");
                        String optionB = sc.nextLine();
                        System.out.print("Enter Option C:");
                        String optionC = sc.nextLine();
                        System.out.print("Enter Option D:");
                        String optionD = sc.nextLine();
                        System.out.print("Correct Answer (a/b/c/d):");
                        String ans = sc.nextLine();
                        if(ans.equalsIgnoreCase("a")||ans.equalsIgnoreCase("b")||ans.equalsIgnoreCase("c")||ans.equalsIgnoreCase("d")){
                            bw.write(question + "," + optionA + "," + optionB + "," + optionC + "," + optionD + "," + ans);
                            bw.newLine();
                        }else{
                            System.out.println("Wrong Input for Correct Answer!");
                            j--;

                        }
                        
                    }
                    bw.flush();
                    bw.close();
                }else{
                    System.out.println("\nQuiz already exists!");
                    return;
                }
            }catch(Exception e){
                System.out.println("\nError Occured Creating The Quiz!");
            }
        }


        
    }
    private void viewAttemptedBy(){
        File file = new File("QuizDatabase.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                String Course = data[0];
                String QuizName = data[1];
                String maxMarks = data[2];

                System.out.println("\n\nCourse: " + Course);
                System.out.println("Quiz Name: "+ QuizName);
                System.out.println("Max Marks: "+maxMarks+"\n");
                int check = 0;
                for(int i=3;i<data.length;i++){
                    System.out.println(i-2 + ". " + data[i] + " - " + data[i+1]);
                    i++;
                    check = 1;
                }
                if(check==0){
                    System.out.println("\nNo User have Attempted this Quizzes.\n");
                }
                System.out.println();

            }
            sc.close();
        }catch(Exception e){
            System.out.println("Error in viewing the Result: " + e.getMessage());
        }
    }
    private void deleteQuiz(){
        File dataBase = new File("QuizDatabase.txt");
        ArrayList<String> Quiz = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(dataBase);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String data[] = line.split(",");
                boolean exists = Course.stream().anyMatch(name -> name.equalsIgnoreCase(data[0]));
                if(exists){
                    Quiz.add(data[1]);
                }
            }
            scanner.close();
        }catch(Exception e){
            System.out.println("Caught an Exception viewing the Courses: "+e.getMessage());
        }
        System.out.println("Enter the name of the Quiz you want to Delete: ");
        for(int i=0;i<Quiz.size();i++){
            System.out.println((i+1)+". "+Quiz.get(i));
        }
        System.out.print("\nEnter your selection(Enter 0 to Exit): ");
        String QuizName = sc.nextLine();
        int index = Integer.parseInt(QuizName);
        if(index==0){
            return;
        }
        try{
            index--;
            if(index<0 || index >= Quiz.size()){
                System.out.println("Input is not valid.");
                System.out.print("Do you want to exit[Y/N]: ");
                String ch1 = sc.nextLine();
                if(ch1.equalsIgnoreCase("y")){
                    return;
                }
                deleteQuiz();
                return;
            }
            // System.out.println("testing" + index);
            QuizName = Quiz.get(index);

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid Index: ");
        }
        String originalQuizName = QuizName;
        QuizName += ".txt";
        
        File temp = new File("QuizDatabaseTemp.txt");
        if(!dataBase.exists()){
            System.out.println("There are no Quiz Created.");
            return;
        }
        try {
            Scanner inScanner = new Scanner(dataBase);
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));
            boolean exists = false;
            while (inScanner.hasNext()){
                String line = inScanner.nextLine();
                String data[] = line.split(",");
                if(data[1].equalsIgnoreCase(originalQuizName)){
                    for(int i = 0; i<Course.size();i++){
                        if(Course.get(i).equalsIgnoreCase(data[0])){
                            exists = true;
                            File fileToDelete = new File(QuizName);
                            fileToDelete.delete();
                        }else{
                            // System.out.println("You are not allowed to delete this quiz.");
                            // bw.write(line);
                            // bw.newLine();
                        }
                    }
                    if(!exists){
                        System.out.println("You are not allowed to delete this quiz.");
                        bw.write(line);
                        bw.newLine();
                    }
                    // exists = true;

                }else{
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
            inScanner.close();
            dataBase.delete();
            temp.renameTo(dataBase);
            if (!exists) {
                System.out.println("\nNo such quiz found!");
            }else{
                System.out.println("\nQuiz deleted successfully.");
            }
        }catch(Exception e){
            System.out.println("An Error occured while deleting the quiz!");
        }
    }
    // public static void main(String[] args) {
    //     while(true)new Quiz("Jitali Patel");
    // }
}
