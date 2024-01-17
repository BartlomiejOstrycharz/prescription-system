import { PrescriptionService } from './../service/prescription/prescription.service';
import { Component, EventEmitter, Output } from '@angular/core';
import { PatientService } from '../service/patient/patient.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-input-button',
  templateUrl: './input-button.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./input-button.component.css'],
})
export class InputButtonComponent {
  @Output() prescriptionNumberEntered = new EventEmitter<string>();

  prescriptionId: string = '';

  constructor(
    private prescriptionService: PrescriptionService,
    private patientService: PatientService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  public clearInput() {
    this.prescriptionId = '';
  }

  public invalidPrescriptionNumber() {
    const config = new MatSnackBarConfig();
    config.verticalPosition = "top";
    this.snackBar.open("Invalid prescription number!", "Close", config);
    this.clearInput();
  }

  public sendPrescriptionNumber() {
    this.patientService.validatePrescriptionNumber(this.prescriptionId).subscribe(
      (response) => {
        if (response) {
          this.prescriptionNumberEntered.emit(this.prescriptionId);
          this.navigateToPrescriptionTable(this.prescriptionId);
        } else {
          alert('Prescription does not exist');
        }
      },
      (error) => {
        console.log(error);
      }
    );

    this.clearInput();
  }

  private navigateToPrescriptionTable(prescriptionId: string) {
    const path = `/prescription-table/${prescriptionId}`;
    this.router.navigate([path]);
  }

  public checkPrescription() {
    this.prescriptionService.checkPrescriptionExistence(this.prescriptionId).subscribe(
      (exists: boolean) => {
        if (exists) {
          // Prescription exists, navigate to the prescription table component
          this.navigateToPrescriptionTable(this.prescriptionId);
        } else {
          this.invalidPrescriptionNumber();
        }
      },
      (error: any) => {
        console.error('Error checking prescription existence: ', error);
      }
    );
  }



}
