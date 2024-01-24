CREATE DATABASE IF NOT EXISTS project;
use project;

DROP TABLE IF EXISTS prescriptions;
DROP TABLE IF EXISTS medications;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS purposes;

CREATE TABLE patients (
    patient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth VARCHAR(255),
    gender ENUM('Male', 'Female', 'Other'),
    address VARCHAR(100),
    phone_number VARCHAR(15),
    email VARCHAR(100));

CREATE TABLE doctors (
    doctor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone_number VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(40),
    specialization VARCHAR(50));

CREATE TABLE purposes (
    purpose_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    purpose_name VARCHAR (50));

CREATE TABLE medications (
    medication_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    medication_name VARCHAR(50),
    purpose_id BIGINT NOT NULL,
    volume VARCHAR (30),
    side_effects VARCHAR(50),
    FOREIGN KEY (purpose_id) REFERENCES purposes(purpose_id));

CREATE TABLE prescriptions (
    prescription_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prescription_name VARCHAR(20) NOT NULL ,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    medication_id BIGINT NOT NULL,
    prescription_date VARCHAR(255),
    quantity INT,
    instructions VARCHAR(100),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
    FOREIGN KEY (medication_id) REFERENCES medications(medication_id));
	
INSERT INTO patients (first_name, last_name, date_of_birth, gender, address, phone_number, email)
VALUES
    ('John', 'Smith', '1988-02-10', 'Male', '123 Main Street', '555-123-4567', 'john.smith@gmail.com'),
    ('Emily', 'Johnson', '1995-09-15', 'Female', '456 Elm Avenue', '555-234-5678', 'emily.j@gmail.com'),
    ('Michael', 'Williams', '1980-06-20', 'Male', '789 Oak Road', '555-345-6789', 'michael.w@gmail.com'),
    ('Sarah', 'Jones', '1992-11-03', 'Female', '234 Pine Lane', '555-456-7890', 'sarah.j@gmail.com'),
    ('Robert', 'Davis', '1975-03-25', 'Male', '567 Cedar Street', '555-567-8901', 'robert.d@gmail.com'),
    ('Laura', 'Brown', '1987-08-14', 'Female', '890 Birch Avenue', '555-678-9012', 'laura.b@gmail.com'),
    ('William', 'Clark', '1990-04-08', 'Male', '123 Spruce Road', '555-789-0123', 'william.c@gmail.com'),
    ('Jennifer', 'Martinez', '1983-07-30', 'Female', '345 Cedar Lane', '555-890-1234', 'jennifer.m@gmail.com'),
    ('James', 'Miller', '1984-01-15', 'Male', '456 Pine Avenue', '555-901-2345', 'james.m@gmail.com'),
    ('Elizabeth', 'Thompson', '1998-05-17', 'Female', '789 Elm Street', '555-012-3456', 'elizabeth.t@gmail.com'),
	 ('Daniel', 'Anderson', '1989-03-12', 'Male', '234 Oak Avenue', '555-123-9876', 'daniel.a@gmail.com'),
    ('Olivia', 'Wilson', '1993-11-05', 'Female', '567 Maple Street', '555-234-8765', 'olivia.w@gmail.com'),
    ('Alexander', 'Thomas', '1985-07-19', 'Male', '123 Elm Road', '555-345-7654', 'alex.t@gmail.com'),
    ('Mia', 'White', '1997-04-27', 'Female', '456 Birch Lane', '555-456-6543', 'mia.w@gmail.com'),
    ('Matthew', 'Harris', '1982-09-08', 'Male', '789 Pine Road', '555-567-5432', 'matthew.h@gmail.com'),
    ('Sophia', 'Lee', '1991-01-14', 'Female', '345 Cedar Avenue', '555-678-4321', 'sophia.l@gmail.com'),
    ('Benjamin', 'Jackson', '1984-06-26', 'Male', '567 Spruce Street', '555-789-3210', 'benjamin.j@gmail.com'),
    ('Ava', 'Scott', '1988-12-03', 'Female', '123 Maple Lane', '555-890-2109', 'ava.s@gmail.com'),
    ('Jacob', 'Moore', '1994-08-07', 'Male', '890 Birch Road', '555-012-1098', 'jacob.m@gmail.com'),
    ('Emma', 'Evans', '1996-02-22', 'Female', '234 Cedar Street', '555-321-0987', 'emma.e@gmail.com');

