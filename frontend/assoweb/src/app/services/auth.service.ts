import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, catchError, Observable, tap, throwError} from 'rxjs';
import {environment} from '../../environments/environment';
import {AuthResponse, LoginRequest, RegisterRequest, User, UserRole} from './auth.models';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly baseUrl = `${environment.apiUrl}/auth`;
  private currentUser$: Observable<User | null> ;
  private currentUserSubject: BehaviorSubject<User | null>;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
    const storedUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<User | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  public get token():string | null {
    return localStorage.getItem('token');
  }

  register(registerRequest:RegisterRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.baseUrl}/admin/signup`, registerRequest)
      .pipe(
        tap(response =>{
          this.storeAuthData(response);
          this.currentUserSubject.next(response.user);
        })
      );
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.baseUrl}/admin/signin`, loginRequest)
      .pipe(
        tap(response => this.handleAuthSuccess(response)),
        catchError(error => this.handleAuthError(error))
      )
  }

  logout():void{
    this.http.post<AuthResponse>(`${this.baseUrl}/admin/signout`, {}).subscribe();

    this.clearAuthdata();

    this.currentUserSubject.next(null);

    this.router.navigate(['/login']);
  }

  refreshToken():Observable<AuthResponse>{
    const refreshToken = localStorage.getItem('refreshToken');
    return this.http.post<AuthResponse>(`${this.baseUrl}/admin/refresh`, {refreshToken})
    .pipe(
      tap(response => {
        this.storeAuthData(response);
      })
    )

  }
  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  isLoggedIn(): boolean{
    const token = this.token;

    if(!token) return false;

    const tokenExp = this.getTokenExpiration(token);
    return tokenExp > new Date();
  }

  isAdmin(): boolean{
    const user  = this.currentUserValue;
    return user?.role === UserRole.ADMIN;
  }

  private storeAuthData(response:AuthResponse){
    localStorage.setItem('accessToken', response.token);
    localStorage.setItem('refreshToken', response.refreshToken);
    localStorage.setItem('currentUser', JSON.stringify(response.user));
  }

  private clearAuthdata():void{
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('currentUser');
  }

  private getTokenExpiration(token: string ): Date {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));

      return new Date(payload.exp * 1000);
    } catch (error) {
      return new Date(0);
    }
  }

  private handleAuthSuccess(response: AuthResponse): void {
    this.storeAuthData(response);
    this.currentUserSubject.next(response.user);
  }


  private handleAuthError(error: any):Observable<any>{
    this.clearAuthdata();
    this.currentUserSubject.next(null);
    return throwError(() => this.getErrorMessage(error));

  }

  private getErrorMessage(error : any): string{
    switch (error.code) {
      case 401: return 'Email ou mot de passe incorrect';
      case 403: return 'Accès non autorisé';
      case 0: return 'Impossible de se connecter au serveur';
      default: return 'Une erreur est subvenue '
    }

  }

}
