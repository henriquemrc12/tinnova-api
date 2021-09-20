import { CommonModule } from "@angular/common";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { AuthService } from "./auth.service";

@NgModule({
    declarations: [],
    imports: [
      CommonModule,
      HttpClientModule
    ],
    providers: [
      AuthService,
      
    ]
  })
  export class AuthModule { }