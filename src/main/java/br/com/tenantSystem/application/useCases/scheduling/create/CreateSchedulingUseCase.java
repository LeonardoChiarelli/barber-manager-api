package br.com.tenantSystem.application.useCases.scheduling.create;

import br.com.tenantSystem.application.ports.out.*;
import br.com.tenantSystem.application.useCases.exception.BusinessRules;
import br.com.tenantSystem.domain.scheduling.Scheduling;
import jakarta.persistence.EntityNotFoundException;

public class CreateSchedulingUseCase {
    private final SchedulingRepository repository;
    private final BarberRepository barberRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final ServiceRepository serviceRepository;
    private final TenantRepository tenantRepository;

    public CreateSchedulingUseCase(SchedulingRepository repository, BarberRepository barberRepository, WorkScheduleRepository workScheduleRepository, ServiceRepository serviceRepository, TenantRepository tenantRepository) {
        this.repository = repository;
        this.barberRepository = barberRepository;
        this.workScheduleRepository = workScheduleRepository;
        this.serviceRepository = serviceRepository;
        this.tenantRepository = tenantRepository;
    }

    public void execute(CreateSchedulingCommand cmd) {

        var existsTenant = tenantRepository.existsById(cmd.tenantId());
        var barber = barberRepository.findById(cmd.barberId()).orElseThrow(() -> new EntityNotFoundException("Barber was not found"));
        var service = serviceRepository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service was not found"));
        var workSchedule = workScheduleRepository.existsByIdAndWeekDay(barber.getId(), cmd.startTime().getDayOfWeek().getValue());

        if (!existsTenant) {
            throw new BusinessRules("Tenant was not found");
        }

        var existsScheduleByStartTime = repository.existsByBarberIdAndStartDate(barber.getId(), cmd.startTime());
        var existsScheduleByEndTime = repository.existsByBarberIdAndEndTime(barber.getId(), cmd.startTime().plus(service.getDurationMinutes()));

        if ((existsScheduleByEndTime || existsScheduleByStartTime)) {
            throw new BusinessRules("Barber already have a schedule at this time");
        }

        var scheduling = Scheduling.builder()
                .schedulingOrigin(cmd.schedulingOrigin())
                .schedulingStatus(cmd.schedulingStatus())
                .barberId(barber.getId())
                .clientName(cmd.clientName())
                .clientPhone(cmd.clientPhone())
                .serviceId(service.getId())
                .tenantId(cmd.tenantId())
                .startTime(cmd.startTime())
                .endTime(cmd.startTime().plus(service.getDurationMinutes()))
                .googleEventId(cmd.googleEventId())
                .build();

        repository.save(scheduling);
    }
}
