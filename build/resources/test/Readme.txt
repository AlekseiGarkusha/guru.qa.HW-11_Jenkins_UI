Что бы запустить тесты с отдельными параметрами через консоль необходимо:
1. В build.gradle добавить   tasks.withType(Test) { systemProperties System.getProperties()
2. Создать отделье тесты для запуска
3. в консоли ./gradlew clean test -Dname=alex -Denvironment=prod -Dbrowser=yandex