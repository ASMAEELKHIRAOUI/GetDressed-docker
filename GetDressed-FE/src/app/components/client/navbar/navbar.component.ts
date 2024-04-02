import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterLink, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  firstName: any = '';
  lastName: any = '';

  constructor(private router: Router){}

  ngOnInit(): void {
    this.firstName = localStorage.getItem('firstName');
    this.lastName = localStorage.getItem('lastName');
  }

  isAdmin(): boolean{
    return localStorage.getItem("role") == "admin"
  }

  isAuth(): boolean{
    const jwt = localStorage.getItem("token")
    if(jwt){
      return false;
    }
    return true;
  }

  logout(): void{
    localStorage.clear();
    this.router.navigate(["/"]);
  }
}
