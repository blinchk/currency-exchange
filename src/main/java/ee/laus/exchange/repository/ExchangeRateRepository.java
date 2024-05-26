package ee.laus.exchange.repository;

import ee.laus.exchange.model.exchange.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("select er from ExchangeRate er where er.currency.code = :code")
    List<ExchangeRate> findAllByCurrencyCode(String code);
    @Query("select er from ExchangeRate er where er.currency.code = :code order by er.date desc limit 1")
    Optional<ExchangeRate> findCurrentByCurrencyCode(String code);
}
