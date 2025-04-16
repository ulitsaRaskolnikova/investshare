# Invest Share
##### Технологии: Java, Spring Boot, Spring Data JPA, Liquibase, MapStruct, Git

- Платформа для создания, редактирования и хранения пользовательских проектов с потенциальной возможностью сбора инвестиций и пожертвований в будущем.
- Реализован базовый CRUD-функционал для управления проектами, включая поддержку ролей пользователей.
- Используется PostgreSQL в связке с Spring Data JPA для надёжного хранения данных, а миграции базы данных организованы с помощью Liquibase.
- В проекте также применяется MapStruct для удобного преобразования между слоями данных. Архитектура предусматривает последующее расширение — добавление платежных модулей и систем транзакций.
- Восстановление пароля через email.