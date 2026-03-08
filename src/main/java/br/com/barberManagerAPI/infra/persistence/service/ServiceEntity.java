package br.com.barberManagerAPI.infra.persistence.service;

import br.com.barberManagerAPI.infra.persistence.barberShop.BarberShopEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Entity
@Table(name = "services")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BarberShopEntity barberShopEntity;

    private String name;
    private Duration durationMinutes;
    private BigDecimal price;
    private boolean active;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDurationMinutes(Duration newDurationMinutes) {
        this.durationMinutes = newDurationMinutes;
    }

    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
