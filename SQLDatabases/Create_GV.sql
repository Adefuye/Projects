-- Factory(fac_id, fac_name, address, zip_code, phone, manager)

CREATE TABLE Factory (
    fac_id          NUMERIC(8),
    fac_name        VARCHAR(50),
    street_num      VARCHAR(10),
    street_name     VARCHAR(30),
    unit_num        VARCHAR(10),
    city            VARCHAR(20),
    state           VARCHAR(2), -- Two-letter state code
    zip_code        NUMERIC(5),
    phone           VARCHAR(20),
    manager         VARCHAR(30),
    PRIMARY KEY (fac_id)
);

-- Material(m_code, m_name, quantity, unit, min_level)

CREATE TABLE Material (
    m_code          VARCHAR(3),
    m_name          VARCHAR(50),
    quantity        NUMERIC(8),
    unit            VARCHAR(8),
    min_level       NUMERIC(8),
    PRIMARY KEY (m_code)
);

-- Product(p_code, p_name, description, quantity, unit, avg_cost)

CREATE TABLE Product (
    p_code          NUMERIC(8),
    p_name          VARCHAR(50),
    description     VARCHAR(1000),
    quantity        NUMERIC(8),
    unit            VARCHAR(8),
    avg_cost        NUMERIC(4),
    PRIMARY KEY (p_code)
);

-- Contract(contract_id, cus_id, date, sale_amount, pay_schedule)
-- - cus_id = comp_id in Customer relation
-- - pay_schedule is a document identifier

CREATE TABLE Contract (
    contract_id     NUMERIC(8),
    cus_id          NUMERIC(8),
    contract_date   DATE,
    sale_amount     NUMERIC(8,2),
    pay_schedule    VARCHAR(8),
    PRIMARY KEY (contract_id),
    FOREIGN KEY (cus_id) REFERENCES Customer
);

-- LineItem(contract_id, p_code, quantity)

CREATE TABLE LineItem (
    contract_id     NUMERIC(8),
    p_code          NUMERIC(8),
    quantity        NUMERIC(8),
    PRIMARY KEY (contract_id, p_code),
    FOREIGN KEY (contract_id) REFERENCES Contract,
    FOREIGN KEY (p_code) REFERENCES Product
);

-- Purchase(purchase_num, sup_id, sup_order_num, book_date, pay_date, note)

CREATE TABLE Purchase (
    purchase_num    NUMERIC(8),
    sup_id          NUMERIC(8),
    sup_order_num   NUMERIC(8),
    book_date       DATE,
    pay_date        DATE,
    note            VARCHAR(1000),
    PRIMARY KEY (purchase_num),
    FOREIGN KEY (sup_id) REFERENCES Supplier
);

-- PurchaseLine(purchase_num, m_code, quantity)

CREATE TABLE PurchaseLine (
    purchase_num    NUMERIC(8),
    m_code          VARCHAR(3),
    quantity        NUMERIC(8),
    PRIMARY KEY (purchase_num, m_code, quantity),
    FOREIGN KEY (purchase_num) REFERENCES Purchase,
    FOREIGN KEY (m_code) REFERENCES Material
);

-- Supplier(sup_id, website, contact_email)

CREATE TABLE Supplier (
    sup_id          NUMERIC(8),
    website         VARCHAR(100),
    contact_email   VARCHAR(50),
    PRIMARY KEY (sup_id)
);

CREATE TABLE Account_receivable (
    sup_id          NUMERIC(8),
    balance         NUMERIC(8,2),
    PRIMARY KEY (sup_id),
    FOREIGN KEY (sup_id) REFERENCES Supplier
);

-- Factory <-> Supplier relation

CREATE TABLE Supplies (
    sup_id          NUMERIC(8),
    fac_id          NUMERIC(8),
    PRIMARY KEY (sup_id, fac_id),
    FOREIGN KEY (sup_id) REFERENCES Supplier,
    FOREIGN KEY (fac_id) REFERENCES Factory
);

-- Customer(comp_id, contact_person, contact_email)

CREATE TABLE Customer (
    cus_id          NUMERIC(8),
    fac_id          NUMERIC(8),
    contact_person  VARCHAR(30),
    contact_email   VARCHAR(50),
    PRIMARY KEY (cus_id),
    FOREIGN KEY (fac_id) REFERENCES Factory
);

-- makes(fac_id, p_code, quantity)

CREATE TABLE makes (
    fac_id          NUMERIC(8),
    p_code          NUMERIC(8),
    quantity        NUMERIC(8),
    PRIMARY KEY (fac_id, p_code, quantity),
    FOREIGN KEY (fac_id) REFERENCES Factory,
    FOREIGN KEY (p_code, quantity) REFERENCES Product
);

