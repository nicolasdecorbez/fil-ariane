import { Component, OnInit } from '@angular/core';
import { DatabaseService, UsersInterface } from '../services/database.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { throwError } from 'rxjs';


@Component({
  selector: 'app-database',
  templateUrl: './database.component.html',
  styleUrls: ['./database.component.css']
})
export class DatabaseComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
    private db_service: DatabaseService
  ) { }
  
  displayedColumns: string[] = ["firstName", "lastName", "email", "phone"];
  get users() { return this.db_service.users }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.db_service.getAllUsers();
  }

  toggleForm(): void {
    const dialogRef = this.dialog.open(DatabaseDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe( () => {
      console.log('The dialog was closed');
    });
  }
}


@Component({
  selector: 'app-database-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./database.component.css']
})
export class DatabaseDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<DatabaseDialogComponent>,
    private db_service: DatabaseService
  ) {}

  userForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required, Validators.maxLength(10)]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  })

  get email()     { return this.userForm.get("email")! }
  get phone()     { return this.userForm.get("phone")! }
  get password()  { return this.userForm.get("password")! }
  get lastName()  { return this.userForm.get("lastName")! }
  get firstName() { return this.userForm.get("firstName")! }

  getNameErrorMessage(): string {
    return "You must enter a value";
  }

  getPhoneErrorMessage(): string {
    if (this.phone.hasError('required')) {
      return 'You must enter a value';
    }
    return this.phone.hasError('maxlength') ? 'Phone numbers are 10 digits lenght' : '';
  }

  getPasswordErrorMessage(): string {
    if (this.password.hasError('required')) {
      return 'You must enter a value';
    }
    return this.password.hasError('minlength') ? 'Password must be longer than 8 characters' : '';
  }
  
  getEmailErrorMessage(): string {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  refresh(): void {
    this.db_service.getAllUsers();
  }

  onSubmit(): void {
    try {
      const data: UsersInterface = this.userForm.value
      if (data.firstName && data.lastName && data.password && data.email && data.phone) {
        this.db_service.saveUser(this.userForm.value).subscribe(user => {
          this.db_service.users.push(user)
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
}


