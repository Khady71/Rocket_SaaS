import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './components/register/register.component';
import {HomeAdmin} from './admin/home-admin/home-admin';
import {DashboardAdmin} from './admin/dashboard-admin/dashboard-admin';
import {AuthGuard} from './services/auth.guard';
import {NgModule} from '@angular/core';
import {LoginComponent} from './components/login/login';

export const routes: Routes = [
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: '', component: HomeAdmin},
    {
        path: 'admin',
        component: DashboardAdmin,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN'},
    },
    { path: '**', redirectTo: '' }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