INSERT INTO doctors (first_name, last_name, phone_number, email, password, specialization)
VALUES
    ('John', 'Doe', '123-456-789', 'john.doe@example.com', 'password123', 'Pediatrician'),
    ('Anna', 'Smith', '987-654-321', 'anna.smith@example.com', 'password456', 'Internist'),
    ('Peter', 'Fox', '555-123-789', 'peter.fox@example.com', 'password789', 'Surgeon'),
    ('Magdalene', 'Flower', '111-222-333', 'magdalene.flower@example.com', 'passwordabc', 'Dermatologist'),
    ('Tom', 'Rabbit', '444-555-666', 'tom.rabbit@example.com', 'passwordxyz', 'Ophthalmologist'),
    ('Emily', 'White', '777-888-999', 'emily.white@example.com', 'password123', 'Psychiatrist'),
    ('Daniel', 'Taylor', '111-222-333', 'daniel.taylor@example.com', 'password456', 'Cardiologist'),
    ('Sophie', 'Brown', '333-444-555', 'sophie.brown@example.com', 'password789', 'Orthopedic Surgeon'),
    ('Michael', 'Clark', '666-777-888', 'michael.clark@example.com', 'passwordabc', 'Gastroenterologist'),
    ('Olivia', 'Ward', '222-333-444', 'olivia.ward@example.com', 'passwordxyz', 'Neurologist');

INSERT INTO purposes (purpose_name)
VALUES
    ('Regular Checkup'),
    ('Emergency Care'),
    ('Vaccination'),
    ('Surgery'),
    ('Dermatological Consultation');

INSERT INTO medications (medication_name, purpose_id, volume, side_effects)
VALUES
    ('Aspirin', 3, '100mg', 'Headache, Upset Stomach'),
    ('Amoxicillin', 2, '250mg', 'Nausea, Diarrhea'),
    ('Ibuprofen', 1, '200mg', 'Stomach Pain, Heartburn'),
    ('Lipitor', 4, '20mg', 'Muscle Pain, Weakness'),
    ('Hydrocortisone Cream', 5, '30g', 'Skin Irritation, Burning Sensation'),
    ('Paracetamol', 3, '500mg', 'Nausea, Allergic Reaction'),
    ('Ciprofloxacin', 2, '500mg', 'Joint Pain, Tendon Rupture'),
    ('Acetaminophen', 1, '300mg', 'Liver Damage, Rash'),
    ('Simvastatin', 4, '40mg', 'Joint Pain, Constipation'),
    ('Clotrimazole Cream', 5, '20g', 'Skin Irritation, Redness');

INSERT INTO prescriptions (prescription_name, patient_id, doctor_id, medication_id, prescription_date, quantity, instructions)
VALUES
    ('RX123456', 1, 1, 1, '2023-01-15', 30, 'Take one tablet daily with food'),
    ('RX123456', 1, 1, 2, '2023-01-15', 10, 'Take one tablet daily with food'),
    ('RX123456', 1, 1, 4, '2023-01-15', 20, 'Take one tablet daily with food'),
    ('RX123456', 1, 1, 7, '2023-01-15', 14, 'Take one tablet daily with food'),
    ('RX789012', 2, 3, 4, '2023-02-05', 20, 'Take one tablet in the evening'),
    ('RX345678', 3, 2, 3, '2023-03-20', 15, 'Take two tablets every six hours'),
    ('RX345678', 3, 2, 1, '2023-03-20', 15, 'Take two tablets every six hours'),
    ('RX901234', 4, 5, 2, '2023-04-10', 25, 'Take one capsule with water'),
    ('RX567890', 5, 4, 5, '2023-05-01', 1, 'Apply a thin layer to affected area twice daily'),
    ('RX123457', 1, 3, 2, '2023-06-12', 10, 'Take one tablet with meals'),
    ('RX890123', 2, 2, 1, '2023-07-25', 5, 'Take one tablet every morning'),
    ('RX890123', 2, 2, 5, '2023-07-25', 10, 'Take one tablet every morning'),
    ('RX890123', 2, 2, 2, '2023-07-25', 30, 'Take one tablet every morning'),
    ('RX456789', 3, 4, 3, '2023-08-18', 18, 'Take two tablets before bedtime'),
    ('RX012345', 4, 1, 4, '2023-09-09', 30, 'Take one tablet after lunch'),
    ('RX678901', 5, 5, 5, '2023-10-02', 2, 'Apply to affected area as needed'),
    ('RX789010', 1, 2, 1, '2023-11-15', 10, 'Take one tablet with meals'),
    ('RX567891', 2, 4, 3, '2023-12-05', 5, 'Take one tablet in the morning'),
    ('RX567891', 2, 4, 5, '2023-12-05', 10, 'Take one tablet in the morning');
