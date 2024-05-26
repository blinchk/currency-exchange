export interface ExchangeRateSummary {
  min: ExchangeRate;
  max: ExchangeRate;
  current: ExchangeRate;
  rates: ExchangeRate[];
}

export interface ExchangeRate {
  amount: number;
  date: string;
}
