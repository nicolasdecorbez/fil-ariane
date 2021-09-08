import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService, LoginInterface } from './services/auth.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'api-dashboard';

  get authStatus() { return this.authService.isAuth }
  
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver, 
    private authService: AuthService, 
    private router: Router,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(){
    
  }

  onSignOut() {
    this.authService.signOut();
    this.router.navigate([""]);
    this.openSnackBar();
  }

  toggleAuth(): void {
    const dialogRef = this.dialog.open(AuthDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe( () => {
      console.log('The dialog was closed');
    });
  }

  openSnackBar() {
    this._snackBar.open("Successfully disconnected !", "Ok !", {
      horizontalPosition: "left",
      verticalPosition: "bottom",
      duration: 5000
    });
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './auth/auth.component.html',
  styleUrls: ['./auth/auth.component.css']
})
export class AuthDialogComponent {

  constructor (
    private authService: AuthService,
    private router: Router,
    public dialogRef: MatDialogRef<AuthDialogComponent>,
    private _snackBar: MatSnackBar
  ) { }

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  get email()     { return this.loginForm.get("email")! }
  get password()  { return this.loginForm.get("password")! }

  getNameErrorMessage(): string {
    return "You must enter a value";
  }

  getEmailErrorMessage(): string {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  onSignIn() {
    try {
      const data: LoginInterface = this.loginForm.value
      if (data.email && data.password) {
        this.authService.signIn().then(() => {
          this.router.navigate([""]);
          this.openSnackBar();
        });
      }
    } 
    
    catch (error) {
      this.dialogRef.close();
    } 
    
    finally {
      this.dialogRef.close();
    }
    
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  openSnackBar() {
    this._snackBar.open("Successfully connected !", "Ok !", {
      horizontalPosition: "left",
      verticalPosition: "bottom",
      duration: 5000
    });
  }

}
