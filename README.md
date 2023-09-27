# Дипломный проект по профессии «Тестировщик»  
  Проект представляет собой комплексное автоматизированное тестирование приложения по покупке тура через веб-сервис. Купить тур можно двумя способами:  
•	оплата с помощью дебетовой карты  
•	выдача кредита по данным банковской карты  
  Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:  
•	сервису платежей (Payment Gate)  
•	кредитному сервису (Credit Gate)  
  Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно ли он был совершён. Данные карт при этом не сохраняются.  
## Prerequisites  
1.	Установить IntelliJ IDEA Community Edition; Java 11  
2.	Установить Docker Desktop;  
3.	Установить Github  
4.	Установить Google Chrome

## Установка и запуск  
*Инструкция по запуску с поддержкой MySQL*    
склонировать репозиторий с помощью команды:  git clone https://github.com/OlgaF0111/Diplom  
Открыть склонированный проект в Intellij IDEA  
Запустить docker-контейнер командой: docker-compose up --build  
Запустить SUT с поддержкой MySQL в новом терминале, командой:   java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar  
Запустить тесты с MySQL в новом терминале, командой: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"  

*Инструкция по запуску с поддержкой PostgreSQL*  
склонировать репозиторий с помощью команды:  git clone https://github.com/OlgaF0111/Diplom  
Открыть склонированный проект в Intellij IDEA  
Запустить docker-контейнер командой: docker-compose up --build  
Запустить SUT с поддержкой PostgreSQL в новом терминале, командой:   java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar  
Запустить тесты с PostgreSQL в новом терминале, командой:   ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"  

Отчет Allure формируется автоматически после прохождения авто-тестов и открывается в браузере кликом по allureServe в папке verification.  

*Остановка и удаление контейнеров.*  
Остановить работу SUT сочетанием клавиш: Ctrl + C   
Для остановки и удаление docker-контейнера:  
docker-compose stop  - остановка контейнера  
docker-compose down  - удаление контейнера  

## Лицензия  
Google Chrome - распространяется бесплатно;  
IntelliJ IDEA Community Edition,  Github, Docker Desktop - бесплатный инструмент разработки;  

## Документация  
[план автоматизации тестирования](https://github.com/OlgaF0111/Diplom/blob/main/documentation/plan.md)    
[тест-кейсы](https://github.com/OlgaF0111/Diplom/blob/main/documentation/test%20case.md)    
[отчет о проведенном тестировании](https://github.com/OlgaF0111/Diplom/blob/main/documentation/Report.md)   
[отчет о проведенной автоматизации](https://github.com/OlgaF0111/Diplom/blob/main/documentation/Summary.md)     

