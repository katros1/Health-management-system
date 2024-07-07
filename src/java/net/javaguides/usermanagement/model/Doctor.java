package net.javaguides.usermanagement.model;

public class Doctor {
	
	    private Long id;
	    private String firstName;
	    private String lastName;
	    private String specialization;
	    private String email;
	    private String phoneNumber;
	    private String address;
	    private String licenseNumber;
	    
		public Doctor(Long id, String firstName, String lastName, String specialization, String email,
				String phoneNumber, String address, String licenseNumber) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.specialization = specialization;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.licenseNumber = licenseNumber;
		}
		
		public Doctor(String firstName, String lastName, String specialization, String email, String phoneNumber,
				String address, String licenseNumber) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.specialization = specialization;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.licenseNumber = licenseNumber;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getSpecialization() {
			return specialization;
		}
		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getLicenseNumber() {
			return licenseNumber;
		}
		public void setLicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
		}
	    
	    
	    
	    

}
