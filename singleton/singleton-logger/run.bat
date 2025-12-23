@echo off
echo ========================================
echo Iniciando SingletonLogger GUI...
echo ========================================
echo.

java -cp out/production/SingletonLogger main.Main

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ========================================
    echo Error al ejecutar la aplicacion
    echo ========================================
    pause
)

