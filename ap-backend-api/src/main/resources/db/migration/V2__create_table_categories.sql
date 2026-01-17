CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    alias VARCHAR(100),
    percentage INT NOT NULL,
    suggested_value DECIMAL(15, 2) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT chk_percentage CHECK (percentage >= 0 AND percentage <= 100),
    CONSTRAINT chk_suggested_value CHECK (suggested_value >= 0)
);

CREATE INDEX idx_categories_name ON categories(name);
CREATE INDEX idx_categories_percentage ON categories(percentage);
