DROP TABLE IF EXISTS news;

CREATE TABLE news (
  id VARCHAR PRIMARY KEY,
  title VARCHAR NOT NULL,
  description CLOB NOT NULL,
  date timestamp DEFAULT current_timestamp(),
  updated_date timestamp DEFAULT current_timestamp(),
  image_url VARCHAR NOT NULL
);

ALTER TABLE news
ALTER COLUMN description SET DATA TYPE VARCHAR(10000);

DROP TABLE IF EXISTS system_param;

CREATE TABLE system_param (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  param_key VARCHAR NOT NULL,
  value VARCHAR NOT NULL,
  data_type VARCHAR NOT NULL,
  creation_date timestamp DEFAULT current_timestamp(),
  updated_date timestamp DEFAULT current_timestamp()
);