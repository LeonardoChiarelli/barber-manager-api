package br.com.barberManagerAPI.application.useCases.scheduling.changeService;

import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.ports.out.ServiceRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChangeServiceSchedulingUseCase {
    private final SchedulingRepository repository;
    private final ServiceRepository serviceRepository;

    public ChangeServiceSchedulingUseCase(SchedulingRepository repository, ServiceRepository serviceRepository) {
        this.repository = repository;
        this.serviceRepository = serviceRepository;
    }

    public void execute(ChangeServiceSchedulingCommand cmd) {

        var scheduling = repository.findById(cmd.schedulingId()).orElseThrow(() -> new EntityNotFoundException("Scheduling not found."));
        var service = serviceRepository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found."));

        var newTimeEnd = scheduling.getStartTime().plus(service.getDurationMinutes());

        var canScheduleEndTime = repository.existsByBarberIdAndEndTime(scheduling.getBarberId(), newTimeEnd);
        var hoursDifference = ChronoUnit.HOURS.between(scheduling.getStartTime(), LocalDateTime.now());

        if (!(canScheduleEndTime)) {
            throw new BusinessRules("You cannot change your service");
        }

        if (hoursDifference < 2) {
            throw new BusinessRules("You cannot change your service");
        }

        repository.changeEndTime(scheduling.getBarberId(), newTimeEnd);
        scheduling.changeEndTime(newTimeEnd);
    }
}
