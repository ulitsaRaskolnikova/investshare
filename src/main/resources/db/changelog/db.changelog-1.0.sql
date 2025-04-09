--changeset ulitsaRaskolnikova:1
CREATE TYPE project_type AS ENUM ('phys', 'jur', 'ip');

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE,
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
     type project_type,
     quick_peek TEXT,
     quick_peek_picture BYTEA,
     poll_address TEXT,
     content TEXT,
     is_public BOOLEAN DEFAULT TRUE,
     is_completed BOOLEAN DEFAULT FALSE,
     current_money DECIMAL(34, 2),
     wanted_money DECIMAL(34, 2),
     duration INTEGER DEFAULT 30,
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
    project_id INTEGER PRIMARY KEY REFERENCES project(id),
    BIC INTEGER,
    ras_schot INTEGER,
    kor_schot INTEGER,
    FIO TEXT,
    INN INTEGER,
    passport_series INTEGER,
    passport_number INTEGER,
    passport_givenby TEXT,
    registration_address TEXT,
    post_address TEXT,
    passport_page_with_photo BYTEA,
    passport_page_with_propiska BYTEA,
    svid_o_postanovke_na_uchet_phys_litsa BYTEA
);

CREATE TABLE juridical_face_project_account (
    project_id INTEGER PRIMARY KEY REFERENCES project(id),
    acts_on_base TEXT,
    position TEXT,
    BIC INTEGER,
    ras_schot INTEGER,
    kor_schot INTEGER,
    FIO TEXT,
    full_organisation_name TEXT,
    short_organisation_name TEXT,
    INN INTEGER,
    OGRN INTEGER,
    KPP TEXT,
    jur_address TEXT,
    fact_address TEXT,
    post_address TEXT,
    svid_o_registratsii_jur_litsa BYTEA,
    svid_o_postanovke_na_nalog_uchet BYTEA,
    protocol_o_nasznachenii_litsa BYTEA,
    USN BYTEA,
    ustav BYTEA
);

CREATE TABLE ip_project_account (
    project_id INTEGER PRIMARY KEY REFERENCES project(id),
    BIC INTEGER,
    ras_schot INTEGER,
    kor_schot INTEGER,
    FIO TEXT,
    ip_svid_serial INTEGER,
    ip_svid_number INTEGER,
    ip_svid_givenby TEXT,
    INN INTEGER,
    OGRN INTEGER,
    jur_address TEXT,
    fact_address TEXT,
    post_address TEXT,
    svid_o_postanovke_na_nalog_uchet BYTEA,
    ip_passport_photo_page BYTEA,
    ip_passport_propiska BYTEA,
    USN BYTEA,
    OGRNIP BYTEA
);
