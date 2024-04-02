import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  form: any = {
    email: null,
    password: null,
    firstName: null,
    lastName: null
  };

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    const { email, password, firstName, lastName } = this.form;

    this.authService.register(email, password, firstName, lastName).subscribe({
      next: (data: any) => {
        localStorage.setItem('token', data.token);
        localStorage.setItem('id', data.userId);
        localStorage.setItem('firstName', data.firstName);
        localStorage.setItem('lastName', data.lastName);
        localStorage.setItem('email', data.email);
        localStorage.setItem('role', data.role);
        localStorage.setItem('authorities', data.authorities);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

}
