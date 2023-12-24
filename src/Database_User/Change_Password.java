package Database_User;

import java.io.*;
import java.util.*;

public class Change_Password {
    public Change_Password(String rollNumber,int flag){
        Scanner sc = new Scanner(System.in);
        if(flag==1){
            try{
                boolean completed = false;
                File file = new File("UserDataFaculty.txt");
                File file1 = new File("UserDataFacultyTemp.txt");
                BufferedWriter temp = new BufferedWriter(new FileWriter(file1, true));
                Scanner inStream = new Scanner(file);
                String line;
                while (inStream.hasNextLine()) {
                    line = inStream.nextLine();
                    String[] arr = line.split(",");
                    if(arr[1].equalsIgnoreCase(rollNumber)){
                        System.out.print("\nEnter the Old Password: ");
                        String oldPass = sc.nextLine();
                        boolean ll = true;
                        int c1 = 0;
                        // int c2 = 0;
                        while (ll) {   
                            if(c1!=0){
                                System.out.print("Enter your Old Password Again: ");
                                oldPass = sc.nextLine();
                                c1 = 0;
                            }                     
                            if(arr[2].equals(oldPass)){
                                System.out.print("\nEnter the New Password: ");
                                String newPass = sc.nextLine();
                                System.out.print("\nConfirm the New Password: ");
                                String confirmNewPass = sc.nextLine();
                                // if(c2!=0){
                                //     System.out.println("Enter your New Password Again");
                                //     newPass = sc.nextLine();
                                //     System.out.println("\nConfirm the New Password Again");
                                //     confirmNewPass = sc.nextLine();
                                //     c2 = 0;
                                // }
                                if(newPass.equals(confirmNewPass)) {
                                    String tempString = arr[0] + "," + arr[1] + "," + newPass;
                                    temp.write(tempString);
                                    temp.newLine();
                                    completed = true;
                                    ll = false;
                                }else{
                                    System.out.println("\nThe New Passwords do not match!");
                                    System.out.print("\nDo you want to try again?[Y/N] : ");
                                    String c=sc.nextLine();
                                    if(c.equalsIgnoreCase("Y")){
                                        // c2++;
                                        continue;
                                    }else{
                                        ll = false;
                                    }
                                }
                            }else{
                                System.out.println("\nIncorrect Old Password!");
                                System.out.print("\nDo you want to try again?[Y/N] : ");
                                String c=sc.nextLine();
                                if(c.equalsIgnoreCase("Y")){
                                    c1++;
                                    continue;
                                }else{
                                    ll = false;
                                }
                            }
                        }
                    }else{
                        temp.write(line);
                        temp.newLine();
                    }
                }
                inStream.close();
                temp.flush();
                temp.close();
                if(completed){
                    file.delete();
                    file1.renameTo(file);
                }else{
                    file1.delete();
                }
                
            }catch(IOException e){
                System.out.println("Error Caught During Changing the Password: " + e.getMessage() );
            }

        }else if(flag == 2){
            try{
                boolean completed = false;
                File file = new File("UserDataStudent.txt");
                File file1 = new File("UserDataStudentTemp.txt");
                BufferedWriter temp = new BufferedWriter(new FileWriter(file1, true));
                Scanner inStream = new Scanner(file);
                String line;
                while (inStream.hasNextLine()) {
                    line = inStream.nextLine();
                    String[] arr = line.split(",");
                    if(arr[1].equalsIgnoreCase(rollNumber)){
                        System.out.print("\nEnter the Old Password: ");
                        String oldPass = sc.nextLine();
                        boolean ll = true;
                        int c1 = 0;
                        // int c2 = 0;
                        while (ll) {   
                            if(c1!=0){
                                System.out.print("Enter your Old Password Again: ");
                                oldPass = sc.nextLine();
                                c1 = 0;
                            }                     
                            if(arr[2].equals(oldPass)){
                                System.out.print("\nEnter the New Password: ");
                                String newPass = sc.nextLine();
                                System.out.print("\nConfirm the New Password: ");
                                String confirmNewPass = sc.nextLine();
                                // if(c2!=0){
                                //     System.out.println("Enter your New Password Again");
                                //     newPass = sc.nextLine();
                                //     System.out.println("\nConfirm the New Password Again");
                                //     confirmNewPass = sc.nextLine();
                                //     c2 = 0;
                                // }
                                if(newPass.equals(confirmNewPass)) {
                                    String tempString = arr[0] + "," + arr[1] + "," + newPass;
                                    temp.write(tempString);
                                    temp.newLine();
                                    completed = true;
                                    ll = false;
                                }else{
                                    System.out.println("\nThe New Passwords do not match!");
                                    System.out.print("\nDo you want to try again?[Y/N] : ");
                                    String c=sc.nextLine();
                                    if(c.equalsIgnoreCase("Y")){
                                        // c2++;
                                        continue;
                                    }else{
                                        ll = false;
                                    }
                                }
                            }else{
                                System.out.println("\nIncorrect Old Password!");
                                System.out.print("\nDo you want to try again?[Y/N] : ");
                                String c=sc.nextLine();
                                if(c.equalsIgnoreCase("Y")){
                                    c1++;
                                    continue;
                                }else{
                                    ll = false;
                                }
                            }
                        }
                    }else{
                        temp.write(line);
                        temp.newLine();
                    }
                }
                inStream.close();
                temp.flush();
                temp.close();
                if(completed){
                    file.delete();
                    file1.renameTo(file);
                }else{
                    file1.delete();
                }
                
            }catch(IOException e){
                System.out.println("Error Caught During Changing the Password: " + e.getMessage() );
            }
        }
    }
    // public static void main(String[] args) {
    //     new Change_Password("16OOP005",1);
    // }
}
