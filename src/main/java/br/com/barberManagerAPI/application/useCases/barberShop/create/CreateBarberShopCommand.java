package br.com.barberManagerAPI.application.useCases.barberShop.create;

import java.time.ZoneId;

public record CreateBarberShopCommand(String name, ZoneId timezone, boolean active) {
}
