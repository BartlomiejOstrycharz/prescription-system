import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {
  private apiURL = 'http://localhost:8080/api/prescriptions';

  constructor(private http: HttpClient) { }

  public checkPrescriptionExistence(prescriptionName: string): Observable<boolean> {
    const url = `${this.apiURL}/exists/${prescriptionName}`;
    return this.http.get<boolean>(url);
  }

  public getPrescriptionsById(prescriptionName: string): Observable<any> {
    const url = `${this.apiURL}/${prescriptionName}`;
    return this.http.get<any>(url);
  }

  public deletePrescriptionById(prescriptionId: number): Observable<void>{
    const url = `${this.apiURL}/delete/${prescriptionId}`;
    return this.http.delete<void>(url);
  }
}

