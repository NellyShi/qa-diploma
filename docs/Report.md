## Отчётный документ по итогам тестирования.

### Краткое описание

Проведена автоматизация тестирования web-сервиса по покупке тура двумя способами. Также тестировалось взаимодействие с СУБД и API Банка.

Тестировались два способа оплаты тура:
1. Обычная оплата по дебетовой карте.
1. Уникальная технология: выдача кредита по данным банковской карты.

Заявлена поддержка двух СУБД:
* MySQL
* PostgreSQL

### Количество тест-кейсов

Проведено по 50 автоматизированных тестов для каждой БД.

### Итоги тестирования:

* 40 успешных (80 %)
* 10 не успешных (20 %)

#### Подготовлены отчёты:

* [Отчёт Gradle 1](https://github.com/NellyShi/qa-diploma/blob/master/docs/reports/gradle_report1.png)
* [Отчёт Gradle 2](https://github.com/NellyShi/qa-diploma/blob/master/docs/reports/gradle_report2.png)
* [Отчёт Allure](https://github.com/NellyShi/qa-diploma/blob/master/docs/reports/allure_report_1.png)
* [Отчёт Allure кредит](https://github.com/NellyShi/qa-diploma/blob/master/docs/reports/allure_report_creadit.png)
* [Отчёт Allure купить](https://github.com/NellyShi/qa-diploma/blob/master/docs/reports/allure_report_pay.png)

### Общие рекомендации
* Исправить баги, указаные в [issue](https://github.com/NellyShi/qa-diploma/issues);
