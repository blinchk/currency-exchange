import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ExchangeRateSummary} from "../../models/exchange-rate";

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {

  constructor(private readonly http: HttpClient) { }

  getExchangeRates(code: string) {
    return this.http.get<ExchangeRateSummary>(`exchange-rate/${code}`);
  }

  convertCurrency(code: string, amount: number) {
    return this.http.post<number>('exchange-rate/convert', {
      code,
      amount
    });
  }
}
