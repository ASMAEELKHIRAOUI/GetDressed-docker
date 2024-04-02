import { OrderItem } from "./order-item";

export interface Order {
    id?: number;
    fullName?: string;
    email?: string;
    phone?: string;
    zipcode?: string;
    address?: string;
    status?: string;
    orderItems?: OrderItem[];
}

export class COrder implements Order{
    constructor( 
        public id?: number,
        public fullName?: string,
        public email?: string,
        public phone?: string,
        public zipcode?: string,
        public address?: string,
        public status?: string,
        public orderItems?: OrderItem[]
    ){}
}