package ee.laus.exchange.repository;

import ee.laus.exchange.model.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("select c from Currency c " +
           "inner join ExchangeRate er on er.currency.id = c.id " +
           "where lower(c.name) like lower(concat('%', :searchTerm, '%')) or " +
           "lower(c.code) like lower(concat('%', :searchTerm, '%')) " +
           "group by c.id")
    List<Currency> findAllBySearchTerm(String searchTerm);

    @Query("select c from Currency c " +
           "inner join ExchangeRate er on er.currency.id = c.id " +
           "group by c.id")
    List<Currency> findAllWithExchangeRate();

    Optional<Currency> findByCode(String code);
}
