import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';
import { Order, OrderStatus, PersonOrders } from 'src/app/types/Order';

@Component({
  selector: 'app-place-order-form',
  templateUrl: './place-order-form.component.html',
  styleUrls: ['./place-order-form.component.css'],
})
export class PlaceOrderFormComponent implements OnInit {
  @Input() productId: number;
  @Output() onOrderSubmitted: EventEmitter<any> = new EventEmitter();

  quantity: number = 1;
  time: string;

  constructor(
    private userService: UserService,
    private orderService: OrderService,
    private notifier: NotificationService
  ) {}

  ngOnInit(): void {}

  orderMeal(): void {
    // validate
    if (this.quantity > 1000) {
      this.notifier.notify(
        'Product quantity should be less than 1000',
        'danger'
      );
      return;
    }
    if (this.quantity < 1) {
      this.notifier.notify(
        'Product quantity must be greater than zero',
        'danger'
      );
      return;
    }
    if (!this.productId) {
      this.notifier.notify(
        'Invalid product id, please refresh page and try again',
        'danger'
      );
      return;
    }

    const order: Order = {
      productIdNum: this.productId,
      productQuantity: this.quantity,
      status: OrderStatus.PENDING,
      userIdStr: this.userService.getUserId(),
    };

    // save order and notify user
    this.orderService.saveOrder(order);
  }
}
