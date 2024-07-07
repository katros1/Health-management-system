
package net.javaguides.usermanagement.model;

public class Appointment {

    private Long id;
    private String time;
    private String date;
    private String patientName;
    private String patientAge;
    private String doctor;

    public Appointment(Long id, String time, String date, String patientName, String patientAge,
            String doctor) {
        super();
        this.id = id;
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.doctor = doctor;
    }

    public Appointment(String time, String date, String patientName, String patientAge,
            String doctor) {
        super();
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patient) {
        this.patientName = patient;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String age) {
        this.patientAge = age;
    }
    
    public String getDoctor() {
        return doctor;
    }

    public void setDocotor(String doctor) {
        this.doctor = doctor;
    }


}
