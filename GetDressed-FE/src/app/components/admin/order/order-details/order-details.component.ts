import { Component } from '@angular/core';
import { COrder, Order } from '../../../../modals/order';
import { OrderService } from '../../../../services/order/order.service';
import { ActivatedRoute } from '@angular/router';
import { OrderItem } from '../../../../modals/order-item';
import { OrderItemService } from '../../../../services/order-item/order-item.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent {

  orderId: number = 0;
  order: Order = new COrder();

  constructor(private orderService: OrderService, private route: ActivatedRoute, private orderItemService: OrderItemService) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id !== null) {
        this.orderId = +id || 0;
        if (this.orderId !== 0) {
          this.orderService.getOrder(this.orderId).subscribe((data: Order) => {
            if (data) {
              this.order = data;
              this.orderItemService.getOrderItems(this.orderId).subscribe((orderitems: OrderItem[]) => {
                this.order.orderItems = orderitems;
              })
              console.log(this.order)
            } else {
              console.error("Order not found");
            }
          });
        }
      }
    });
  }

}
