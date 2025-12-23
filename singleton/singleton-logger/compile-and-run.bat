@echo off
echo ========================================
echo Compilando y ejecutando SingletonLogger
echo ========================================
echo.

echo Paso 1: Compilando...
javac -d out/production/SingletonLogger -sourcepath src src/main/Main.java

if %ERRORLEVEL% EQU 0 (
    echo Compilacion exitosa!
    echo.
    echo Paso 2: Ejecutando aplicacion...
    echo ========================================
    echo.
    java -cp out/production/SingletonLogger main.Main
) else (
    echo.
    echo ========================================
    echo Error en la compilacion
    echo ========================================
    pause
    exit /b 1
)

