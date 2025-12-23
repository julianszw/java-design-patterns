@echo off
echo ========================================
echo Compilando SingletonLogger...
echo ========================================

javac -d out/production/SingletonLogger -sourcepath src src/main/Main.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Compilacion exitosa!
    echo ========================================
) else (
    echo.
    echo ========================================
    echo Error en la compilacion
    echo ========================================
    pause
    exit /b 1
)

pause

