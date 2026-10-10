import java.util.*;
import java.io.*;

class Patient implements Serializable {
    int id, age;
    String name, gender, disease, doctor, date;
    boolean admitted = true;

    Patient(int id, String name, int age, String gender,
            String disease, String doctor, String date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.doctor = doctor;
        this.date = date;
    }

    void display() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Disease: " + disease);
        System.out.println("Doctor: " + doctor);
        System.out.println("Admission Date: " + date);
        System.out.println("Status: " +
                (admitted ? "Admitted" : "Discharged"));
    }
}

public class HospitalPatientManagement{

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();

    static Patient find(int id) {
        for (Patient p : patients) {
            if (p.id == id)
                return p;
        }
        return null;
    }

    static void register() {
        System.out.print("Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (find(id) != null) {
            System.out.println("Patient ID already exists!");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Gender: ");
        String gender = sc.nextLine();

        System.out.print("Disease: ");
        String disease = sc.nextLine();

        System.out.print("Doctor Name: ");
        String doctor = sc.nextLine();

        System.out.print("Admission Date: ");
        String date = sc.nextLine();

        patients.add(new Patient(id, name, age, gender,
                disease, doctor, date));

        System.out.println("Patient registered successfully!");
    }

    static void displayAll() {
        for (Patient p : patients)
            p.display();
    }

    static void searchID() {
        System.out.print("Enter Patient ID: ");
        Patient p = find(sc.nextInt());

        if (p == null)
            System.out.println("Patient not found!");
        else
            p.display();
    }

    static void searchDisease() {
        sc.nextLine();
        System.out.print("Enter disease: ");
        String disease = sc.nextLine();

        for (Patient p : patients) {
            if (p.disease.equalsIgnoreCase(disease))
                p.display();
        }
    }

    static void searchDoctor() {
        sc.nextLine();
        System.out.print("Enter doctor name: ");
        String doctor = sc.nextLine();

        for (Patient p : patients) {
            if (p.doctor.equalsIgnoreCase(doctor))
                p.display();
        }
    }

    static void update() {
        System.out.print("Enter Patient ID: ");
        Patient p = find(sc.nextInt());
        sc.nextLine();

        if (p == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("New Name: ");
        p.name = sc.nextLine();

        System.out.print("New Age: ");
        p.age = sc.nextInt();
        sc.nextLine();

        System.out.print("New Gender: ");
        p.gender = sc.nextLine();

        System.out.print("New Disease: ");
        p.disease = sc.nextLine();

        System.out.print("New Doctor: ");
        p.doctor = sc.nextLine();

        System.out.println("Patient updated successfully!");
    }

    static void discharge() {
        System.out.print("Enter Patient ID: ");
        Patient p = find(sc.nextInt());

        if (p == null)
            System.out.println("Patient not found!");
        else {
            p.admitted = false;
            System.out.println("Patient discharged!");
        }
    }

    static void showStatus(boolean status) {
        for (Patient p : patients) {
            if (p.admitted == status)
                p.display();
        }
    }

    static void save() {
        try {
            ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream("patients.dat"));

            out.writeObject(patients);
            out.close();

            System.out.println("Records saved!");
        } catch (Exception e) {
            System.out.println("Error saving records!");
        }
    }

    static void load() {
        try {
            ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream("patients.dat"));

            patients = (ArrayList<Patient>) in.readObject();
            in.close();

            System.out.println("Records loaded!");
        } catch (Exception e) {
            System.out.println("No saved records found!");
        }
    }

    public static void main(String[] args) {

        load();

        int choice;

        do {
            System.out.println("\n===== HOSPITAL PATIENT MANAGEMENT =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search by Patient ID");
            System.out.println("4. Search by Disease");
            System.out.println("5. Search by Doctor");
            System.out.println("6. Update Patient");
            System.out.println("7. Discharge Patient");
            System.out.println("8. Currently Admitted Patients");
            System.out.println("9. Discharged Patients");
            System.out.println("10. Save Records");
            System.out.println("11. Load Records");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: register(); break;
                case 2: displayAll(); break;
                case 3: searchID(); break;
                case 4: searchDisease(); break;
                case 5: searchDoctor(); break;
                case 6: update(); break;
                case 7: discharge(); break;
                case 8: showStatus(true); break;
                case 9: showStatus(false); break;
                case 10: save(); break;
                case 11: load(); break;
                case 0: save(); System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}