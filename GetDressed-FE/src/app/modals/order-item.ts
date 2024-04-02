import { Product } from "./product";

export interface OrderItem {
    total?: number;
    quantity?: number;
    product?: Product
}
export class COrderItem implements OrderItem{
    constructor( 
        public total?: number,
        public quantity?: number,
        public product?: Product
    ){}
}