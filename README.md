# job4j_cars

Учебный проект: реализация площадки продаж автомобилей с базовым функционалом:
- Регистрация на площадке;
- Размещение, редактирование, удаление созданных объявлений;
- Просмотр всех объявлений;



### Технологии проекта
- Spring Boot 2.5.2;
- PostgreSQL 14;
- Hibernate 5.6.11;
- Java-17;
- Maven 3.x+;
- Checkstyle.

### Для корректной работы приложения необходимо установить:
- Java 17;
- Apache Maven 3.x+;
- PostgreSQL 14.

### Запуск приложения
- Загрузите локально проект;
- В PostgreSQL создайте базу данных с названием ```create database cars```
- В корневой папке проекта выполните команду```mvn install```
- Для запуска проекта используйте команду ```java -jar target/job4j_cars-1.0.jar```
- В браузере перейдите по ссылке http://localhost:8080/index
- Для авторизации в режиме администратора используйте ```логин: admin, пароль: admin```
