package br.com.barberManagerAPI.infra.persistence.scheduling;

import br.com.barberManagerAPI.domain.scheduling.SchedulingOrigin;
import br.com.barberManagerAPI.domain.scheduling.SchedulingStatus;
import br.com.barberManagerAPI.infra.persistence.barber.BarberEntity;
import br.com.barberManagerAPI.infra.persistence.barberShop.BarberShopEntity;
import br.com.barberManagerAPI.infra.persistence.service.ServiceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "scheduling")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class SchedulingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BarberShopEntity barberShopEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private BarberEntity barberEntity;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ServiceEntity> servicesEntity;

    private String clientName;
    private String clientPhone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus schedulingStatus;

    @Enumerated(EnumType.STRING)
    private SchedulingOrigin schedulingOrigin;

    private Optional<String> googleEventId;
    private LocalTime createAt;

    private void changeBarber(BarberEntity barberEntity) { this.barberEntity = barberEntity; }

    public void changeStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void changeEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void changeStatus(SchedulingStatus schedulingStatus) {
        this.schedulingStatus = schedulingStatus;
    }
}
