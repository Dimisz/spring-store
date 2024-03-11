export interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    pictureUrl: string;
    category?: string;
    brand: string;
    quantityInStock?: number;
}

export interface ProductParams {
    orderBy: string;
    searchTerm?: string;
    categories: string[];
    brands: string[];
    pageNumber: number;
    pageSize: number;
}