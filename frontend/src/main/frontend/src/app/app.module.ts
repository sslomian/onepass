import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdInputModule } from '@angular/material';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { UserService } from './user.service';
import { UserInfoComponent } from './user-info/user-info.component';
import { AppRoutingModule } from './app-routing/app-routing.module'
import { MdListModule } from '@angular/material';
import { MdButtonModule } from '@angular/material';
import { MdCardModule } from '@angular/material';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { UserPrivDataComponent, UserPrivDataDialog, UserPrivDataDialogUpdate } from './user-priv-data/user-priv-data.component';
import { MdDialogModule } from '@angular/material';
import { UserPrivDataService } from "./user-priv-data.service";
import { MdGridListModule } from '@angular/material';
import { CryptoService } from "./crypto.service";
import { UserData } from "./user-data";
import { Ng2Webstorage } from 'ng2-webstorage';
import { LogoutComponent } from './logout/logout.component';
import { MdIconModule } from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserInfoComponent,
    RegisterComponent,
    LoginComponent,
    UserPrivDataComponent,
    UserPrivDataDialog,
    UserPrivDataDialogUpdate,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    MdInputModule,
    AppRoutingModule,
    MdListModule,
    MdButtonModule,
    MdCardModule,
    ReactiveFormsModule,
    MdDialogModule,
    MdGridListModule,
    Ng2Webstorage,
    MdIconModule
  ],
  entryComponents: [
    UserPrivDataDialog,
    UserPrivDataDialogUpdate
  ],
  providers: [
    UserService,
    UserPrivDataService,
    UserPrivDataComponent,
    CryptoService,
    UserData
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
