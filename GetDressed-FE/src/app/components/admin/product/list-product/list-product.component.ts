import { Component } from '@angular/core';
import { Product } from '../../../../modals/product';
import { ProductService } from '../../../../services/product/product.service';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './list-product.component.html',
  styleUrl: './list-product.component.css'
})
export class ListProductComponent {
  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe((data: Product[]) => {
      this.products = data;
    })
  }

  deleteProduct(id: number | undefined): void {
    if (id) {
      this.productService.deleteProduct(id)
        .subscribe({
          next: (res) => {
            console.log(res);
            window.location.reload();
          },
          error: (e) => console.error(e)
        });
    }
  }

  editProduct(id: number | undefined): void {
    if (id) {
      console.log('Product id:', id);
      this.router.navigate(['/dashboard/product/edit', id]);
    }
  }

}
