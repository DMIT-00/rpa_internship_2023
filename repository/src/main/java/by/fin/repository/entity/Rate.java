package by.fin.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rates")
@IdClass(Rate.RateId.class)
public class Rate {
    @Id
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rate", precision = 20, scale = 4)
    private BigDecimal rate;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class RateId implements Serializable {
        private Currency currency;
        private LocalDate date;
    }
}
