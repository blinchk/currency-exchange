import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CurrencyListItem, CurrencyResponse} from "../../models/currency";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private readonly http: HttpClient) {

  }

  getCurrencies(searchTerm: string) {
    return this.http.get<CurrencyListItem[]>('currency', {
      params: {
        searchTerm: searchTerm ?? null
      }
    });
  }

  getCurrency(code: string) {
    return this.http.get<CurrencyResponse>(`currency/${code}`);
  }
}
