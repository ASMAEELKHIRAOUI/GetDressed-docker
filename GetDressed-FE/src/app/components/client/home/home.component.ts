import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Product } from '../../../modals/product';
import { ProductService } from '../../../services/product/product.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CCart, Cart } from '../../../modals/cart';
import { CartService } from '../../../services/cart/cart.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  products: Product[] = [];
  toSave: Cart = new CCart();
  productQuantities: { [productId: number]: number } = {};
  searchTerm: string = '';

  constructor(private productService: ProductService, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe((data: Product[]) => {
      this.products = data;
      this.products.forEach(product => {
        if (product.id !== undefined) {
          this.productQuantities[product.id] = 1;
        }
        if (product.price !== undefined && product.promotion !== undefined) {
          product.price = +(product.price - (product.promotion * (product.price / 100))).toFixed(2)
        }
      });
    })
  }

  productDetails(id: number | undefined): void {
    if (id) {
      console.log('Product id:', id);
      this.router.navigate(['/product', id]);
    }
  }

  onSubmit(productId: number | undefined) {
    const jwt = localStorage.getItem("token")
    if (!jwt) {
      this.router.navigate(["/auth/login"])
    } else {
      this.toSave.product = productId
      this.toSave.quantity = this.productQuantities[productId ? productId : 0]
      console.log(this.toSave.product)
      console.log(this.toSave.quantity)
      this.cartService.addCart(this.toSave).subscribe({
        next: data => {
          // this.onSuccessSave(data?.data);
          console.log("Success")
          // this.notificationService.show(['Cart added successfully'], 'success');
          // this.router.navigate(['/dashboard/cart']);
        },
        error: (err) => {
          console.log(err.error.date[0])
          // this.notificationService.show([err.error.date[0]], 'error');
        }
      })
    }
  }

  search(searchTerm: string) {
    if (searchTerm.trim() === '') {
      // If the search string is empty, fetch all products
      this.productService.getProducts().subscribe((data: Product[]) => {
        this.products = data;
        this.updateProductQuantities();
      });
    } else {
      // If there's a search string, filter products based on it
      this.productService.search(searchTerm).subscribe((data: Product[]) => {
        this.products = data;
        this.updateProductQuantities();
      });
    }
  }

  updateProductQuantities() {
    this.productQuantities = {};
    this.products.forEach(product => {
      if (product.id !== undefined) {
        this.productQuantities[product.id] = 1;
      }
      if (product.price !== undefined && product.promotion !== undefined) {
        product.price = +(product.price - (product.promotion * (product.price / 100))).toFixed(2);
      }
    });
  }

}