-- Create enum type for employee roles
CREATE TYPE employee_role AS ENUM ('ADMIN', 'MANAGER', 'VOLUNTEER');

-- Create employees table
CREATE TABLE employees (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(60) NOT NULL,
    birth_date DATE NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(60) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(128) NOT NULL,
    role employee_role NOT NULL DEFAULT 'VOLUNTEER',
    cep VARCHAR(9) NOT NULL,
    street VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(60) NOT NULL,
    number VARCHAR(20) NOT NULL,
    city VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    state VARCHAR(20) NOT NULL,
    complement VARCHAR(30),
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create index for better performance
CREATE INDEX idx_employees_email ON employees(email);
CREATE INDEX idx_employees_cpf ON employees(cpf);
CREATE INDEX idx_employees_active ON employees(active);
