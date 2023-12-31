# **Перечень автоматизируемых сценариев:**  

## Возможность покупки тура двумя способами:  
•	Оплата  дебетовой картой.  
•	Оплата с помощью выдачи кредита по данным карты.  

Предусловия для позитивных и негативных сценариев:  Открыть в браузере страницу: http://localhost:8080/  


## *Позитивные сценарии:*  
Применяются для обоих способов оплаты тура (оплата дебетовой картой и с помощью выдачи кредита по данным карты).    
•	Нажать на кнопку "Купить" - для оплаты дебетовой картой;   
•	Нажать на кнопку "Купить в кредит" – при оплате с помощью выдачи кредита по данным карты;  

    
  1. *Отправка формы со статусом карты "APPROVED"*.     
   
Шаги:  
   1.Ввести в поле "Номер карты" карту  4444 4444 4444 4441.  
   2.Ввести в поле "Месяц" номер текущего месяца.  
   3.Ввести в поле "Год" последние две цифры текущего года.  
   4.Ввести в поле "Владелец" фамилию и имя на латинице, например Petr Petrov.  
   5.Ввести в поле "CVC" три цифры.  
   6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Появилось всплывающее окно с сообщением "Успешно! Операция одобрена Банком".  

Проверка наличия записи в таблицах БД.  
Ожидаемый результат: В таблицах БД появилась запись о совершенной покупки тура.  


2.	*Отправка формы с введением в поле месяца, следующего за текущим.*  
   
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер месяца следующего за текущим (текущий месяц+1)  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат: Появилось всплывающее окно с сообщением "Успешно! Операция одобрена Банком".   

Проверка наличия записи в таблицах БД.  
Ожидаемый результат: В таблицах БД появилась запись о совершенной покупки тура.  


3.	*Отправка формы с введением в поле года, следующего за текущим.*  
   
Шаги:  
1.Нажать на кнопку "Купить" на главной странице веб-сервиса.  
2.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
3.Ввести в поле "Месяц" номер текущего месяца.  
4.Ввести в поле "Год" последние две цифры года, следующего за текущим (текущий год+1)  
5.Ввести в поле "Владелец" фамилию и имя на латинице.  
6.Ввести в поле "CVC" три цифры.  
7.Нажать кнопку "Продолжить".    
Ожидаемый результат: Появилось всплывающее окно с сообщением "Успешно! Операция одобрена Банком".   

Проверка наличия записи в таблицах БД.  
Ожидаемый результат: В таблицах БД появилась запись о совершенной покупки тура.   



4.	*Отправка формы с введением поля Владельца заглавными буквами.*  
   
Шаги:  
1.Нажать на кнопку "Купить" на главной странице веб-сервиса.  
2.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
3.Ввести в поле "Месяц" номер текущего месяца.  
4.Ввести в поле "Год" последние две цифры текущего года.  
5.Ввести в поле "Владелец" фамилию и имя на латинице заглавными буквами, "PETR PETROV"  
6.Ввести в поле "CVC" три цифры.  
7.Нажать кнопку "Продолжить".  
Ожидаемый результат: Появилось всплывающее окно с сообщением "Успешно! Операция одобрена Банком".  

Проверка наличия записи в таблицах БД.  
Ожидаемый результат: В таблицах БД появилась запись о совершенной покупки тура.  



## *Негативные сценарии:*  


1.	*Отправка формы со статусом карты "DECLINED".*  
   
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4442.    
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат: Появилось всплывающее окно с сообщением "Ошибка! Банк отказал в проведении операции".  

Проверка наличия записи в таблицах БД.  
Ожидаемый результат: В таблицах БД НЕ появилась запись о совершенной покупки тура.  



2. *Отправка формы с пустым полем номера карты*  
   
Шаги:  
1.Поле "Номер карты" оставить пустым.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Номер карты» появилось сообщение об ошибке  "Поле обязательно для заполнения"  


3. *Отправка формы с номером карты из одной цифры.*  
   
Шаги:  
1.Ввести в поле "Номер карты" одну цифру, например "4"  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Номер карты» появилось сообщение об ошибке  "Неверный формат"  


4. *Отправка формы с номером карты из пятнадцати цифр*  
   
Шаги:  
1.Ввести с помощью цифр в поле "Номер карты" карту с номером"4444 4444 4444 444"  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Номер карты» появилось сообщение об ошибке  "Неверный формат"  


5. *Отправка формы где номер карты нули.*  
   
Шаги:  
1.Вести в поле "Номер карты"  "0000 0000 0000 0000"  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Появилось всплывающее окно с сообщением "Ошибка! Банк отказал в проведении операции"  


