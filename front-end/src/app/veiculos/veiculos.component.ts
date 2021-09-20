import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../auth/auth.service';
import { DeleteComponent } from './dialogs/delete/delete.component';
import { NewEditCarsComponent } from './dialogs/new-edit-cars/new-edit-cars.component';
import { VeiculosService } from './veiculos.service';

@Component({
  selector: 'app-veiculos',
  templateUrl: './veiculos.component.html',
  styleUrls: ['./veiculos.component.css']
})
export class VeiculosComponent implements OnInit {

  displayedColumns: string[] = [
    'nome', 
    'marca', 
    'ano', 
    'vendido',
    'editar',
    'deletar'];

  dataSource: any;

  constructor(
    public dialog: MatDialog,
    private veiculoService: VeiculosService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    if(!this.authService.isLogged()){
      window.location.href = '/login';
    }
    this.getAll();
  }

  new(){
    const dialogRef = this.dialog.open(NewEditCarsComponent, {
      width: '400px',
      data:{isEdit:false}
    });
    dialogRef.afterClosed().subscribe(result => {
      this.getAll();
    });
  }

  edit(id: number){
    const dialogRef = this.dialog.open(NewEditCarsComponent,{ 
      width: '400px',
      data: {isEdit: true, id: id}});
    dialogRef.afterClosed().subscribe(result => {
      this.getAll();
    });
  }

  delete(id: number){
    const dialogRef = this.dialog.open(DeleteComponent,{data:{isEdit: true, id: id}});
    dialogRef.afterClosed().subscribe(result => {
      if(result.result){
        this.veiculoService.delete(id).subscribe(()=> {this.getAll()});
      }
    });
  }

  toSale(id: number, isSale: boolean){
    this.veiculoService.toSale(id, isSale).then(()=> {this.getAll()});
  }

  getAll(){
    this.veiculoService.findAll().subscribe((data: any)=>{
      this.dataSource = data?.length == 0 ? null : new MatTableDataSource<any>(data);
    });
  }
}
