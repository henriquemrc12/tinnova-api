import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formGroup: FormGroup = this.fb.group({
    email: [null, [Validators.required]],
    senha: [null, [Validators.required]]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  submitLogin(): void {
    if(!this.formGroup.invalid){
      this.authService.login(this.formGroup.value).subscribe((data: any)=>{
        this.authService.setToken(data?.access_token);
        this.authService.setName(data?.name);
        window.location.href = '/veiculos'
      });
    }
  }

  redirectToSignup(): void{
    window.location.href='/signup';
  }
}
