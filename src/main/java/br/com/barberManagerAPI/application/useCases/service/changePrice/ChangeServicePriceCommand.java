package br.com.barberManagerAPI.application.useCases.service.changePrice;

import java.math.BigDecimal;
import java.util.UUID;

public record ChangeServicePriceCommand(UUID serviceId, BigDecimal newPrice) {
}
