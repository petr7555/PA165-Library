npm run build
rm -r ..\resources\build
cp -r .\build ..\resources
cd ..\..\..\
mvn spring-boot:run -Pdev
