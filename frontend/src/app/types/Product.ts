interface Product {
  name: string;
  price: number;
  description: string;
  id: number;
  imageUrl: string;
  nutritionInfo: string;
}

export interface CategorizedProducts {
  featured: Product[];
  hotDeals: Product[];
  others: Product[];
}

export default Product;
