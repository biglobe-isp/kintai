@echo off
set BIN_DIR=%~dp0
set DATA_FILE=%BIN_DIR%\bobio\%1
rem type %DATA_FILE%
FOR /F "delims=" %%a IN (%DATA_FILE%) DO (
   @ECHO %%a
)