import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../../model/Patient';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiURL = 'http://localhost:8080/api/prescriptions/exists/';

  constructor(private httpClient: HttpClient) {}

  public validatePrescriptionNumber(
    prescriptionNumber: string
  ): Observable<boolean> {
    return this.httpClient.get<boolean>(
      `${this.apiURL}${prescriptionNumber}`
    );
  }

  getPatientByPrescriptionId(prescriptionName: string): Observable<Patient> {
    return this.httpClient.get<Patient>(
      `${this.apiURL}/patients/byPrescription/${prescriptionName}`
    );
  }
}
