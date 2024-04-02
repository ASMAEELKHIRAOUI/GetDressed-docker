export class ResponseModel<T> {
    status?: number;
    data?: T;
    message?: string;
}