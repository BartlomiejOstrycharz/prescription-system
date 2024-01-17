import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {
  private apiURL = 'http://localhost:8080/api/prescriptions';

  constructor(private http: HttpClient) { }

  checkPrescriptionExistence(prescriptionName: string): Observable<boolean> {
    const url = `${this.apiURL}/exists/${prescriptionName}`;
    return this.http.get<boolean>(url);
  }

  getPrescriptionsById(prescriptionName: string): Observable<any> {
    const url = `${this.apiURL}/${prescriptionName}`;
    return this.http.get<any>(url);
  }
}

