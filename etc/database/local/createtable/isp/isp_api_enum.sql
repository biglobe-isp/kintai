CREATE TABLE isp_api_enum (
  api_id NUMBER NOT NULL,
  api_path VARCHAR(100) NOT NULL,
  api_name VARCHAR(100) NOT NULL,
  create_date DATE NOT NULL,
  CONSTRAINT pk_isp_api_enum PRIMARY KEY(api_id)
)