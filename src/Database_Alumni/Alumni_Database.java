package Database_Alumni;

import java.io.*;
import java.util.*;

public class Alumni_Database extends Database {
    String choice;
    Scanner sc = new Scanner(System.in);
    public int exitStatus = 0;
    public Alumni_Database(int flag) {
        if(flag == 1){
            System.out.println("Selection Menu");
            System.out.println("1\t Add new alumini data");
            System.out.println("2\t Search for an alumini by name");
            System.out.println("3\t Update the information of a specific alumini");
            System.out.println("4\t Delete an alumini from the database");
            System.out.println("5\t Display all alumini in the database");
            System.out.println("0\t Exit");
            System.out.print("\nEnter your selection: ");
            this.choice = sc.nextLine();
            switch (this.choice) {
                case "1": addAluminiData(); break;
                case "2": searchByName(); break;
                case "3": updateInfo(); break;
                case "4": deleteFromDB(); break;
                case "5": displayAll(); break;
                case "0": exitStatus = -1; return;  
                default : System.out.println("\nWrong Selection\n");return;

            }
        }else if(flag == 0){
            System.out.println("Selection Menu");
            System.out.println("1\t Search for an alumini by name");
            System.out.println("2\t Display all alumini in the database");
            System.out.println("0\t Exit");
            System.out.print("\nEnter your selection: ");
            this.choice = sc.nextLine();
            switch (this.choice) {
                case "1": searchByName(); break;
                case "2": displayAll(); break;
                case "0": exitStatus = -1; return;  
                default : System.out.println("\nWrong Selection\n");return;

            }
        }
        
    }
    @Override
    public void addAluminiData() {
        File file = new File("AlumniData.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.print("\nEnter Name : ");
            String name = sc.nextLine();
            System.out.print("Enter Age : ");
            int age = Integer.parseInt(sc.next());
            System.out.print("Enter Gender : ");
            char gender = Character.toLowerCase(sc.next().charAt(0));
            System.out.print("Enter Course : ");
            String course = sc.next();
            System.out.print("Enter Roll Number : ");
            String rollNumber = sc.next();
            System.out.print("Enter Year Graduated : ");
            int yearGraduated = Integer.parseInt(sc.next());
            System.out.print("Enter Job Position : ");
            String jobPosition = sc.next();
            System.out.print("Enter Company Name : ");
            String companyName = sc.next();
            System.out.print("Enter Contact Number : ");
            long contactNumber = Long.parseLong(sc.next());
            System.out.print("Enter Email Address : ");
            String emailAddress = sc.next();
            System.out.print("Enter Current City : ");
            String currentCity = sc.next();
            System.out.print("Enter Current Country : ");
            String currentCountry = sc.next();
            System.out.print("Enter Permanent City : ");
            String permanentCity = sc.next();
            System.out.print("Enter Permanent Country : ");
            String permanentCountry = sc.next();

            bw.write(name + "," + age + "," + gender + "," + course + "," + rollNumber + "," + yearGraduated + "," + jobPosition + "," + companyName + "," + contactNumber + "," + emailAddress + "," + currentCity + "," + currentCountry + "," + permanentCity + "," + permanentCountry);
            bw.newLine();

            System.out.println("Data has been successfully written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    @Override
    public void searchByName(){
        File file = new File("AlumniData.txt");
        if(!file.exists()) {
            System.out.println("File does not exist!");
            return;
        }
        System.out.print("Enter the Name or Roll Number you want to search :");
        String name = sc.nextLine();
        try{
            Scanner inStream = new Scanner(file);
            // System.out.println(inStream);
            int c = 0;
            while(inStream.hasNextLine()){
                String data= inStream.nextLine();
                String[] row = data.split(",");
                String[] disp = {"Name: ","Age: ","Gender: ","Course: ","Roll Number: ","Year Graduated: ","Job Position: ","Company Name: ","Contact Number: ","Email Address: ","Current City: ","Current Country: ","Permanent City: ","Permanent Country: "};
                if(row[4].equalsIgnoreCase(name)){
                    System.out.println("Alumni Found");
                    for(int i=0; i<row.length; i++){
                        System.out.println(disp[i] + row[i]);
                        // System.out.println(row[i] + " ");
                    }
                    c = 1;
                    break;
                }
                if(row[0].equalsIgnoreCase(name)){
                    System.out.println("Alumni Found");
                    System.out.println();
                    for(int i=0; i<row.length; i++){
                        // System.out.println(row[i] + " ");
                        System.out.println(disp[i] + row[i]);
                    }
                    System.out.println();
                    c = 1;
                    break;
                }
            }
            if(c == 0){
                System.out.println("No Alumni Found with that Name or Roll Number");
            }
            inStream.close();
        }catch(Exception e){
            System.out.println(e);
        }
        

    }

    @Override
    public void updateInfo(){
        File file = new File("AlumniData.txt");
        if (!file.exists()) {
            System.out.println("File does not exists!");
            return;
        }
        System.out.print("\nName of alumnus: ");
        String name = sc.nextLine();
        try{
            Scanner inStream = new Scanner(file);
            int c = 0;
            while(inStream.hasNextLine()){
                String data= inStream.nextLine();
                String[] row = data.split(",");
                if(row[0].equalsIgnoreCase(name)){
                    c = 1;
                    // deleteFromDB(name);
                    break;
                }
            }
            inStream.close();

            if(c==1){
                deleteFromDB(name);
            }
            if(c == 0){
                System.out.println("No Alumni Found with that Name");
                return;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("New Information: ");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.print("Enter Age : ");
            int age = Integer.parseInt(sc.next());
            System.out.print("Enter Gender : ");
            char gender = Character.toLowerCase(sc.next().charAt(0));
            System.out.print("Enter Course : ");
            String course = sc.next();
            System.out.print("Enter Roll Number : ");
            String rollNumber = sc.next();
            System.out.print("Enter Year Graduated : ");
            int yearGraduated = Integer.parseInt(sc.next());
            System.out.print("Enter Job Position : ");
            String jobPosition = sc.next();
            System.out.print("Enter Company Name : ");
            String companyName = sc.next();
            System.out.print("Enter Contact Number : ");
            long contactNumber = Long.parseLong(sc.next());
            System.out.print("Enter Email Address : ");
            String emailAddress = sc.next();
            System.out.print("Enter Current City : ");
            String currentCity = sc.next();
            System.out.print("Enter Current Country : ");
            String currentCountry = sc.next();
            System.out.print("Enter Permanent City : ");
            String permanentCity = sc.next();
            System.out.print("Enter Permanent Country : ");
            String permanentCountry = sc.next();

            bw.write(name + "," + age + "," + gender + "," + course + "," + rollNumber + "," + yearGraduated + "," + jobPosition + "," + companyName + "," + contactNumber + "," + emailAddress + "," + currentCity + "," + currentCountry + "," + permanentCity + "," + permanentCountry);
            bw.newLine();
            
            System.out.println("Data has been successfully written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }

    @Override
    public void deleteFromDB(){
        File file = new File("AlumniData.txt");
        if(!file.exists()){
            System.out.println("File does not exists!");
            return;
        }
        System.out.println("\nDelete an alumni from database ");
        System.out.print("Name of the alumnus to be deleted: ");
        String name = sc.nextLine();
        int flag = 0;
        
        try{
            File file1 = new File("AlumniData_temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter temp = new BufferedWriter(new FileWriter(file1, true));
            Scanner inStream = new Scanner(file);
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (!data[0].equalsIgnoreCase(name)) {
                    temp.write(line + "\n");
                }else{
                    flag = 1;
                }
            }
            if(flag==1){
                System.out.println("Successfully deleted the Almnus.");
            }else{
                System.out.println("No such Alumnus found in our records.");
            }
            inStream.close();
            temp.flush();
            temp.close();
            // br.flush();
            br.close();
            // file.delete();
            if (file.delete()) {
                // System.out.println("Original file deleted.");
            } else {
                System.out.println("Failed to delete the original file.");
            }
            
            file1.renameTo(file);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteFromDB(String name){
        File file = new File("AlumniData.txt");
        if(!file.exists()){
            System.out.println("File does not exists!");
            return;
        }
        
        int flag = 0;
        
        try{
            File file1 = new File("AlumniData_temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter temp = new BufferedWriter(new FileWriter(file1, true));
            Scanner inStream = new Scanner(file);
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] data = line.split(",");
                if (!data[0].equalsIgnoreCase(name)) {
                    temp.write(line + "\n");
                }else{
                    flag = 1;
                }
            }
            if(flag==1){
                // System.out.println("Successfully deleted the Almnus.");
            }else{
                System.out.println("No such Alumnus found in our records.");
            }
            inStream.close();
            temp.flush();
            temp.close();
            br.close();
            if (file.delete()) {
                // System.out.println("Original file deleted.");
            } else {
                System.out.println("Failed to delete the original file.");
            }
            
            file1.renameTo(file);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void displayAll(){
        File file = new File("AlumniData.txt");
        if(!file.exists()) {
            System.out.println("File does not exist!");
            return;
        }
        try{
            Scanner inStream = new Scanner(file);
            while(inStream.hasNextLine()){
                String data= inStream.nextLine();
                String[] row = data.split(",");
                
                int cc = 0;
                String[] disp = {"Name: ","Age: ","Gender: ","Course: ","Roll Number: ","Year Graduated: ","Job Position: ","Company Name: ","Contact Number: ","Email Address: ","Current City: ","Current Country: ","Permanent City: ","Permanent Country: "};
                for(int i = 1; i<row.length;i++){
                    if(cc==0){
                        System.out.println("\nName: "+row[0] + "\n");
                    }
                    cc++;
                    System.out.println(disp[i] + row[i]);
                }
                System.out.println();    
            }
            inStream.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}