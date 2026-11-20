package Lab_9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Q5 {
    private static final String FILE_NAME = "hospital-record.dat";

    public static void main(String[] args) {
        Doctor doctor = new Doctor("D001", "Dr. Priya Shah");
        Patient patient = new Patient("P001", "Rohan Verma", doctor);
        doctor.patient = patient;

        try {
            serializeDoctor(doctor);
            Doctor restoredDoctor = deserializeDoctor();
            System.out.println("Doctor: " + restoredDoctor.name);
            System.out.println("Patient: " + restoredDoctor.patient.name);
            System.out.println("Circular reference preserved: "
                    + (restoredDoctor.patient.doctor == restoredDoctor));
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Hospital serialization failed: " + exception.getMessage());
        }
    }

    private static void serializeDoctor(Doctor doctor) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            output.writeObject(doctor);
        }
    }

    private static Doctor deserializeDoctor() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Doctor) input.readObject();
        }
    }

    private static class Doctor implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String doctorId;
        private final String name;
        private Patient patient;

        private Doctor(String doctorId, String name) {
            this.doctorId = doctorId;
            this.name = name;
        }

        private void writeObject(ObjectOutputStream output) throws IOException {
            output.defaultWriteObject();
        }

        private void readObject(ObjectInputStream input)
                throws IOException, ClassNotFoundException {
            input.defaultReadObject();
        }
    }

    private static class Patient implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String patientId;
        private final String name;
        private Doctor doctor;

        private Patient(String patientId, String name, Doctor doctor) {
            this.patientId = patientId;
            this.name = name;
            this.doctor = doctor;
        }
    }
}
