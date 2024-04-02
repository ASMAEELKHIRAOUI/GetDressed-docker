export interface Cart {
    id?: number;
    product?: any;
    quantity?: number
}
export class CCart implements Cart {
    constructor(
        public id?: number,
        public product?: any,
        public quantity?: number
    ) { }
}