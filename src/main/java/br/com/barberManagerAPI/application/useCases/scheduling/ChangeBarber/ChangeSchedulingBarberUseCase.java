package br.com.barberManagerAPI.application.useCases.scheduling.ChangeBarber;

import br.com.barberManagerAPI.application.ports.out.BarberRepository;
import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.ports.out.ServiceRepository;
import br.com.barberManagerAPI.application.ports.out.WorkScheduleRepository;
import br.com.barberManagerAPI.application.useCases.scheduling.create.CreateSchedulingCommand;
import br.com.barberManagerAPI.application.useCases.scheduling.create.CreateSchedulingUseCase;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import br.com.barberManagerAPI.domain.scheduling.SchedulingStatus;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChangeSchedulingBarberUseCase {

    private final SchedulingRepository repository;
    private final BarberRepository barberRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final ServiceRepository serviceRepository;
    private final CreateSchedulingUseCase createSchedulingUseCase;

    public ChangeSchedulingBarberUseCase(SchedulingRepository repository, BarberRepository barberRepository, WorkScheduleRepository workScheduleRepository, ServiceRepository serviceRepository, CreateSchedulingUseCase createSchedulingUseCase) {
        this.repository = repository;
        this.barberRepository = barberRepository;
        this.workScheduleRepository = workScheduleRepository;
        this.serviceRepository = serviceRepository;
        this.createSchedulingUseCase = createSchedulingUseCase;
    }

    public void execute(ChangeSchedulingBarberCommand cmd) {

        var scheduling = repository.findById(cmd.schedulingId()).orElseThrow(() -> new EntityNotFoundException("Scheduling not found"));
        var service = serviceRepository.findById(scheduling.getServiceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        var oldBarber = barberRepository.findById(cmd.oldBarberId()).orElseThrow(() -> new EntityNotFoundException("Barber not found"));
        var oldBarberWorkSchedule = workScheduleRepository.findById(oldBarber.getId()).orElseThrow(() -> new EntityNotFoundException("Work Schedule not found"));

        var newBarber = barberRepository.findById(cmd.newBarberId()).orElseThrow(() -> new EntityNotFoundException("Barber not found"));
        var newBarberWorkSchedule = workScheduleRepository.existsByIdAndWeekDay(newBarber.getId(), scheduling.getStartTime().getDayOfWeek().getValue());

        // Abstrair regras de negócio para uma Strategy
            // Lógica de validação (oldBarber), startDate > 2
        var hoursDifference = ChronoUnit.HOURS.between(scheduling.getStartTime(), LocalDateTime.now());

        if (hoursDifference < 2) {
            throw new BusinessRules("Scheduling start time must be greater than 2 to be cancelled");
        }

        repository.changeStatus(cmd.schedulingId(), SchedulingStatus.CANCELED);

            // Lógica de validação (newBarber) -> Se está trabalhando no dia, Se tem agenda disponível (StartTime, EndTime e DurationService)

        var existsScheduleByStartTime = repository.existsByBarberIdAndStartDate(newBarber.getId(), scheduling.getStartTime());
        var existsScheduleByEndTime = repository.existsByBarberIdAndEndTime(newBarber.getId(), scheduling.getEndTime());

        if (existsScheduleByStartTime || existsScheduleByEndTime) {
            throw new BusinessRules("Barber already have a scheduled start and end time");
        }

        if (!newBarberWorkSchedule) {
            throw new BusinessRules("Barber doesn't work this day.");
        }

        createSchedulingUseCase.execute(new CreateSchedulingCommand(
                scheduling.getTenantId(),
                newBarber.getId(),
                scheduling.getServiceId(),
                scheduling.getClientName(),
                scheduling.getClientPhone(),
                scheduling.getStartTime(),
                SchedulingStatus.CONFIRMED,
                scheduling.getOrigin(),
                scheduling.getGoogleEventId()
                ));
    }
}
