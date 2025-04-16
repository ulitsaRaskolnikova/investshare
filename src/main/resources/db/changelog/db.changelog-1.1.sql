--changeset ulitsaRaskolnikova:2
CREATE TABLE password_reset_token (
  id BIGSERIAL PRIMARY KEY,
  token VARCHAR(255),
  user_id INT NOT NULL,
  expiry_date TIMESTAMP,

  CONSTRAINT fk_user
      FOREIGN KEY (user_id)
          REFERENCES "user"(id)
          ON DELETE CASCADE
);

