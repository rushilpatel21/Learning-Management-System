package Database_Alumni;

public abstract class Database {


    public abstract void addAluminiData();
    public abstract void searchByName();
    public abstract void updateInfo();
    public abstract void deleteFromDB(String name);
    public abstract void deleteFromDB();
    public abstract void displayAll();
    
}