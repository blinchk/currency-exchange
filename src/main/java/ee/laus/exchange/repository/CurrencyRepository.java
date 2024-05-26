package ee.laus.exchange.repository;

import ee.laus.exchange.model.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("select c from Currency c where " +
           "lower(c.name) like lower(concat('%', :searchTerm, '%')) or " +
           "lower(c.code) like lower(concat('%', :searchTerm, '%'))")
    List<Currency> findAllBySearchTerm(String searchTerm);

    Currency findByCode(String code);
}
