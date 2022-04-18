import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { RegistryComponent } from './component/registry/registry.component';
import { RegistrySuccessComponent } from './component/registry-success/registry-success.component';
import { ManageEmployeeComponent } from './component/manage-employee/manage-employee.component';
import { ManageUserComponent } from './component/manage-user/manage-user.component';
import { ProfileComponent } from './component/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistryComponent,
    RegistrySuccessComponent,
    ManageEmployeeComponent,
    ManageUserComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
