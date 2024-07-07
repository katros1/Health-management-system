
package net.javaguides.usermanagement.model;

public class Statics {
    protected int patients;
    protected int doctors;
    protected int nurses;
    protected int appointments;
    
    public Statics(int patients, int doctors, int nurses, int appointments) {
        super();
        this.patients = patients;
        this.nurses = nurses;
        this.doctors = doctors;
        this.appointments = appointments;
    }
    
    public int getPatient() {
        return patients;
    }
    public int getDoctor() {
        return doctors;
    }
    
    public int getNurse() {
        return nurses;
    }
    
    public int getAppointment() {
        return appointments;
    }
}
