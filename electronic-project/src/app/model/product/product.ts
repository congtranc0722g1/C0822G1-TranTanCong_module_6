import {Category} from "./category";
import {Trademark} from "./trademark";
import {Image} from "./image";

export interface Product {
  id?: number;
  code?: string;
  name?: string;
  createDay?: string;
  description?: string;
  price?: number;
  entryPrice?: number;
  quantity?: number;
  flagDelete?: boolean;
  category?: Category;
  trademark?: Trademark;
  images?: Image[];
}
