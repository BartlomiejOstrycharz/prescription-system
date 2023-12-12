package org.prescription.system.Model;

public class Patient {
    private Long patient_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private Gender gender;
    private String address;
    private String phone_number;
    private String email;

    // Getter methods
    public Long getPatient_id() {
        return patient_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }
}
