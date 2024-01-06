// prescription-table.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PrescriptionService } from '../service/prescription/prescription.service';
import { CommonModule } from '@angular/common';
import { Prescription } from '../model/Prescription';

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
    private prescriptionService: PrescriptionService
  ) {
    this.prescription = undefined; // Initialize with undefined in the constructor
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
}
