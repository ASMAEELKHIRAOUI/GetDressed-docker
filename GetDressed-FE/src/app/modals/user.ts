export interface User {
    firstName?: string;
    lastName?: string;
    email?: string
}
export class CUser implements User{
    constructor( 
        public firstName?: string,
        public lastName?: string,
        public email?: string
    ){}
}