CREATE TABLE Employee (
    emp_id          NUMERIC(8),
    fac_id          NUMERIC(8),
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
    mid_initial     VARCHAR(1),
    street_num      VARCHAR(10),
    street_name     VARCHAR(30),
    unit_num        VARCHAR(10),
    city            VARCHAR(20),
    state           VARCHAR(2), -- Two-letter state code
    zip_code        NUMERIC(5),
    email           VARCHAR(50),
    gender          VARCHAR(20),
    PRIMARY KEY (emp_id),
    FOREIGN KEY (fac_id) REFERENCES Factory
);

-- Skill(sk_code, title, description, sk_level)
-- - sk_code is a unique identifier
-- - sk_level can be 'beginner', 'medium', 'advanced'

CREATE TABLE Skill (
    sk_code         VARCHAR(10),
    title           VARCHAR(50),
    description     VARCHAR(1000),
    sk_level        VARCHAR(8)
        CHECK (sk_level IN ('beginner', 'medium', 'advanced')),
    PRIMARY KEY (sk_code)
);

-- Position(pos_code, title, description, pay_range_high, pay_range_low)
-- - pos_code is a unique identifier

CREATE TABLE Position (
    pos_code        VARCHAR(10),
    title           VARCHAR(50),
    description     VARCHAR(1000),
    pay_range_high  NUMERIC(8,2),
    pay_range_low   NUMERIC(8,2),
    PRIMARY KEY (pos_code)
);

-- Job(job_code, title, pos_code, emp_mode, pay_rate, pay_type, company)
-- - job_code is a unique identifier
-- - emp_mode can be 'full-time' or 'part-time'
-- - pay_type can be 'wage' or 'salary'
-- - pay_rate is the hourly rate when pay_type = 'wage'; annual pay when
--   pay_type = 'salary'
-- - "A position belongs to one position only"

CREATE TABLE Job (
    job_code        NUMERIC(8),
    title           VARCHAR(50),
    pos_code        VARCHAR(10),
    emp_mode        VARCHAR(9)
        CHECK (emp_mode IN ('full-time', 'part-time')),
    pay_rate        NUMERIC(8,2),
    pay_type        VARCHAR(6)
        CHECK (pay_type IN ('wage', 'salary')),
    fac_id          NUMERIC(8),
    PRIMARY KEY (job_code),
    FOREIGN KEY (pos_code) REFERENCES Position,
    FOREIGN KEY (fac_id) REFERENCES Factory
);

-- Additional Relations
-----------------------

-- Works(per_id, job_code, start_date, end_date)
-- - start_date <= end_date
-- - end_date < today indicates per_id no longer holds the job

CREATE TABLE Works (
    emp_id          NUMERIC(8),
    job_code        NUMERIC(8),
    start_date      DATE,
    end_date        DATE,
    PRIMARY KEY (emp_id, job_code),
    FOREIGN KEY (emp_id) REFERENCES Employee,
    FOREIGN KEY (job_code) REFERENCES Job
);

-- Has_Skill(emp_id, sk_code)

CREATE TABLE Has_Skill (
    emp_id          NUMERIC(8),
    sk_code         VARCHAR(10),
    PRIMARY KEY (emp_id, sk_code),
    FOREIGN KEY (emp_id) REFERENCES Employee,
    FOREIGN KEY (sk_code) REFERENCES Skill
);

-- Requires(pos_code, sk_code)

CREATE TABLE Requires (
    pos_code        VARCHAR(10),
    sk_code         VARCHAR(10),
    PRIMARY KEY (pos_code, sk_code),
    FOREIGN KEY (pos_code) REFERENCES Position,
    FOREIGN KEY (sk_code) REFERENCES Skill
);

-- Requires_by_job(job_code, sk_code)
-- For extra requirements

CREATE TABLE Requires_by_job (
    job_code        NUMERIC(8),
    sk_code         VARCHAR(10),
    PRIMARY KEY (job_code, sk_code),
    FOREIGN KEY (job_code) REFERENCES Job,
    FOREIGN KEY (sk_code) REFERENCES Skill
);

-- Relation for phone numbers
-- Employees can have multiple
CREATE TABLE Phone (
    emp_id          NUMERIC(8),
    num             VARCHAR(20),
    description     VARCHAR(20), -- 'cell', 'work', etc
    PRIMARY KEY (emp_id, num),
    FOREIGN KEY (emp_id) REFERENCES Employee
);
