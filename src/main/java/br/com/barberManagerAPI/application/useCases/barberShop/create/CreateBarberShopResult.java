package br.com.barberManagerAPI.application.useCases.barberShop.create;

import java.time.ZoneId;
import java.util.UUID;

public record CreateBarberShopResult(UUID id, String name, ZoneId timezone, boolean active) {
}
