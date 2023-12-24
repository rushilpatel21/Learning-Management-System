package Database_User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Signup {
    public int category;
    public String name;
    public String email; //email is now used as roll number.
    String choice;
    int check = 0;
    Scanner sc = new Scanner(System.in);
    public Signup(){
        System.out.println("\n\n\tSIGN UP");
        System.out.println("\n\nSelection Menu\n");
        System.out.println("1\t Student");
        System.out.println("2\t Faculty");
        System.out.print("\nEnter your choice: ");
        this.choice = sc.nextLine();
        switch (this.choice) {
            case "1": studentSignUp(); break;
            case "2": facultySignUp(); break;
            default : System.out.println("\nInvalid Choice!\n"); return;
        }
        
    }
    void to_check(String email, int flag){
        if(flag==1){
            File file = new File("UserDataStudent.txt");
            if(!file.exists()) {
                System.out.println("File does not exist!");
                return;
            }
            try{
                Scanner inStream = new Scanner(file);
                while(inStream.hasNext()){
                    String data= inStream.nextLine();
                    String[] arr = data.split(",");
                    if(email.equalsIgnoreCase(arr[1])){
                        check = 1;
                        break;
                    }
                }
                inStream.close();
            }catch(Exception e){
                System.out.println("Error reading from the file.");
            }

        }else{
            File file = new File("UserDataFaculty.txt");
            if(!file.exists()) {
                System.out.println("File does not exist!");
                return;
            }
            try{
                Scanner inStream = new Scanner(file);
                while(inStream.hasNext()){
                    String data= inStream.nextLine();
                    String[] arr = data.split(",");
                    if(email.equalsIgnoreCase(arr[1])){
                        check = 1;
                        break;
                    }
                }
                inStream.close();
            }catch(Exception e){
                System.out.println("Error reading from the file.");
            }        
        }
    }
    private boolean isStrongPassword(String password){
        if(password==null || password.equals("") || password.length() < 8){
            return false;
        }
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).*$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(password);

        return m.find();
    }
    private void studentSignUp(){
        File file = new File("UserDataStudent.txt");
        if(!file.exists()) {
            try{
                file.createNewFile(); 
            }catch(IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String name, email, password;
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            System.out.print("Enter your Roll Number: ");
            email = sc.nextLine();
            to_check(email, 1);
            if(check==1){
                System.out.println("The Roll Number already exsists. Use Another Roll Number to Sign Up.");
                category = 0;
                return;
            }else{
                category = 1;
            }
            this.name = name;
            this.email = email;
            boolean check = false;
            while (!check) {
                System.out.print("Enter your password: ");
                password = sc.nextLine();
                boolean isStrong = isStrongPassword(password);
                if (isStrong) {
                    // System.out.println("The password is strong.");
                    check = true;
                    bw.write(name + "," + email + "," + password);
                    bw.newLine();
                    break;
                } else {
                    System.out.println("The password requires at least 8 characters, should contain at least one uppercase letter, one lowercase letter, one digit, and one of the special characters @, #, $, %, ^, &, +, or =.");
                    
                }
            }
            System.out.println("Student Registered Successfully.");
        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    private void facultySignUp(){
        File file = new File("UserDataFaculty.txt");
        if(!file.exists()) {
            try {
                file.createNewFile(); 
            } catch (IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
            // System.out.println("File does not exist!");
            // return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            String name, email, password;
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            System.out.print("Enter your Roll Number: ");
            email = sc.nextLine();
            to_check(email, 2);
            if(check==1){
                System.out.println("The Roll Number already exsists. Use Another Roll Number to Sign Up.");
                category = 0;
                return;
            }else{
                category = 2;
            }
            this.name = name;
            this.email = email;
            boolean check = false;
            while (!check) {
                System.out.print("Enter your password: ");
                password = sc.nextLine();
                boolean isStrong = isStrongPassword(password);
                if (isStrong) {
                    // System.out.println("The password is strong.");
                    check = true;
                    bw.write(name + "," + email + "," + password);
                    bw.newLine();
                    break;
                } else {
                    System.out.println("The password requires at least 8 characters, should contain at least one uppercase letter, one lowercase letter, one digit, and one of the special characters @, #, $, %, ^, &, +, or =.");
                    
                }
            }
            
            System.out.println("Faculty Registered Successfully.");
        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
}
