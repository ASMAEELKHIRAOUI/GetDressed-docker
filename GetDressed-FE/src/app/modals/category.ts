export interface Category {
    id?: number;
    name?: string;
}
export class CCategory implements Category {
    constructor(
        public name?: string
    ) { }
}