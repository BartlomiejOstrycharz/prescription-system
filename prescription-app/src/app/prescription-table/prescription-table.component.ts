// prescription-table.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionService } from '../service/prescription/prescription.service';
import { CommonModule } from '@angular/common';
import { Prescription } from '../model/Prescription';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-prescription-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prescription-table.component.html',
  styleUrls: ['./prescription-table.component.css'],
})
export class PrescriptionTableComponent implements OnInit {
  prescriptionId: any;
  prescription: Prescription[] | undefined; // Change the type to Prescription[] | undefined

  constructor(
    private route: ActivatedRoute,
    private prescriptionService: PrescriptionService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.prescription = undefined;
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.prescriptionId = params['prescriptionId'];
      this.prescriptionService.getPrescriptionsById(this.prescriptionId).subscribe(
        (data: Prescription[]) => {
          this.prescription = data;
          console.log(this.prescription);
        },
        (error: any) => {
          console.error('Error fetching prescription details: ', error);
        }
      );
    });
  }

  public deletePrescription(prescriptionId: number){
    console.log('Deleting prescription with ID:', prescriptionId);

      this.prescriptionService.deletePrescriptionById(prescriptionId).subscribe(
        (succes) => {
            this.deletedAlertSuccess();
            if (!this.prescription || this.prescription.length === 1) {
              this.router.navigate(['/validate']);
            }
            else {
              this.refreshPrescriptionData();
            }

        },
        (error) => {
            this.deletedAlertError();
        }
      )
  }

  public deletedAlertSuccess(){
    const config = new MatSnackBarConfig();
    config.verticalPosition = "top";
    config.duration = 3000;
    this.snackBar.open("Medication deleted successfully", "Close", config);
  }

  public deletedAlertError(){
    const config = new MatSnackBarConfig();
    config.verticalPosition = "top";
    config.duration = 3000;
    this.snackBar.open("Somethink went wrong", "Close", config);
  }

  private refreshPrescriptionData() {
    // Refetch the updated prescription data after deletion
    this.prescriptionService.getPrescriptionsById(this.prescriptionId).subscribe(
      (data: Prescription[]) => {
        this.prescription = data;
        console.log(this.prescription);
      },
      (error: any) => {
        console.error('Error fetching prescription details: ', error);
      }
    );
  }
}
