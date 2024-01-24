import { PrescriptionService } from './../service/prescription/prescription.service';
import { Component, EventEmitter, Output } from '@angular/core';
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
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  public clearInput() {
    this.prescriptionId = '';
  }

  public invalidPrescriptionNumber() {
    const config = new MatSnackBarConfig();
    config.verticalPosition = "top";
    config.duration = 3000;
    this.snackBar.open("Invalid prescription number!", "Close", config);
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
