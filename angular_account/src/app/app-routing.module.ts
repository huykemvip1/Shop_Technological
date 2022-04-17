import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { RegistrySuccessComponent } from './component/registry-success/registry-success.component';
import { RegistryComponent } from './component/registry/registry.component';

const routes: Routes = [
  {path:"login",component: LoginComponent},
  {path:"registry",component: RegistryComponent},
  {path:"registry/success",component: RegistrySuccessComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
