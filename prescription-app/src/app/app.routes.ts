import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InputButtonComponent } from './input-button/input-button.component';
import { PrescriptionTableComponent } from './prescription-table/prescription-table.component';

export const routes: Routes = [
  { path: 'prescription-table/:prescriptionId', component: PrescriptionTableComponent },
  { path: 'validate', component: InputButtonComponent },
  { path: '', redirectTo: '/validate', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
