// prescription-table.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { PrescriptionService } from '../service/prescription/prescription.service';
import { CommonModule } from '@angular/common';
import { Prescription } from '../model/Prescription';
import { Medication } from '../model/Medication';

@Component({
  selector: 'app-prescription-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prescription-table.component.html',
  styleUrls: ['./prescription-table.component.css'],
})
export class PrescriptionTableComponent implements OnInit {
  prescriptionId: any;
  prescription!: Prescription;

  constructor(
    private route: ActivatedRoute,
    private prescriptionService: PrescriptionService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.prescriptionId = params['prescriptionId'];
      this.prescriptionService.getPrescriptionById(this.prescriptionId).subscribe(
        (data: any) => {
          this.prescription = data;

          if (this.prescription && this.prescription.medication) {
            console.log(this.prescription);
          } else {
            console.error('Prescription or medication is undefined.');
          }
        },
        (error: any) => {
          console.error('Error fetching prescription details: ', error);
        }
      );
    });
  }



}
