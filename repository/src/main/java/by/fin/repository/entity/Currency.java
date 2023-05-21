package by.fin.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency")
public class Currency {
    @Id
    @Column(name = "currency_id")
    private Long id;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "name")
    private String name;

    @Column(name = "scale")
    private Long scale;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rate> rates = new ArrayList<>();

    public void addRate(Rate rate) {
        rates.add(rate);
        rate.setCurrency(this);
    }

    public void removeRate(Rate rate) {
        rates.remove(rate);
        rate.setCurrency(null);
    }
}
