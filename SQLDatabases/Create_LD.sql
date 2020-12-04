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
    pos_desc        VARCHAR(1000),
    pay_range_high  NUMERIC(8,2),
    pay_range_low   NUMERIC(8,2),
    PRIMARY KEY (pos_code)
);

-- Job(job_code, title, pos_code, emp_mode, {required_skill}, pay_rate, pay_type, company, more ...)
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
    comp_id         NUMERIC(8),
    PRIMARY KEY (job_code),
    FOREIGN KEY (pos_code) REFERENCES Position,
    FOREIGN KEY (comp_id) REFERENCES Company
);

-- GICS(ind_id, ind_title, level, description, parent_id)
-- Relation is used recursively for industry groups, industries and
-- sub-industries.

CREATE TABLE GICS (
    ind_id          NUMERIC(8),
    ind_title       VARCHAR(100),
    ind_level       VARCHAR(14)
        CHECK (ind_level IN ('industry-group', 'industry', 'sub-industry')),
    ind_desc        VARCHAR(1000),
    parent_id       NUMERIC(8),
    PRIMARY KEY (ind_id),
    FOREIGN KEY (parent_id) REFERENCES GICS
);

-- Relation for sub industries; companies can have multiple

CREATE TABLE Sub_Industry (
    comp_id         NUMERIC(8),
    ind_id          NUMERIC(8),
    PRIMARY KEY (comp_id, ind_id),
    FOREIGN KEY (comp_id) REFERENCES Company,
    FOREIGN KEY (ind_id) REFERENCES GICS
);

-- Company(comp_id, address+, zip_code, industry_group, website)
-- - address is a composite field
-- - A company belongs to one industry group but can belong to multiple
--   sub industries.

CREATE TABLE Company (
    comp_id         NUMERIC(8),
    comp_name       VARCHAR(50),
    street_num      VARCHAR(10),
    street_name     VARCHAR(30),
    unit_num        VARCHAR(10),
    city            VARCHAR(20),
    state_code      VARCHAR(2),
    zip_code        VARCHAR(5),
    industry_group  NUMERIC(4),
    website         VARCHAR(50),
    PRIMARY KEY (comp_id),
    FOREIGN KEY (industry_group) references GICS (ind_id)
);

-- Course(c_code, title, level, description, status, retail_price)
-- - c_code is a unique identifier
-- - status is 'active' or 'expired'

CREATE TABLE Course (
    c_code          NUMERIC(8),
    title           VARCHAR(50),
    c_level         VARCHAR(8)
        CHECK (c_level IN ('beginner', 'medium', 'advanced')),
    c_desc          VARCHAR(1000),
    status          VARCHAR(7)
        CHECK (status IN ('active', 'expired')),
    retail_price    NUMERIC(8,2),
    PRIMARY KEY (c_code)
);

-- Section(c_code, sec_no, complete_date, year, offered_by, format, price)
-- - (c_code, sec_no, year) for a unique identifier for every section
-- - format can be classroom, online-sync, online-self-paced, or
--   correspondence.
-- - offered_by refers to a university or training company

CREATE TABLE Section (
    c_code          NUMERIC(8),
    sec_no          NUMERIC(8),
    complete_date   DATE,
    year            NUMERIC(4)
        CHECK (year >= 1900 AND year <= 2020),
    offered_by      VARCHAR(50),
    format          VARCHAR(20)
        CHECK (format IN ('classroom', 'online-sync', 'online-self-paced', 'correspondence')),
    price           NUMERIC(6,2),
    PRIMARY KEY (c_code, sec_no, year),
    FOREIGN KEY (c_code) REFERENCES Course
);

-- Person(per_id, name, address+, zip_code, email, gender, {phone})
-- - per_id is the primary key
-- - LD database records a person's entire job history (ok cool)

CREATE TABLE Person (
    per_id          NUMERIC(8),
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
    gender          VARCHAR(6),
    PRIMARY KEY (per_id)
);

-- Additional Relations
-----------------------

-- Works(per_id, job_code, start_date, end_date)
-- - start_date <= end_date
-- - end_date < today indicates per_id no longer holds the job

CREATE TABLE Works (
    per_id          NUMERIC(8),
    job_code        NUMERIC(8),
    start_date      DATE,
    end_date        DATE,
    PRIMARY KEY (per_id, job_code),
    FOREIGN KEY (per_id) REFERENCES Person,
    FOREIGN KEY (job_code) REFERENCES Job
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

-- Teaches(c_code, sk_code)

CREATE TABLE Teaches (
    c_code          NUMERIC(8),
    sk_code         VARCHAR(10),
    PRIMARY KEY (c_code, sk_code),
    FOREIGN KEY (c_code) REFERENCES Course,
    FOREIGN KEY (sk_code) REFERENCES Skill
);

-- Prerequisite(c_code, required_code)

CREATE TABLE Prerequisite (
    c_code          NUMERIC(8),
    required_code   NUMERIC(8),
    PRIMARY KEY (c_code, required_code),
    FOREIGN KEY (c_code) REFERENCES Course
);

-- Takes(per_id, c_code, sec_no, complete_date)

CREATE TABLE Takes (
    per_id          NUMERIC(8),
    c_code          NUMERIC(8),
    sec_no          NUMERIC(8),
    complete_date   DATE,
    PRIMARY KEY (per_id, c_code, sec_no, complete_date),
    FOREIGN KEY (per_id) REFERENCES Person,
    FOREIGN KEY (c_code, sec_no, complete_date) REFERENCES Section
);

-- Has_Skill(per_id, sk_code)

CREATE TABLE Has_Skill (
    per_id          NUMERIC(8),
    sk_code         VARCHAR(10),
    PRIMARY KEY (per_id, sk_code),
    FOREIGN KEY (per_id) REFERENCES Person,
    FOREIGN KEY (sk_code) REFERENCES Skill
);

-- Relation for phone numbers
-- People can have multiple
CREATE TABLE Phone (
    per_id          NUMERIC(8),
    num             VARCHAR(20),
    phone_desc      VARCHAR(20), -- 'cell', 'work', etc
    PRIMARY KEY (per_id, num),
    FOREIGN KEY (per_id) REFERENCES Person
);