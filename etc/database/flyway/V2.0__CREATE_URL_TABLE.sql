CREATE TABLE url (
  short_url VARCHAR2(1024) NOT NULL,
  original_url VARCHAR2(1024) NOT NULL,
  CONSTRAINT pk_url PRIMARY KEY(short_url)
);
