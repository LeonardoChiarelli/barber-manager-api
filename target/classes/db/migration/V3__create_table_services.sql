CREATE TABLE services(

    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name varchar(150) NOT NULL,
    duration_minutes INTERVAL NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    active BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_services_tenant_id FOREIGN KEY(tenant_id) REFERENCES tenants(id),
    CONSTRAINT name_not_blank CHECK ( character_length(trim(name)) > 0)
);

CREATE INDEX idx_services_name ON services(name);
CREATE INDEX idx_services_active ON services(active)