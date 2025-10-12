-- Create events table
CREATE TABLE events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    date TIMESTAMP NOT NULL,
    greeting_description VARCHAR(1000),
    attendance INTEGER,
    embedded_instagram TEXT,
    status VARCHAR(20) NOT NULL,
    street VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(30) NOT NULL,
    number VARCHAR(20) NOT NULL,
    city VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    state VARCHAR(20) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complement VARCHAR(30),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create index for better performance
CREATE INDEX idx_events_date ON events(date);
CREATE INDEX idx_events_status ON events(status);
CREATE INDEX idx_events_active ON events(active);
