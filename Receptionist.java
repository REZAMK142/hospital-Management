import java.io.*;
import java.util.*;

import java.util.ArrayList;

public class Receptionist extends Person{


    public Receptionist(String name, String lastName) {
        super(name + lastName);
    }

    public void addNewPatient() {
        Scanner sc = new Scanner(System.in);
        String patientID, nameLastName, doctor, date;
        String[] medicines;

        System.out.println("Enter patient's id:");
        patientID = sc.nextLine();
        System.out.println("Enter patient's name and last name:");
        nameLastName = sc.nextLine();
        System.out.println("Enter patient's doctor:");
        doctor = sc.nextLine();
        System.out.println("Enter patient's date of hospitalization:");
        date = sc.nextLine();
        System.out.println("Enter patient's medicines(separate with ','):");
        medicines = sc.nextLine().split(",");


        try {
            String newPatient = patientID + "-" + nameLastName + "-" + doctor + "-" + date + "-" + String.join(" ", medicines) + '\n';
            File file = new File("patients.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(newPatient);
            bw.close();
            Main.patients.add(new Patient(patientID, nameLastName, doctor, date, new ArrayList<>(Arrays.asList(medicines))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePatient(String patientID) {
        boolean flag = false;
        for (int i = 0; i < Main.patients.size(); i++) {
            if(Main.patients.get(i).getID().equals(patientID)) {
                flag = true;

                Main.patients.remove(i);


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


                    for (int j = 0; j < Main.patients.size(); j++) {
                        String id = Main.patients.get(j).getID();
                        String nameLastName = Main.patients.get(j).getName() + " " + Main.patients.get(j).getLastName();
                        String doctor = Main.patients.get(j).getDoctor();
                        String date = Main.patients.get(j).getDate();
                        String[] medicines = Main.patients.get(j).getMedicines().toArray(new String[0]);

                        String newPatient = id + "-" + nameLastName + "-" + doctor + "-" + date + "-" + String.join(" ", medicines) + '\n';
                        myWriter.write(newPatient);
                    }

                    System.out.println("Removed successfully.");
                    myWriter.close();

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }


            }
        }
        if(!flag)
            System.out.println("There is no such patient.");
    }

    public void searchAndShowPatient(String id) {
        boolean flag = false;
        for (int i = 0; i < Main.patients.size(); i++) {
            Patient p = Main.patients.get(i);
            if(p.getID().equals(id)) {
                flag = true;
                System.out.println("Patient's ID: " + p.getID());
                System.out.println("Patient's name: " + p.getName());
                System.out.println("Patient's last name: " + p.getLastName());
                System.out.println("Patient's doctor: " + p.getDoctor());
                System.out.println("Date of hospitalization: " + p.getDate());
                System.out.println("Patient's medicines: " + String.join(",", p.getMedicines().toArray(new String[0])));
            }
        }
        if(!flag) {
            System.out.println("There's no such patient.");
        }
    }

    public void showAllPatients() {

        ArrayList<Patient> tempPatients = new ArrayList<>(Main.patients);

        Collections.sort(tempPatients, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {

                return p1.getID().compareTo(p2.getID());
            }
        });

        for (int i = 0; i < tempPatients.size(); i++) {
            Patient p = tempPatients.get(i);
            System.out.println("----------------------------------------------------");
            System.out.println("Patient's ID: " + p.getID());
            System.out.println("Patient's name: " + p.getName());
            System.out.println("Patient's last name: " + p.getLastName());
            System.out.println("Patient's doctor: " + p.getDoctor());
            System.out.println("Date of hospitalization: " + p.getDate());
            System.out.println("Patient's medicines: " + String.join(",", p.getMedicines().toArray(new String[0])));
        }
    }
}
