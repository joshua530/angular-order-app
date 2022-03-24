import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order } from 'src/app/types/Order';
import { UserService } from 'src/app/services/user.service';
import { OrderStatus } from '../../types/Order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  orders: Order[];
  recommendedOrders: Order[];
  ORDER_STATUS = OrderStatus;

  constructor(
    private orderService: OrderService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    let userId = this.userService.getUserId();
    this.orderService.fetchOrders(userId).subscribe((orders) => {
      if (orders) this.orders = orders;
    });
  }
}
