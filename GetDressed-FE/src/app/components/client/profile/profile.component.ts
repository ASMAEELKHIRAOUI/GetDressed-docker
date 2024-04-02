import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../../../services/order/order.service';
import { Order } from '../../../modals/order';
import { CUser, User } from '../../../modals/user';
import { ProfileService } from '../../../services/profile/profile.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  orders: Order[] = [];
  toSave: User = new CUser()

  constructor(private orderService: OrderService, private userService: ProfileService, private router: Router) { }

  ngOnInit(): void {
    this.orderService.getUsersOrders().subscribe((data: Order[]) => {
      this.orders = data;
    })
    this.toSave.email = localStorage.getItem("email")!;
    this.toSave.firstName = localStorage.getItem("firstName")!;
    this.toSave.lastName = localStorage.getItem("lastName")!;
  }

  onSubmit() {
    this.userService.editUser(this.toSave).subscribe({
      next: data => {
        localStorage.setItem("firstName", this.toSave.firstName ? this.toSave.firstName : "")
        localStorage.setItem("lastName", this.toSave.lastName ? this.toSave.lastName : "")
        localStorage.setItem("email", this.toSave.email ? this.toSave.email : "")
        // this.onSuccessSave(data?.data);
        // this.notificationService.show(['User added successfully'], 'success');
        window.location.reload();
        // setTimeout(() => {
        //   this.router.navigate(['/']);
        // }, 50);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }
}
