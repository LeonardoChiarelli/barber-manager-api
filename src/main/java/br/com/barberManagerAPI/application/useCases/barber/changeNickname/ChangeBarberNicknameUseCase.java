package br.com.barberManagerAPI.application.useCases.barber.changeNickname;

import br.com.barberManagerAPI.application.ports.out.BarberRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeBarberNicknameUseCase {

    private final BarberRepository repository;

    public ChangeBarberNicknameUseCase(BarberRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeBarberNicknameCommand cmd) {

        var barber = repository.findById(cmd.barberId()).orElseThrow(() -> new EntityNotFoundException("Barber does not exist"));

        if (barber.getNickname().equals(cmd.nickname())) {
            throw new IllegalArgumentException("Barber's old nickname is equals to new nickname");
        }

        repository.changeNicknameById(cmd.barberId(), cmd.nickname());
        barber.changeNickname(cmd.nickname());
    }
}
