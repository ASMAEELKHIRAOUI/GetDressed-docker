import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { COrder, Order } from '../../../modals/order';
import { OrderService } from '../../../services/order/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent {

  toSave: Order = new COrder();

  constructor(private orderService: OrderService, private router: Router) { }

  onSubmit() {
    this.orderService.addOrder(this.toSave).subscribe({
      next: data => {
        // this.notificationService.show(['Order added successfully'], 'success');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }

}
