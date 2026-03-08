package br.com.barberManagerAPI.infra.persistence.barberShop;

import br.com.barberManagerAPI.infra.persistence.barber.BarberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "barber_shops")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class BarberShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private ZoneId timezone;
    private boolean active;

    @OneToMany
    private List<BarberEntity> barbers;

    public void deactivate() { this.active = false; }
    public void activate() { this.active = true; }
    public void changeName(String newName) { this.name = newName; }
}
