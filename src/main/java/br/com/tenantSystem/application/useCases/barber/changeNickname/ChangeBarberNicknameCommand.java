package br.com.tenantSystem.application.useCases.barber.changeNickname;

import java.util.UUID;

public record ChangeBarberNicknameCommand(UUID barberId, String nickname) {
}
