import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { VeiculosService } from "../../veiculos.service";

@Component({
    selector: 'app-new-edit-cars',
    templateUrl: './new-edit-cars.component.html',
    styleUrls: ['./new-edit-cars.component.css']
  })
export class NewEditCarsComponent implements OnInit {
  
  formGroup: FormGroup = this.fb.group({
    id: [null],
    nome: [null, [Validators.required]],
    marca: [null, [Validators.required]],
    ano: [null, [Validators.required]],
    descricao: [null, [Validators.required]],
    vendido: [false, [Validators.required]]
  });
  
  isEdit: boolean = false;

  constructor(
    private fb: FormBuilder,
    private veiculosService: VeiculosService,
    public dialogRef: MatDialogRef<NewEditCarsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.isEdit = data.isEdit;
    if(this.isEdit){
      this.formGroup.controls.id.setValue(data.id);
    }
  }

  ngOnInit(): void {
    if(this.edit){
      this.edit();
    }
  }

  submit(){
    if(this.formGroup.valid){
      if(this.isEdit){
        this.veiculosService.update(this.formGroup.controls.id.value, this.formGroup.value).subscribe(()=>{this.close()})
      } else {
        this.veiculosService.create(this.formGroup.value).subscribe(()=>{this.close()})
      }
    }
  }

  close(){
    this.dialogRef.close();
  }

  edit(){
    this.veiculosService.findById(this.formGroup.controls.id.value).subscribe((data)=>{
      this.formGroup.patchValue(data);
    });
  }
}