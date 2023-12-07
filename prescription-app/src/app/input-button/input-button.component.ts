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

  constructor(private apiService: ApiService, private router: Router) {

  }

  public clearInput() {
    this.inputValue ='';
  }


}
