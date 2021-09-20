import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GestoresComponent } from './gestores.component';
import { GestoresService } from './gestores.service';
import { AuthModule } from '../auth/auth.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    GestoresComponent
  ],
  imports: [
    CommonModule,
    AuthModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  providers: [
    GestoresService
  ]
})
export class GestoresModule { }
