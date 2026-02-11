CREATE TABLE barbers(

    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,
    nickname VARCHAR(200) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_barbers_tenant_id FOREIGN KEY(tenant_id) REFERENCES tenants(id),
    CONSTRAINT name_not_blank CHECK ( character_length(trim(name)) > 0)
);

CREATE INDEX idx_barber_name ON barbers(name);
CREATE INDEX idx_barber_nickname ON barbers(nickname)