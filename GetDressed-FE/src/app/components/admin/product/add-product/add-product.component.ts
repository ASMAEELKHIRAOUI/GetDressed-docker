import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CProduct, Product } from '../../../../modals/product';
import { ProductService } from '../../../../services/product/product.service';
import { Category } from '../../../../modals/category';
import { CategoryService } from '../../../../services/category/category.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {

  products: Product[] = [];
  categories: Category[] = [];
  toSave: Product = new CProduct();

  constructor(private productService: ProductService, private router: Router, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
    })
  }

  onSubmit() {
    this.productService.addProduct(this.toSave).subscribe({
      next: data => {
        this.onSuccessSave(data?.data);
        // this.notificationService.show(['Product added successfully'], 'success');
        this.router.navigate(['/dashboard/product']);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }

  onSuccessSave(product?: Product) {
    if (product) {
      this.products.push(product);
    }
  }

}
