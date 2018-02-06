CREATE OR REPLACE PROCEDURE fk_disabled
IS
  CURSOR c1 IS SELECT * FROM user_cons_columns WHERE constraint_name like 'FK%';
  tbl varchar(30);
  cnst varchar(30);
  stmt varchar(200);

BEGIN
  DBMS_OUTPUT.PUT_LINE('start...');
  FOR rec IN c1 LOOP

    tbl := rec.table_name;
    cnst := rec.constraint_name;
    stmt := 'ALTER TABLE ' || tbl || ' DISABLE CONSTRAINT ' || cnst;

    DBMS_OUTPUT.PUT_LINE(tbl || ' - ' || cnst || ' - ' || stmt);

    execute immediate stmt;
    END LOOP;
  DBMS_OUTPUT.PUT_LINE('...end');
END;
/
