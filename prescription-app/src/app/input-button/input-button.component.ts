import { PrescriptionService } from './../service/prescription/prescription.service';
import { Component, EventEmitter, Output } from '@angular/core';
import { PatientService } from '../service/patient/patient.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

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

  constructor(private prescriptionService: PrescriptionService, private patientService: PatientService, private router: Router) {}

  public clearInput() {
    this.prescriptionId = '';
  }

  public sendPrescriptionNumber() {
    this.patientService.validatePrescriptionNumber(this.prescriptionId).subscribe(
      (response) => {
        if (response) {
          this.prescriptionNumberEntered.emit(this.prescriptionId); // Emit the event
          const path = `/prescription-table/${this.prescriptionId}`;
          this.router.navigate([path]);
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


  checkPrescription() {
    this.prescriptionService.checkPrescriptionExistence(this.prescriptionId).subscribe(
      (exists: boolean) => {
        if (exists) {
          // Prescription exists, navigate to the prescription table component
          this.router.navigate(['/prescription-table', this.prescriptionId]);
        } else {
          // Prescription does not exist, handle accordingly (display error message, etc.)
          console.log('Prescription does not exist');
        }
      },
      (error: any) => {
        console.error('Error checking prescription existence: ', error);
      }
    );
  }

}
