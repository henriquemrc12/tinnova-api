import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VeiculosComponent } from './veiculos.component';
import { VeiculosService } from './veiculos.service';
import { AuthModule } from '../auth/auth.module';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatTableModule} from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { DeleteComponent } from './dialogs/delete/delete.component';
import { NewEditCarsComponent } from './dialogs/new-edit-cars/new-edit-cars.component';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    VeiculosComponent,
    NewEditCarsComponent,
    DeleteComponent
  ],
  imports: [
    CommonModule,
    AuthModule,
    MatSlideToggleModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [
    VeiculosService
  ]
})
export class VeiculosModule { }
