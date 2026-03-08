package br.com.barberManagerAPI.application.useCases.barberShop.changeName;

import java.util.UUID;

public record ChangeBarberShopNameCommand(UUID tenantId, String newName) {
}
