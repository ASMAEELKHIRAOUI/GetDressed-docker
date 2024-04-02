import { Component } from '@angular/core';
import { CCart, Cart } from '../../../modals/cart';
import { CartService } from '../../../services/cart/cart.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../../services/product/product.service';
import { CProduct, Product } from '../../../modals/product';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  carts: Cart[] = [];
  subtotal: number = 0
  toSave: Cart = new CCart()

  constructor(private cartService: CartService, private productService: ProductService) { }

  ngOnInit(): void {
    this.cartService.getCartsByUser().subscribe((data: Cart[]) => {
      this.carts = data;
      this.calculateSubtotal();
    })
  }

  deleteCart(id: number | undefined): void {
    if (id) {
      this.cartService.deleteCart(id)
        .subscribe({
          next: (res) => {
            console.log(res);
            window.location.reload();
          },
          error: (e) => console.error(e)
        });
    }
  }

  calculateTotal(cart: any): number {
    if (cart.product.price !== undefined && cart.product.promotion !== undefined && cart.quantity !== undefined) {
      return +((cart.product.price - (cart.product.promotion * (cart.product.price / 100))) * cart.quantity).toFixed(2);
    }
    return 0;
  }

  calculateSubtotal(): void {
    this.subtotal = 0;
    this.carts.forEach(cart => {
      if (cart.product !== undefined && cart.quantity !== undefined) {
        const product: Product = cart.product;
        const price = product.price !== undefined ? product.price : 0;
        const promotion = product.promotion !== undefined ? product.promotion : 0;
        this.subtotal += ((price - (promotion * price / 100)) * cart.quantity);
      }
    });
  }

  updateQuantity(id: number | undefined, quantity: number | undefined) {
    var newCart: Cart = new CCart()
    newCart.id = id
    newCart.quantity = quantity
    console.log(newCart);

    this.cartService.editCart(id, newCart).subscribe({
      next: data => {
        this.calculateSubtotal();
        // window.location.reload();
        // this.onSuccessSave(data?.data);
        // this.notificationService.show(['Product added successfully'], 'success');
        // this.router.navigate(['/dashboard/product']);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }

}
