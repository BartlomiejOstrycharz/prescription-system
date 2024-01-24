// prescription-table.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionService } from '../service/prescription/prescription.service';
import { CommonModule } from '@angular/common';
import { Prescription } from '../model/Prescription';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import * as QRCode from 'qrcode';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';



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
  async generatePDF() {
    const content = document.getElementById('print-content');
    const printButton = document.getElementById('print-button');

    if (printButton) {
      printButton.style.display = 'none';
    }


    const options = {
      scale: 4,
      logging: true,
      allowTaint: true,
      useCORS: true,
      scrollX: 0,
      scrollY: 0,
      windowWidth: document.documentElement.scrollWidth,
      windowHeight: document.documentElement.scrollHeight,
    };


    const pdf = new jsPDF({
      orientation: 'landscape',
      unit: 'mm',
      format: 'a4',
    });

    const canvas = await html2canvas(content!, options);
    const imgData = canvas.toDataURL('image/png');

    const imgWidth = 297;
    const imgHeight = (canvas.height * imgWidth) / canvas.width;

    pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);


    const qrCodeData = Math.random().toString(36).substring(2, 15);
    const qrCodeOptions = {
      margin: 2,
      width: 40,
      color: {
        dark: '#000000',
        light: '#ffffff',
      },
    };


    const qrCodeDataURL = await QRCode.toDataURL(qrCodeData, qrCodeOptions);

    pdf.addImage(qrCodeDataURL, 'PNG', imgWidth-50, imgHeight + 20, 40, 40);


    pdf.save('prescription.pdf');

    if (printButton) {
      printButton.style.display = '';
    }
  }
}
