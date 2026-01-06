import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service'


@Component({
  selector: 'app-register',
  template: `./register.html`,
})
export class RegisterComponent {
  user:any = {email: '', password: ''};

  constructor(private authService: AuthService, private router: Router) {
  }

  register(){
    this.authService.register(this.user).subscribe(() =>{
      this.router.navigate(['/login']);
    },error =>{
      console.log('Registration error : ', error);
    });
  }
}
