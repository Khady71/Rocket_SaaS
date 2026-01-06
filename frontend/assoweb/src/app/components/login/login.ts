import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.html',
    styleUrls: ['./login.css']
})
export class LoginComponent {
    credentials: any = { username: '', password: '' };

    constructor(private authService: AuthService, private router: Router) { }

    login() {
        this.authService.login(this.credentials).subscribe((response) => {
            localStorage.setItem('token', response);
            this.router.navigate(['/home']);
        }, error => {
            console.error('Login error: ', error);
        });
    }
}
