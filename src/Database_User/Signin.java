package Database_User;

import java.util.*;
import java.io.*;

public class Signin {
    public int category = 0;
    public String name;
    public String email; //email is now used as roll number.
    private String choice;
    
    Scanner sc = new Scanner(System.in);
    public Signin(){
        System.out.println("\n\n\tSIGN IN");
        System.out.println("\n\nSelection Menu\n");
        System.out.println("1\t Student");
        System.out.println("2\t Faculty");
        System.out.print("\nEnter your choice: ");
        choice = sc.nextLine();
        switch (choice){
            case "1" :
            studentSignIn();
            // category = 1;
            break;
            case "2" :
            facultySignIn();
            // category = 2;
            break;
            default :
            System.out.println("\nInvalid Choice!\n");
        }
    }
    private void studentSignIn(){
        try{
            File file = new File("UserDataStudent.txt");
            if(!file.exists()){
                // file.createNewFile();
                System.out.println("\nNo User in the Data Base yet. \n");
                return;
            }
            System.out.println("Student Sign In \n");
            System.out.print("Enter your Roll Number: ");
            String username = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            Scanner inStream = new Scanner(file);
            while(inStream.hasNext()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if((data[1].equals(username)) && (data[2].equals(password))) {
                    System.out.println("Login Successful!");
                    category = 1;
                    name = data[0];
                    this.email = data[1];
                    inStream.close();
                    return;
                }
            }
            inStream.close();
            System.out.println("\n\tRoll Number or Password is incorrect.");
        }catch(IOException e){
            System.out.println("Error in reading file.");
        }catch(Exception e){
            System.out.println("An error occurred.");
        }
    }
    private void facultySignIn(){
        try{
            File file = new File("UserDataFaculty.txt");
            if(!file.exists()){
                // file.createNewFile();
                System.out.println("\nNo User in the Data Base yet. \n");
                return;
            }
            System.out.println("Faculty Sign In \n");
            System.out.print("Enter your Roll Number: ");
            String username = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            Scanner inStream = new Scanner(file);
            while(inStream.hasNext()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if((data[1].equals(username)) && (data[2].equals(password))) {
                    System.out.println("Login Successful!\n");
                    category = 2;
                    name = data[0];
                    this.email = data[1];
                    inStream.close();
                    return;
                }
            }
            inStream.close();
            System.out.println("\n\nRoll Number or Password is incorrect.\n");
        }catch(IOException e){
            System.out.println("Error in reading file.");
        }catch(Exception e){
            System.out.println("An error occurred.");
        }
    }
}
