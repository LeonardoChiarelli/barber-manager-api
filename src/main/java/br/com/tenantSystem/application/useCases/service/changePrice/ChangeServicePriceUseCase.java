package br.com.tenantSystem.application.useCases.service.changePrice;

import br.com.tenantSystem.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeServicePriceUseCase {

    private final ServiceRepository repository;

    public ChangeServicePriceUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeServicePriceCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        if (service.getPrice().equals(cmd.newPrice())) {
            throw new IllegalArgumentException("Service's old price is equals to new price");
        }

        repository.changePriceById(cmd.serviceId(), cmd.newPrice());
        service.changePrice(cmd.newPrice());
    }
}
