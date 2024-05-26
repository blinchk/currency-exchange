# Currency Exchange App
The project is a web application that provides Bank of Lithuania exchange rates. The main features include: a page with exchange rates, a history viewer of the exchange rate of the currency, and a currency exchange rate calculator.
## Features
* __List Of Currencies__: List of currencies, that have exchange rates from March 2024.
* __Currency Calculator__: Converts an entered amount to a foreign currency based on the latest exchange rate.
* __Exchange Rate History__: Shows the history of exchange rates for a selected currency in a chart.

## Technologies
### BE
* Java, Maven, Spring Boot
* Quartz (for job scheduling)

### FE
* TypeScript, Angular, Sass
* Bootstrap
* Apache ECharts

### Database
The application uses an H2 database to store exchange rate data. The database is configured to run in embedded mode, making it lightweight and easy to set up.

## Docker Setup

Run `docker compose up -d`.

## Manual Setup
### BE
```shell
./mvnw clean install
./mvnw spring-boot:run
```

The application will be available at http://localhost:8080.
### FE
```shell
cd frontend
npm run install
ng serve
```

The application will be available at http://localhost:4200.