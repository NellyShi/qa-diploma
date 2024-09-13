echo Choose subd:
echo 1 - psql
echo 2 - mysql
choice /c 12 /m "Choose subd:"
set choice=%errorlevel%

if %choice%==1 (
	gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
)

if %choice%==2 (
	gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
)



