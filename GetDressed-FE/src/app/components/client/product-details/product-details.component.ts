import { Component } from '@angular/core';
import { CProduct, Product } from '../../../modals/product';
import { ProductService } from '../../../services/product/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CartService } from '../../../services/cart/cart.service';
import { CCart, Cart } from '../../../modals/cart';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {

  productId: number = 0;
  product: Product = new CProduct();
  products: Product[] = []
  toSave: Cart = new CCart();

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id !== null) {
        this.productId = +id || 0;
        if (this.productId !== 0) {
          this.productService.getProduct(this.productId).subscribe((data: Product) => {
            if (data) {
              this.product = data;
              if (this.product.price !== undefined && this.product.promotion !== undefined) {
                this.product.price = +(this.product.price - (this.product.promotion * (this.product.price / 100))).toFixed(2)
              }
              this.productService.productsByCategory(this.product.category).subscribe((productsData: Product[]) => {
                this.products = productsData;
                this.products.forEach(product => {
                  if (product.price !== undefined && product.promotion !== undefined) {
                    product.price = +(product.price - (product.promotion * (product.price / 100))).toFixed(2)
                  }
                });
                this.products = this.products.filter(product => product.id !== this.productId);
              })
              console.log(this.product)
            } else {
              console.error("Product not found");
            }
          });
        }
      }
    });

  }

  productDetails(id: number | undefined): void{
    if(id){
      console.log('Product id:', id);
      this.router.navigate(['/product', id]);
      window.scrollTo(0, 0);
    }
  }

  onSubmit(productId: number | undefined) {
    this.toSave.product = productId
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
