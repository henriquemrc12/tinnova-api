import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front-end';
  isLogged: boolean = false;

  constructor(
    private authService: AuthService
  ){}

  ngOnInit(): void{
    this.isLogged = this.getLogged();
  }

  getLogged(): any{
    return this.authService.isLogged();
  }

  logout(){
    this.authService.logout();
  }

  getUserLoggedName(){
    return this.authService.getName();
  }
}
