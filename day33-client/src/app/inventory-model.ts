export interface LineItem {
    itemId?: number,
    prodName: string,
    quantity: number,
    unitPrice: number,
}

export interface PO extends LineItem {
    ordId?: number,
    name: string,
    email: string
}

export interface POId{
    ordId?: number
}

export interface POTotal {
    ordId?: number,
    name: string,
    total: number
}