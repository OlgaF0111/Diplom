## Запуск приложения  
### Подготовительный этап  
1.	Установить IntelliJ IDEA; Java 11  
2.	Установить Docker Desktop;  
3.	Установить Github  
4.	Установить Google Chrome  
5.	Клонировать репозиторий с Github (https://github.com/OlgaF0111/Diplom)
   
###	Запустить docker-контейнер с СУБД MySQL и PostgreSQL, командой в терминале:
   
docker-compose up --build  

###	В новом терминале запустить SUT:  
*для mysql*   
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar  

*для postgresql*    
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar

###	Запустить авто-тесты  
*для mysql*    
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"  

*для postgresql*    
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"  
