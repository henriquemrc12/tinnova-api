import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { GestoresService } from './gestores.service';

@Component({
  selector: 'app-gestores',
  templateUrl: './gestores.component.html',
  styleUrls: ['./gestores.component.css']
})
export class GestoresComponent implements OnInit {
  
  formGroup: FormGroup = this.fb.group({
    nome: [null, [Validators.required]],
    email: [null, [Validators.required]],
    senha: [null, [Validators.required]]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private service: GestoresService
  ) { }

  ngOnInit(): void {
  }

  submitSignup(): void{
    if(!this.formGroup.invalid){
      this.service.create(this.formGroup.value).subscribe(()=> {window.location.href = '/login'})
    }
  }
  redirectToLogin(): void{
    window.location.href='/login';
  }
}
