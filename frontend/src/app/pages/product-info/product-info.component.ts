import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import Product from 'src/app/types/Product';
import { ActivatedRoute } from '@angular/router';
import { catchError, of } from 'rxjs';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.css'],
})
export class ProductInfoComponent implements OnInit {
  product: Product | null;
  productId: number;
  displaySuccess: boolean = false;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private notifier: NotificationService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.productId = params['id'];
    });

    this.productService
      .getProduct(this.productId)
      .pipe(
        catchError((err) => {
          return of(null);
        })
      )
      .subscribe((product) => (this.product = product));
  }
}
