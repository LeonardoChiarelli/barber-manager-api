package br.com.barberManagerAPI.infra.persistence.barber;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "barbers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class BarberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UUID barberShopId;

    private String name;
    private String nickname;
    private boolean active;

    public void activate() { this.active = true; }
    public void deactivate() { this.active = false; }
    public void changeNickname(String nickname) { this.nickname = nickname; }
}
