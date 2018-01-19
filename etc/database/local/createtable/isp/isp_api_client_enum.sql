CREATE TABLE isp_api_client_enum (
  client_id NUMBER NOT NULL,
  api_id NUMBER NOT NULL,
  client_domain_identifier VARCHAR(20) NOT NULL,
  client_path VARCHAR(100) NOT NULL,
  client_name VARCHAR(100) NOT NULL,
  create_date DATE NOT NULL,
  CONSTRAINT
    pk_isp_api_client_enum PRIMARY KEY(client_id),
  CONSTRAINT
    uk_isp_api_client_enum UNIQUE(api_id, client_path, client_domain_identifier)
)