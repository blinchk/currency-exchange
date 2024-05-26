import {Component, Input, OnInit} from '@angular/core';
import {CurrencyService} from "../../services/currency/currency.service";
import {CurrencyResponse} from "../../models/currency";
import {EChartsOption} from "echarts";

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss']
})
export class CurrencyComponent implements OnInit {
  @Input() activeCurrencyCode: string | null = null;
  currency: CurrencyResponse | null = null;
  eChartsOptions: EChartsOption = {};

  constructor(private readonly currencyService: CurrencyService) { }

  ngOnInit(): void {
    this.activeCurrencyCode && this.currencyService.getCurrency(this.activeCurrencyCode)
      .subscribe((response) => {
        this.currency = response;
      })

    this.eChartsOptions = {
      xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line'
        }
      ]
    };
  }

}
