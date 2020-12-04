-- Store(store_id, address+, zip_code, phone)

CREATE TABLE Store (
    store_id        NUMERIC(8),
    street_num      VARCHAR(10),
    street_name     VARCHAR(30),
    unit_num        VARCHAR(10),
    city            VARCHAR(20),
    state_code      VARCHAR(2),
    zip_code        VARCHAR(5),
    phone           VARCHAR(20),
    PRIMARY KEY (store_id)
);

-- Position(pos_code, title, description, pay_range_high, pay_range_low)
-- - pos_code is a unique identifier

CREATE TABLE Position (
    pos_code        VARCHAR(10),
    title           VARCHAR(50),
    pos_desc        VARCHAR(1000),
    pay_range_high  NUMERIC(8,2),
    pay_range_low   NUMERIC(8,2),
    PRIMARY KEY (pos_code)
);

-- Skill(sk_code, title, description, sk_level)
-- - sk_code is a unique identifier
-- - sk_level can be 'beginner', 'medium', 'advanced'

CREATE TABLE Skill (
    sk_code         VARCHAR(10),
    title           VARCHAR(50),
    sk_desc         VARCHAR(1000),
    sk_level        VARCHAR(8)
        CHECK (sk_level IN ('beginner', 'medium', 'advanced')),
    PRIMARY KEY (sk_code)
);

-- Job(job_code, pos_code, emp_mode, {required_skill}, pay_rate, pay_type,
--     cate_code, company, more ...)
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
    store_id        NUMERIC(8),
    PRIMARY KEY (job_code),
    FOREIGN KEY (pos_code) REFERENCES Position,
    FOREIGN KEY (store_id) REFERENCES Store
);

-- Inventory(item_num, store_id, title, description, min_level, unit, avg_cost, quantity, old_date)
-- Fields modified from assignment for simplicity and to make mock
-- data generation easier.

CREATE TABLE Inventory (
    item_num        VARCHAR(8),
    store_id        NUMERIC(8),
    title           VARCHAR(50),
    item_desc       VARCHAR(1000),
    min_level       NUMERIC(8),
    unit            VARCHAR(8),
    avg_cost        NUMERIC(8,2),
    quantity        NUMERIC(8),
    old_date        DATE,
    PRIMARY KEY (item_num, store_id),
    FOREIGN KEY (store_id) REFERENCES Store
);

-- Sales(invoice_num, date, item_num, store_id, quantity, price, note)

CREATE TABLE Sales (
    invoice_num     NUMERIC(8),
    sale_date       DATE,
    item_num        VARCHAR(8),
    store_id        NUMERIC(8),
    quantity        NUMERIC(8,0),
    price           NUMERIC(8,2),
    note            VARCHAR(1000),
    PRIMARY KEY (invoice_num),
    FOREIGN KEY (item_num, store_id) REFERENCES Inventory
);

-- Purchase(pur_num, date, item_num, store_id, quantity, unit_cost, note)

CREATE TABLE Purchase (
    pur_num         NUMERIC(8),
    pur_date        DATE,
    item_num        VARCHAR(8),
    store_id        NUMERIC(8),
    quantity        NUMERIC(8,0),
    unit_cost       NUMERIC(8,2),
    note            VARCHAR(1000),
    PRIMARY KEY (pur_num),
    FOREIGN KEY (item_num, store_id) REFERENCES Inventory
);

-- Account_payable(sup_id, balance)

CREATE TABLE Account_payable (
    sup_id          NUMERIC(8),
    balance         NUMERIC(8,2),
    PRIMARY KEY (sup_id),
    FOREIGN KEY (sup_id) REFERENCES Supplier
);

CREATE TABLE Supplier (
    sup_id          NUMERIC(8),
    website         VARCHAR(50),
    contact_email   VARCHAR(30),
    PRIMARY KEY (sup_id)
);

-- PurchasePaymentRecord(sup_id, pur_num, date, amount, trans_type)

CREATE TABLE PurchasePaymentRecord (
    sup_id          NUMERIC(8),
    pur_num         NUMERIC(8),
    pur_date        DATE,
    amount          NUMERIC(8,2),
    trans_type      VARCHAR(6)
        CHECK (trans_type IN ('credit', 'debit')),
    PRIMARY KEY (sup_id, pur_num),
    FOREIGN KEY (sup_id) REFERENCES Supplier,
    FOREIGN KEY (pur_num, pur_date)
        REFERENCES Purchase (pur_num, pur_date)
);

CREATE TABLE Customer (
    cus_id          NUMERIC(8),
    store_id        NUMERIC(8),
    contact_person  VARCHAR(30),
    contact_email   VARCHAR(50),
    PRIMARY KEY (cus_id, store_id),
    FOREIGN KEY (store_id) REFERENCES Store
);

CREATE TABLE Employee (
    emp_id          NUMERIC(8),
    store_id        NUMERIC(8),
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
    mid_initial     VARCHAR(1),
    street_num      VARCHAR(10),
    street_name     VARCHAR(30),
    unit_num        VARCHAR(10),
    city            VARCHAR(20),
    state_code      VARCHAR(2),
    zip_code        NUMERIC(5),
    email           VARCHAR(50),
    gender          VARCHAR(20),
    PRIMARY KEY (emp_id),
    FOREIGN KEY (store_id) REFERENCES Store
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
    phone_desc      VARCHAR(20), -- 'cell', 'work', etc
    PRIMARY KEY (emp_id, num),
    FOREIGN KEY (emp_id) REFERENCES Employee
);