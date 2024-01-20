import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static ArrayList<Patient> patients = new ArrayList<>();


    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Receptionist receptionist = new Receptionist("ali", "hamidi");
        patients = Patient.addPatientFromFile();
        Nurse nurse = null;



        String state = "main";

        do {

            //show menu
            if(state.equals("main")) {
                System.out.println("1.Nurse");
                System.out.println("2.Ptient Reception");
                System.out.println("3.Exit");
            }else if(state.equals("nurse")) {
                System.out.println("1.Update patient's file");
                System.out.println("2.Change password");
                System.out.println("3.Back");
            }else if(state.equals("patient")) {
                System.out.println("1.Add new patient");
                System.out.println("2.Remove patient");
                System.out.println("3.Search patient");
                System.out.println("4.Show all patient's file");
                System.out.println("5.Back");
            }

            String s = sc.next();
            if(!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5")) {
                System.out.println("Wrong input.");
            }else {

                //if in main menu
                if(state.equals("main")) {
                    if (s.equals("1")) {
                        state = "nurse";
                        nurse = Nurse.Enter();
                        if(nurse == null)
                            System.exit(0);
                    }
                    else if (s.equals("2"))
                        state = "patient";
                    else
                        System.exit(0);
                }
                //if in nurse menu
                else if(state.equals("nurse")) {
                    //updating patient's file
                    if (s.equals("1")) {
                        System.out.println("Enter patient's ID: ");
                        String id = sc.next();
                        boolean flag = false;
                        for (int i = 0; i < patients.size(); i++) {
                            if (patients.get(i).getID().equals(id)) {
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) {
                            System.out.println("There's no such patient.");
                        }else {
                            nurse.updatePatientFile(patients, id);
                        }
                    }
                    //change password
                    else if (s.equals("2")) {
                        do {
                            System.out.println("Enter current password: ");
                            if(sc.next().equals(nurse.getPassword()))
                                break;
                            else
                                System.out.println("Wrong password. try again.");
                        }while (true);

                        System.out.println("Enter new password:");
                        String newPass = sc.next();
                        nurse.changePss(newPass);

                    }
                    //back
                    else {
                        state = "main";
                    }
                }
                //else if in patient menu
                else {
                    //Add new patient
                    if(s.equals("1")) {
                        receptionist.addNewPatient();
                    }
                    //Remove patient
                    else if(s.equals("2")) {
                        System.out.println("Enter patient's ID:");
                        String patientID = sc.next();
                        receptionist.removePatient(patientID);
                    }
                    //Search patient
                    else if (s.equals("3")) {
                        System.out.println("Enter patient's ID:");
                        String patientID = sc.next();
                        receptionist.searchAndShowPatient(patientID);
                    }
                    //Show all patient's file
                    else if(s.equals("4")) {
                        receptionist.showAllPatients();
                    }
                    //back
                    else {
                        state = "main";
                    }
                }
            }


        }while (true);

    }
}