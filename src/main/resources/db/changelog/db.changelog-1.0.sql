--changeset ulitsaRaskolnikova:1
CREATE TYPE project_type AS ENUM ('phys', 'jur', 'ip');

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username TEXT,
    avatar BYTEA,
    email TEXT UNIQUE,
    balance DECIMAL(34, 2),
    password TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL REFERENCES "user"(id),
    name TEXT NOT NULL,
    quick_peek TEXT,
    quick_peek_picture BYTEA,
    category VARCHAR(255),
    location VARCHAR(255),
    duration_days INTEGER DEFAULT 30,
    wanted_money DECIMAL(34, 2),
    is_public BOOLEAN DEFAULT TRUE,
    current_money DECIMAL(34, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_project_relation (
    project_id INTEGER NOT NULL REFERENCES project(id),
    user_id INTEGER NOT NULL REFERENCES "user"(id),
    relation_type INTEGER NOT NULL,
    relation_arg INTEGER,
    PRIMARY KEY (project_id, user_id)
);

CREATE TABLE favoured_project_for_user (
    project_id INTEGER NOT NULL REFERENCES project(id),
    user_id INTEGER NOT NULL REFERENCES "user"(id),
    PRIMARY KEY (project_id, user_id)
);

CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    body TEXT NOT NULL,
    user_id INTEGER NOT NULL REFERENCES "user"(id),
    project_id INTEGER NOT NULL REFERENCES project(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE dev_diary (
    id SERIAL PRIMARY KEY,
    body TEXT NOT NULL,
    user_id INTEGER NOT NULL REFERENCES "user"(id),
    project_id INTEGER NOT NULL REFERENCES project(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE physical_face_project_account (
    project_id INT NOT NULL PRIMARY KEY,
    bic VARCHAR(255),
    ras_schot VARCHAR(255),
    kor_schot VARCHAR(255),
    fio TEXT,
    inn VARCHAR(255),
    post_address TEXT,
    passport_series VARCHAR(255),  
    passport_number VARCHAR(255),  
    passport_givenby TEXT,         
    registration_address TEXT,     
    passport_page_with_photo BYTEA, 
    passport_page_with_propiska BYTEA, 
    svid_o_postanovke_na_uchet_phys_litsa BYTEA
);

CREATE TABLE juridical_face_project_account (
    project_id INT NOT NULL PRIMARY KEY,

    bic VARCHAR(255),
    ras_schot VARCHAR(255),
    kor_schot VARCHAR(255),
    fio TEXT,
    inn VARCHAR(255),
    post_address TEXT,

    acts_on_base TEXT,
    represented_by TEXT,
    full_organisation_name TEXT,
    abbreviated_name_of_organisation TEXT,
    ogrn VARCHAR(255),
    kpp TEXT,
    legal_address TEXT,
    actual_address TEXT,

    svid_o_gos_reg_jur_face BYTEA,
    svid_o_post_na_inn BYTEA,
    prot_o_nazn_lica BYTEA,
    uved_o_post_na_usn BYTEA,
    deistv_na_osnovanii BYTEA
);


CREATE TABLE ip_project_account (
    project_id INT NOT NULL PRIMARY KEY,

    bic VARCHAR(255),
    ras_schot VARCHAR(255),
    kor_schot VARCHAR(255),
    fio TEXT,
    inn VARCHAR(255),
    post_address TEXT,

    ie_certificate_series VARCHAR(255),
    ie_certificate_number VARCHAR(255),
    ie_certificate_issued TEXT,
    ogrn VARCHAR(255),
    legal_address TEXT,
    actual_address TEXT,

    passport_page_with_photo BYTEA,
    passport_page_with_propiska BYTEA,
    svid_o_postanovke_na_uchet_phys_litsa BYTEA,
    svid_o_reg_indiv_predp BYTEA,
    uved_o_post_na_usn BYTEA
);

