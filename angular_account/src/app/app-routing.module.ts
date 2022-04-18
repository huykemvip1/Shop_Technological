import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { ManageEmployeeComponent } from './component/manage-employee/manage-employee.component';
import { ManageUserComponent } from './component/manage-user/manage-user.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegistrySuccessComponent } from './component/registry-success/registry-success.component';
import { RegistryComponent } from './component/registry/registry.component';

const routes: Routes = [
  {path:"login",component: LoginComponent},
  {path:"registry",component: RegistryComponent},
  {path:"registry/success",component: RegistrySuccessComponent},
  {path:"manage_employee",component: ManageEmployeeComponent},
  {path:"manage_customer",component:ManageUserComponent},
  {path:"profile",component:ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
