import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestoresComponent } from './gestores/gestores.component';
import { LoginComponent } from './login/login.component';
import { VeiculosComponent } from './veiculos/veiculos.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'veiculos'},
  {path: 'veiculos', component: VeiculosComponent},
  {path: 'signup', component: GestoresComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
