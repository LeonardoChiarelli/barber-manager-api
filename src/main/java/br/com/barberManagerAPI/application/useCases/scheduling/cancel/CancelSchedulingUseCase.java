package br.com.barberManagerAPI.application.useCases.scheduling.cancel;

import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import br.com.barberManagerAPI.domain.scheduling.SchedulingStatus;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CancelSchedulingUseCase {

    private final SchedulingRepository repository;

    public CancelSchedulingUseCase(SchedulingRepository repository) {
        this.repository = repository;
    }

    public void execute(CancelSchedulingCommand cmd) {

        var scheduling = repository.findById(cmd.schedulingId()).orElseThrow(() -> new EntityNotFoundException("Scheduling does not exist"));

        var hoursDifference = ChronoUnit.HOURS.between(scheduling.getStartTime(), LocalDateTime.now());

        if (hoursDifference < 2) {
            throw new BusinessRules("Scheduling start time must be greater than 2 to be cancelled");
        }

        repository.changeStatus(cmd.schedulingId(), SchedulingStatus.CANCELED);

        // Possivelmente enviar para a fila e remover da visualização do Google Calendar
    }
}
