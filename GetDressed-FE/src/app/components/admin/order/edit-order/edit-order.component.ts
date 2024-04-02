import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { COrder, Order } from '../../../../modals/order';
import { OrderService } from '../../../../services/order/order.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-order',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-order.component.html',
  styleUrl: './edit-order.component.css'
})
export class EditOrderComponent {

  orderId: number = 0;
  toSave: Order = new COrder();

  constructor(private orderService: OrderService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id !== null) {
        this.orderId = +id || 0;
        if (this.orderId !== 0) {
          this.orderService.getOrder(this.orderId).subscribe((data: Order) => {
            if (data) {
              this.toSave = data;
            } else {
              console.error("Order not found");
            }
          });
        }
      }
    });

  }

  onSubmit() {
    this.orderService.editOrder(this.orderId, this.toSave).subscribe({
      next: data => {
        // this.notificationService.show(['Order added successfully'], 'success');
        this.router.navigate(['/dashboard/order']);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }

}
