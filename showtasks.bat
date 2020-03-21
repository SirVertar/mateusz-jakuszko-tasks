call gradlew build

if "%ERRORLEVEL%" == "0" goto browser
echo GRADLEW BUILD has errors - breaking work

:browser
start http://localhost:8080/crud/v1/task/getTasks