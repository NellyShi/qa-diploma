echo Choose subd:
echo 1 - psql
echo 2 - mysql
choice /c 12 /m "Choose subd:"
set choice=%errorlevel%

timeout /t 10

echo docker-compose up...
start cmd /k "docker-compose up"

timeout /t 10

if %choice%==1 (
	start cmd /k "java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar ./artifacts/aqa-shop.jar"
)

if %choice%==2 (
	start cmd /k "java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar ./artifacts/aqa-shop.jar"
)

exit