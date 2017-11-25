call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD.BAT has failed - there were errors - breaking work
goto fail

:runbrowser
start "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo running browser has failed - there were errors - breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.