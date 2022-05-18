import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

import { PO, POId, POTotal } from './inventory-model';

@Injectable()
export class InventoryService {

  constructor(private http: HttpClient) { }

  postPO(po: PO): Promise<POId> {
    return lastValueFrom(
      this.http.post<POId>('/api/order', po)
    );
  }

  getPOTotal(): Promise<POTotal[]> {
    return lastValueFrom(
      this.http.get<POTotal[]>('/api/order/values')
    );
  }

}
