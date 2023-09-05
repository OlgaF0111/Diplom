package ru.netology.sql;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;     //представляет соединение базы данных с реляционной базой данных.
import java.sql.DriverManager;  //JDBC-подключается к любой БД, отслеживает все доступные драйверы и управляет установлением соединений между БД и соответствующим драйвером.
import java.sql.SQLException;


public class SqlHelper {                                                                            //класс - Помощник по Sql
    public static Connection getConnection() throws SQLException {                //метод установки соединения с БД.  Со всеми БД??? с исключением
        String url = System.getProperty("db.url");                                                //Строковый URL-адрес=Получить свойство (url-адрес базы данных)
        String username = System.getProperty("db.user");                                          //имя пользователя=Получить свойство (пользователь базы данных)
        String password = System.getProperty("db.password");                                      //пароль=Получить свойство (пароль базы данных)
        try {                                                                                     //определяет блок кода, в котором может произойти исключение;
            return DriverManager.getConnection(url, username, password);                          //подключения к серверу БД.   методу передаются параметры соединения с БД(адрес, имя, праоль).
        } catch (
                SQLException err) {                                                              //происходит обработка исключения (Исключение обозначающее неполадки, вызванные ошибками во введенных пользователем данных)
            err.printStackTrace();                                                                //метод печати информации относительно исключения(где и в какой строке кода)
        }
        return null;                                                                              //выход при отсутсвии значения (если условие не сработало)
    }

    @SneakyThrows                                                                                // аннотация из Lombok, генерирует обертки для проверяемых исключений
    public static void cleanDb() {                                               //метод очистки базы данных
        val creditRequest = "DELETE FROM credit_request_entity";                                  //запрос на получение кредита=УДАЛИТЬ ИЗ таблицы объекта запроса на получение кредита
        val order = "DELETE FROM order_entity";                                                   //приказ=УДАЛИТЬ ИЗ таблицы объект заказа
        val payment = "DELETE FROM payment_entity";                                               //оплата=УДАЛИТЬ ИЗ таблицы объект платежа
        val runner = new QueryRunner();                                                           // запуск=новый класс, исполнитель запросов
        try (val conn = getConnection();                                                          //определяет блок кода, в котором может произойти исключение
        ) {
            runner.update(conn, creditRequest);                                                   //вызов метода(команда обнови,внеси изменения в строку(..., запрос на получение кредита)
            runner.update(conn, order);                                                           //команда обнови,внеси изменения в строку(..., приказ) ???
            runner.update(conn, payment);                                                         //команда обнови,внеси изменения в строку(..., оплата)
        } catch (
                SQLException exception) {                                                        //происходит обработка исключения (Исключение, которое предоставляет информацию об ошибке доступа к базе данных или других ошибках)
            exception.printStackTrace();                                                          //метод используется чтобы получить информацию относительно исключения(где и в какой строке кода)
        }
    }

    @SneakyThrows
    //Аннотация от Lombok для обработки проверенных исключений
    public static String getStatusCreditRequestEntity() {                         //метод получения ID кредитного запроса
        try (val conn = getConnection();                                                          //определяет блок кода, в котором может произойти исключение //заготовка запроса для аккаунта
            val countStmt = conn.createStatement()) {                                            //Создание Statement для отправки инструкций SQL в базу данных //создает объект оператора JDBC
            val sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";  // SQL= запрос на получение статусов, которые есть внутри таблицы credit_request_entity(ID кредитного запроса),при сортировке результирующего набора в порядке возрастания
            val resultSet = countStmt.executeQuery(sql);                                          //получение выполненных запросов=результирующий запрос в базу SQL
            if (resultSet.next()) {                                                               //Метод используется для перемещения к следующей строке ResultSet, делая ее текущей (двигает курсор)
                return resultSet.getString("status");                                  //Возвращает набор получаемых результатов из таблицы - колонка статус
            }
        } catch (
                SQLException err) {                                                              //происходит обработка исключения (Исключение обозначающее неполадки, вызванные ошибками во введенных пользователем данных)
            err.printStackTrace();                                                                //метод печати информации относительно исключения(где и в какой строке кода)
        }
        return null;                                                                              //выход при отсутсвии значения (если условие не сработало)
    }

    @SneakyThrows
    public static String getStatusPaymentEntity() {                               //метод получения статуса платежного идентификатора
        try (val conn = getConnection();                                                         //определяет блок кода, в котором может произойти исключение
             val countStmt = conn.createStatement()) {                                           //заготовка запроса для аккаунта
            val sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";        //SQL= запрос на получение статусов, которые есть внутри таблицы payment_entity(платежный идентификатор),при сортировки результирующего набора в порядке возрастания
            val resultSet = countStmt.executeQuery(sql);                                         //получение выполненных запросов=результирующий запрос в базу SQL
            if (resultSet.next()) {                                                              //Если получен набор результатов. переход к следующей строке ResultSet, делая ее текущей
                return resultSet.getString("status");                                 //Возвращает набор получаемых результатов из таблицы - колонка статус
            }
        } catch (
                SQLException err) {                                                              //происходит обработка исключения (Исключение обозначающее неполадки, вызванные ошибками во введенных пользователем данных)
            err.printStackTrace();                                                               //метод печати информации относительно исключения(где и в какой строке кода)
        }
        return null;                                                                             //выход при отсутсвии значения (если условие не сработало)
    }
}