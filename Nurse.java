import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Nurse extends Person{
    private String ID, password;


    public Nurse(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }


    public static boolean nurse_validation(String nurseID, String password) {


        try {
            File myObj = new File("nurses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] splitedData = myReader.nextLine().split("-");
                if(splitedData[0].equals(nurseID) && splitedData[1].equals(password))
                    return true;
            }
            myReader.close();
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public static Nurse Enter() {
        Scanner sc = new Scanner(System.in);

        int tries = 3;
        System.out.println("enter nurse ID and password:");
        do {

            String id = sc.next();
            String pass = sc.next();

            tries--;
            if(tries == 0){
                System.out.println("You do not have permission to access the application.");
                break;
            }

            if(!Nurse.nurse_validation(id, pass))
                System.out.println("access denied. please try again.");
            else {
                return new Nurse(id, pass);
            }
        }while (tries != 0);

        return null;
    }

    public void changePss(String newPass) {
        this.password = newPass;

        ArrayList<Nurse> nurses = new ArrayList<>();


        try {
            File myObj = new File("nurses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] splitedData = myReader.nextLine().split("-");
                nurses.add(new Nurse(splitedData[0], splitedData[1]));
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(int i = 0; i < nurses.size(); i++) {
            if(nurses.get(i).getID().equals(this.ID)) {
                nurses.get(i).setPassword(newPass);
            }
        }

        //delete all from file
        try {
            PrintWriter writer = new PrintWriter("nurses.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write updated patients in file
        try {
            FileWriter myWriter = new FileWriter("nurses.txt");


            for (int j = 0; j < nurses.size(); j++) {
                String id = nurses.get(j).getID();
                String pass = nurses.get(j).getPassword();

                String newNurse = id + "-" + pass + '\n';
                myWriter.write(newNurse);
            }

            System.out.println("Changed successfully.");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void updatePatientFile(ArrayList<Patient> patients, String patientID) {
        System.out.println("Enter new medicine for patient(use ',' for separating):");
        String medicines = String.join(" ", new Scanner(System.in).nextLine().split(","));

        for(int i = 0; i < patients.size(); i++)
            if (patients.get(i).getID().equals(patientID))
                patients.get(i).setMedicines(medicines);




        //delete all from file
        try {
            PrintWriter writer = new PrintWriter("patients.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write updated patients in file
        try {
            FileWriter myWriter = new FileWriter("patients.txt");


            for (int j = 0; j < patients.size(); j++) {
                String id = patients.get(j).getID();
                String nameLastName = patients.get(j).getName() + " " + patients.get(j).getLastName();
                String doctor = patients.get(j).getDoctor();
                String date = patients.get(j).getDate();
                String[] mediciness = patients.get(j).getMedicines().toArray(new String[0]);

                String newPatient = id + "-" + nameLastName + "-" + doctor + "-" + date + "-" + String.join(" ", mediciness) + '\n';
                myWriter.write(newPatient);
            }

            System.out.println("Updated successfully.");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getID () {
        return ID;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
