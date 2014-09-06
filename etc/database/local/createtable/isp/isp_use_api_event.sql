CREATE TABLE isp_use_api_event (
  api_id NUMBER NOT NULL,
  api_client_id NUMBER NOT NULL,
  not_found_api_name VARCHAR(100) NOT NULL,
  tat NUMBER NOT NULL,
  event_date DATE NOT NULL
)