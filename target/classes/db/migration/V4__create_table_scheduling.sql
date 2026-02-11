CREATE TABLE scheduling(

    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    barber_id UUID NOT NULL,
    service_id UUID NOT NULL,
    client_name VARCHAR(150) NOT NULL,
    client_phone VARCHAR(15) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT "PENDING",
    origin VARCHAR(100) NOT NULL,
    google_event_id VARCHAR(255) NOT NULL,
    create_at time DEFAULT CURRENT_TIME,

    CONSTRAINT fk_scheduling_tenant_id FOREIGN KEY(tenant_id) REFERENCES tenants(id),
    CONSTRAINT fk_scheduling_barber_id FOREIGN KEY(barber_id) REFERENCES barbes(id),
    CONSTRAINT fk_scheduling_service_id FOREIGN KEY(service_id) REFERENCES services(id),
);

CREATE INDEX idx_scheduling_barber_start_time ON scheduling(barber_id, start_time);
CREATE INDEX idx_scheduling_barber_status ON scheduling(barber_id, status);