import Product from './Product';

export enum OrderStatus {
  PENDING = 'PENDING',
  PROCESSING = 'PROCESSING',
  DELIVERED = 'DELIVERED',
}

export interface Order {
  productQuantity: number;
  status: OrderStatus;
  productIdNum: number;
  userIdStr: string;
  timePlaced?: string;
  product?: Product;
  id?: number;
}

export interface PersonOrders {
  id: string; // uuid token for owner
  orders: Order[];
}
