package br.com.barberManagerAPI.application.useCases.barberShop.deactivate;

import java.util.UUID;

public record DeactivateBarberShopCommand(UUID tenantId) {
}
