# Дипломный проект профессии «Тестировщик»
В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

## Тестовая документация
1. [План тестирования](https://github.com/NellyShi/qa-diploma/blob/master/docs/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/NellyShi/qa-diploma/blob/master/docs/Report.md);
1. [Отчет по итогам автоматизации](https://github.com/NellyShi/qa-diploma/blob/master/docs/Summary.md)

## Запуск приложения
### Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
1. Установать и запустить Docker Desktop;
1. Склонировать репозиторий с Github [по ссылке](git@github.com:NellyShi/qa-diploma.git).
1. Открыть проект в IntelliJ IDEA.

### Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
   ```
   docker-compose up
   ```
1. В новой вкладке терминала запустить тестируемое приложение:
   * Для MySQL: 
   ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
   ```
   * Для PostgreSQL: 
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
1. Убедиться в готовности системы. Приложение должно быть доступно по адресу:
```
http://localhost:8080/
```

### Запуск тестов
В новой вкладке терминала запустить тесты:
1. Для MySQL: 
   ```
   gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
   ```
1. Для PostgreSQL: 
   ```
   gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
   ```
