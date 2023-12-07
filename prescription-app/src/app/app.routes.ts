
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InputButtonComponent } from './input-button/input-button.component';

export const routes: Routes = [
  { path: 'prescription', component: InputButtonComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
