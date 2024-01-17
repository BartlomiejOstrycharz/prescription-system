// prescription.model.ts
import { Doctor } from "./Doctor";
import { Medication } from "./Medication";
import { Patient } from "./Patient";

export interface Prescription {
  prescriptionId: string;
  patient: Patient;
  doctor: Doctor;
  prescriptionDate: string;
  quantity: number;
  instructions: string;
  medication: Medication;
}