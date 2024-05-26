import {Component, Input, OnInit} from '@angular/core';
import {CurrencyService} from "../../services/currency/currency.service";
import {CurrencyResponse} from "../../models/currency";
import {EChartsOption} from "echarts";
import {ExchangeRateService} from "../../services/exchange-rate/exchange-rate.service";
import {ExchangeRateSummary} from "../../models/exchange-rate";

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss']
})
export class CurrencyComponent implements OnInit {
  @Input() activeCurrencyCode: string | null = null;
  currency: CurrencyResponse | null = null;
  exchangeRates: ExchangeRateSummary | null = null;
  isLoading: boolean = false;
  eChartsOptions: EChartsOption = {};
  @Input() amount: number = 0;
  convertedAmount: number = 0;

  constructor(private readonly currencyService: CurrencyService,
              private readonly exchangeRateService: ExchangeRateService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.activeCurrencyCode && this.currencyService.getCurrency(this.activeCurrencyCode)
      .subscribe((response) => {
        this.currency = response;
      })

    this.activeCurrencyCode && this.exchangeRateService.getExchangeRates(this.activeCurrencyCode)
      .subscribe((response) => {
        this.exchangeRates = response;

        const dates = this.exchangeRates.rates.map(rate => rate.date);
        const amounts = this.exchangeRates.rates.map(rate => rate.amount);
        this.eChartsOptions = {
          ...this.eChartsOptions,
          xAxis: {
            type: "category",
            data: dates,
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: amounts,
            type: "line",
            smooth: true
          }]
        }
        this.isLoading = false;
      })
  }

  onAmountChange(newAmount: number) {
   if (isNaN(newAmount)) {
     return;
   } else if (newAmount === 0 || newAmount == null) {
     this.convertedAmount = 0;
   }
   if (this.exchangeRates && this.currency) {
     this.exchangeRateService.convertCurrency(this.currency.code, newAmount)
       .subscribe((response) => this.convertedAmount = response)
   }
  }
}
