import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order, OrderStatus, PersonOrders } from 'src/app/types/Order';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css'],
})
export class AdminHomeComponent implements OnInit {
  orders: Order[] = [];
  ORDER_STATUS = OrderStatus;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.orderService
      .fetchAllOrders()
      .subscribe((orders) => (this.orders = orders));
  }

  processOrder(orderId: number, status: OrderStatus) {
    this.orderService
      .processOrder(orderId, status)
      .subscribe((processedOrder) => {
        if (processedOrder != null) {
          // loop through the orders and update the order whose update
          // was requested
          this.orders.map((order) => {
            if (processedOrder.id === order.id)
              order.status = processedOrder.status;
            return order;
          });
        }
      });
  }
}
