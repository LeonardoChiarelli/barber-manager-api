CREATE TABLE tenants(

    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    timezone VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT name_not_blank CHECK ( character_length(trim(name)) > 0)
);

CREATE INDEX idx_tenants_name ON tenants(name)