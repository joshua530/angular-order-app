import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { CategorizedProducts } from 'src/app/types/Product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  products: CategorizedProducts;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService
      .fetchProducts()
      .subscribe((prods) => (this.products = prods));
  }
}
