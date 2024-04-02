export interface Product {
    id?: number;
    name?: string;
    description?: string;
    price?: number;
    stock?: number;
    promotion?: number;
    image?: number;
    category?: string
}
export class CProduct implements Product{
    constructor( 
        public price?: number,
        public promotion?: number,
        public id?: number,
        public name?: string,
        public description?: string,
        public stock?: number,
        public image?: number,
        public category?: string
    ){}
}
