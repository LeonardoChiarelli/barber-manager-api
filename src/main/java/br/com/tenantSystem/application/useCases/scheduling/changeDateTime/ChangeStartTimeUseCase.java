package br.com.tenantSystem.application.useCases.scheduling.changeDateTime;

import br.com.tenantSystem.application.ports.out.SchedulingRepository;
import br.com.tenantSystem.application.ports.out.ServiceRepository;
import br.com.tenantSystem.application.useCases.exception.BusinessRules;
import jakarta.persistence.EntityNotFoundException;

public class ChangeStartTimeUseCase {
    private final SchedulingRepository repository;
    private final ServiceRepository serviceRepository;

    public ChangeStartTimeUseCase(SchedulingRepository schedulingRepository, ServiceRepository serviceRepository) {
        this.repository = schedulingRepository;
        this.serviceRepository = serviceRepository;
    }

    public void execute(ChangeStartTimeCommand cmd) {

        var scheduling = repository.findById(cmd.schedulingId()).orElseThrow(() -> new EntityNotFoundException("Scheduling was not found"));
        var service = serviceRepository.findById(scheduling.getServiceId()).orElseThrow(() -> new EntityNotFoundException("Service was not found"));

        var endTime = cmd.newStartTime().plus(service.getDurationMinutes());

        var canScheduleStartTime = repository.existsByBarberIdAndStartDate(scheduling.getId(), cmd.newStartTime());
        var canScheduleEndTime = repository.existsByBarberIdAndEndTime(scheduling.getId(), endTime);

        if (!(canScheduleStartTime || canScheduleEndTime)) {
            throw new BusinessRules("Invalid date range");
        }

        repository.changeStartTime(scheduling.getId(), cmd.newStartTime());
        repository.changeEndTime(scheduling.getId(), endTime);

        scheduling.changeStartTime(cmd.newStartTime());
        scheduling.changeEndTime(endTime);
    }
}
