package net.javaguides.usermanagement.model;

public class User {

    protected int id;
    protected String name;
    protected String email;
    protected int age;
    protected String phoneNumber;
    protected String gender;
    protected String bloodGp;
    protected String password;

    public User() {
    }

    public User(String name, String email, String gender, int age, String phone, String bloodGp) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phone;
        this.gender = gender;
        this.bloodGp = bloodGp;
    }
    
    public User(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String gender, int age, String phone, String bloodGp) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phone;
        this.gender = gender;
        this.bloodGp = bloodGp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGp() {
        return bloodGp;
    }

    public void setBloodGp(String bloodGp) {
        this.bloodGp = bloodGp;
    }
}
