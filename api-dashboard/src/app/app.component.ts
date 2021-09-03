import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  authStatus!: boolean;
  title = 'api-dashboard';
  
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver, 
    private authService: AuthService, 
    private router: Router
  ) {}

  ngOnInit(){
    this.authStatus = this.authService.isAuth;
  }

  onSignIn() {
    this.authService.signIn().then(() => {
      this.authStatus = this.authService.isAuth;
      this.router.navigate(['']);
    });
  }

  onSignOut() {
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
    this.router.navigate([""]);
  }
}
