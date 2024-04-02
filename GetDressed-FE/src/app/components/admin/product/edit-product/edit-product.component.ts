import { Component } from '@angular/core';
import { ProductService } from '../../../../services/product/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CProduct, Product } from '../../../../modals/product';
import { CategoryService } from '../../../../services/category/category.service';
import { Category } from '../../../../modals/category';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent {

  productId: number = 0;
  categories: Category[] = []
  toSave: Product = new CProduct();

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id !== null) {
        this.productId = +id || 0;
        if (this.productId !== 0) {
          this.productService.getProduct(this.productId).subscribe((data: Product) => {
            if (data) {
              this.toSave = data;
            } else {
              console.error("Product not found");
            }
          });
        }
      }
    });

    this.categoryService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
    })

    // this.productService.getProduct(this.productId).subscribe((data: Product) => {
    //   this.productToEdit = data;
    // });

  }

  onSubmit() {
    this.productService.editProduct(this.productId, this.toSave).subscribe({
      next: data => {
        // this.onSuccessSave(data?.data);
        // this.notificationService.show(['Product added successfully'], 'success');
        this.router.navigate(['/dashboard/product']);
      },
      error: (err) => {
        console.log(err.error.date[0])
        // this.notificationService.show([err.error.date[0]], 'error');
      }
    })
  }

  // onSuccessSave(product?: Product){
  //   if(product){
  //     this.products.push(product);
  //   }
  // }

}
