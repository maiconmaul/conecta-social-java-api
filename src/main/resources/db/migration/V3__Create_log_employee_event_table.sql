-- Create log_employee_event table
CREATE TABLE log_employee_event (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_employee UUID NOT NULL,
    id_event UUID NOT NULL,
    log_message VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraints
    CONSTRAINT fk_log_employee_event_employee 
        FOREIGN KEY (id_employee) REFERENCES employees(id),
    CONSTRAINT fk_log_employee_event_event 
        FOREIGN KEY (id_event) REFERENCES events(id)
);

-- Create index for better performance
CREATE INDEX idx_log_employee_event_employee ON log_employee_event(id_employee);
CREATE INDEX idx_log_employee_event_event ON log_employee_event(id_event);
CREATE INDEX idx_log_employee_event_created_at ON log_employee_event(created_at);
