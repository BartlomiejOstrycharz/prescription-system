import { ApiService } from './../service/api.service';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-input-button',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './input-button.component.html',
  styleUrl: './input-button.component.css',
})
export class InputButtonComponent {
  inputValue: string = '';

  constructor(private apiService: ApiService, private router: Router) {}

  public clearInput() {
    this.inputValue = '';
  }

  public sendPrescriptionNumber() {
    this.apiService.validatePrescriptionNumber(this.inputValue).subscribe(
      (response) => {
        if (response.status === 200) {
          const path = `/prescription${this.inputValue}`;
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
}
