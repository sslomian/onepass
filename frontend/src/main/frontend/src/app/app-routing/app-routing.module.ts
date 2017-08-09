import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../user/user.component';
import { RegisterComponent } from '../register/register.component';
import { AppComponent } from '../app.component';
import { UserPrivDataComponent } from '../user-priv-data/user-priv-data.component';
import {LoginComponent} from "../login/login.component";
import {LogoutComponent} from "../logout/logout.component";

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'home', component: AppComponent },
  { path: 'userPrivData', component: UserPrivDataComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
