package ee.laus.exchange.model.exchange;

import ee.laus.exchange.model.currency.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Currency currency;
    private Double amount;
    private LocalDate date;
}
