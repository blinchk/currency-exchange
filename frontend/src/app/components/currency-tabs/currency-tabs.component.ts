import {Component, Input, OnInit} from '@angular/core';
import {CurrencyListItem} from "../../models/currency";
import {CurrencyService} from "../../services/currency/currency.service";

@Component({
  selector: 'app-currency-tabs',
  templateUrl: './currency-tabs.component.html',
  styleUrls: ['./currency-tabs.component.scss']
})
export class CurrencyTabsComponent implements OnInit {
  active = 'USD';
  currencies: CurrencyListItem[] = [];
  displayedCurrencies: string[] = [];
  @Input() searchTerm = '';

  constructor(private readonly currencyService: CurrencyService) { }

  ngOnInit(): void {
    this.getCurrencies(false);
  }

  onSearchTermChange(updatedValue: string) {
    this.searchTerm = updatedValue;
    this.getCurrencies(true);
  }

  getCurrencies(isUpdate: boolean) {
      this.currencyService.getCurrencies(this.searchTerm)
        .subscribe((response) => {
        if (!isUpdate) {
          this.currencies = response;
        }
        this.displayedCurrencies = response.map(currency => currency.code);
      })
  }
}
