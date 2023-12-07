import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiURL = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  public validatePrescriptionNumber(
    prescriptionNumber: string
  ): Observable<any> {
    const url = `${this.apiURL}/validate-prescription`;
    const number = { prescriptionNumber };

    return this.httpClient.post(url, number);
  }
}