6. *Отправка формы с произвольным номером карты.*  
   
Шаги:  
1.Ввести в поле "Номер карты" карту "4444 4444 4444 4443"  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Появилось всплывающее окно с сообщением "Ошибка! Банк отказал в проведении операции"  



7. *Отправка формы с пустым полем месяца.*  
   
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Поле "Месяц" оставить пустым.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Месяц» появилось сообщение об ошибке  "Поле обязательно для заполнения"  


8. *Отправка формы с введением даты месяца из одной цифры.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" "1"  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Месяц» появилось сообщение об ошибке  "Неверный формат"  


9. *Отправка формы с нулями в поле Месяц.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц"  "00"  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Месяц» появилось сообщение об ошибке  "Неверно указан срок действия карты".  


10. *Отправка формы с введением несуществующей даты месяца.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" несуществующую дату месяца,"13"  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Месяц» появилось сообщение об ошибке  "Неверно указан срок действия карты". 


11. *Отправка формы  с введением месяца предшествующего текущему.*
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер предыдущего месяца (текущий месяц-1)  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Месяц» появилось сообщение об ошибке  "Срок действия карты истек".  


12. *Отправка формы  с пустым полем Год.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Поле "Год"  оставить пустым.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Год» появилось сообщение об ошибке  "Поле обязательно для заполнения"  


13. *Отправка формы  с введением одной цифры в поле год.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" "1"  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Год» появилось сообщение об ошибке  "Неверный формат"  


14. *Отправка формы  с введением нулевых значений в поле год.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" "00"  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Год» появилось сообщение об ошибке  "Неверно указан срок действия карты".  


15. *Отправка формы  с введением истекшего года выдачи карты.*
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.   
3.Ввести в поле "Год" последние две цифры истекшего срока действия карты (текущий год+6).  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Год» появилось сообщение об ошибке  "Истёк срок действия карты". 


16. *Отправка формы  с введением  года предшествующего текущему.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры предыдущего года (текущий год-1)  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Год» появилось сообщение об ошибке  "Истёк срок действия карты".  


17. *Отправка формы  с введением Владельца на кириллице.*   
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на кириллице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


18. *Отправка формы  с введением одного слова в поле Владельц на латинице.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" ввести одно слово на латинице.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


19. *Отправка формы  с пустым полем Владелец.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Поле "Владелец" пустое.  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Поле обязательно для заполнения"  


20. *Отправка формы  с введением одной буквы в поле Владелец на латинице.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" одну букву на латинице, "F"  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


21. *Отправка формы  с введением в поле Владельца строчными буквами на латинице.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице строчными буквами, "petr petrov"  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"   


22. *Отправка формы  с введением в поле Владельца состоящего из трех слов на латинице*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию, имя и отчество на латинице "Petr Petrovich Petrov"  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


23. *Отправка формы  с введением в поле Владельца максимально-допустимого количества букв на латинице.*  
     
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" максимально-допустимое количество букв на латинице, "FFFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


24. *Отправка формы  с введением цифр в поле Владелец.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя состоящие из цифр, "0123"  
5.Ввести в поле "CVC" три цифры.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


25. *Отправка формы  с введением спецсимволов в поле Владелец.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя состоящие из спецсимволов "$&@%%&#@"  
5.Ввести в поле "CVC" три цифры.
6.Нажать кнопку "Продолжить".
Ожидаемый результат:  Под полем «Владелец» появилось сообщение об ошибке  "Неверный формат"  


26. *Отправка формы  с пустым полем CVC.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Поле "CVC" пустое.  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «CVC» появилось сообщение об ошибке  "Поле обязательно для заполнения"  


27. *Отправка формы  с введением CVC из одной цифры*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" одну цифру, "1".  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «CVC» появилось сообщение об ошибке  "Неверный формат"  


28. *Отправка формы  с введением CVC из двух цифр.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.  
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC" двух цифр, "12"  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «CVC» появилось сообщение об ошибке  "Неверный формат"  


29. *Отправка формы  с введением CVC нулей.*  
    
Шаги:  
1.Ввести в поле "Номер карты" карту с номером 4444 4444 4444 4441.   
2.Ввести в поле "Месяц" номер текущего месяца.  
3.Ввести в поле "Год" последние две цифры текущего года.  
4.Ввести в поле "Владелец" фамилию и имя на латинице.  
5.Ввести в поле "CVC, "000"  
6.Нажать кнопку "Продолжить".  
Ожидаемый результат:  Под полем «CVC» появилось сообщение об ошибке  "Неверный формат"  


