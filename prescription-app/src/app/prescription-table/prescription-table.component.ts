import { Component } from '@angular/core';
import { NavigationBarComponent } from '../navigation-bar/navigation-bar.component';


@Component({
  selector: 'app-prescription-table',
  standalone: true,
  imports: [NavigationBarComponent],
  templateUrl: './prescription-table.component.html',
  styleUrl: './prescription-table.component.css'
})
export class PrescriptionTableComponent {

}
