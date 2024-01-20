import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Patient extends Person{
    private String ID, doctor, date;
    private ArrayList<String> medicines = new ArrayList<>();


    public Patient(String ID, String nameLastName, String doctor, String date, ArrayList<String> medicines) {
        super(nameLastName);

        this.ID = ID;
        this.doctor = doctor;
        this.date = date;
        for (int i = 0; i < medicines.size(); i++) {
            this.medicines.add(medicines.get(i));
        }

    }

    public static ArrayList<Patient> addPatientFromFile() {
        ArrayList<Patient> patients = new ArrayList<>();


        try {
            File myObj = new File("patients.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] splitedData = myReader.nextLine().split("-");
                ArrayList<String> medicines = new ArrayList<>();
                for (int i = 0; i < splitedData[4].split(" ").length; i++)
                    medicines.add(splitedData[4].split(" ")[i]);


                patients.add(new Patient(splitedData[0], splitedData[1], splitedData[2], splitedData[3], medicines));
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return patients;
    }

    public String getID() {
        return ID;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDate () {
        return date;
    }

    public ArrayList<String> getMedicines(){
         return medicines;
    }

    public void setMedicines (String medicines) {
        this.medicines.clear();
        this.medicines.addAll(Arrays.asList(medicines.split(" ")));
    }
}
