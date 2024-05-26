package ee.laus.exchange.repository;

import ee.laus.exchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> { }
