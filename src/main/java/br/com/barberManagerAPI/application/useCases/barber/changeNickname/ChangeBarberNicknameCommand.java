package br.com.barberManagerAPI.application.useCases.barber.changeNickname;

import java.util.UUID;

public record ChangeBarberNicknameCommand(UUID barberId, String nickname) {
}
