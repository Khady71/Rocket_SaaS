import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router,
    ) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {
        if (this.authService.isLoggedIn()) {
            if (route.data['role'] === 'ADMIN' && !this.authService.isAdmin()) {
                this.router.navigate(['unauthorized']);
                return false;
            }
            return true;
        }

        this.router.navigate(['/login'], {
            queryParams: {returnUrl: state.url},
        });

        return false;

    }
}

// Admin guard for admin-only routes
@Injectable({
    providedIn: 'root'
})
export class AdminGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router
    ) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean {
        if (this.authService.isLoggedIn() && this.authService.isAdmin()) {
            return true;
        }

        this.router.navigate(['/unauthorized']);
        return false;
    }
}
