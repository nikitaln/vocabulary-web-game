$(function(){

    //нажатие КНОПКИ ПРОВЕРКИ слова
    $('#btn-check-word').click(function(){

        var ruWord = $('.translate-input-form').serialize();

        $.ajax({
            url: '/check',
            method: 'post',
            dataType: 'text',
            data: ruWord,
            success: function(response){

                $('#input-ru-word').val('');

                if (response === 'true') {

                    $('#result-translate').text('Верно');

                } else {

                $('#result-translate').text('Неверно');

                }
            }
        });
    });

    //кнопка СЛЕДУЮЩЕГО СЛОВА
    $('#btn-next-word').click(function(){
           //вызов  функции
        getNewWord();

    });

    //функция получения нового слова, которую мы можем вызвать
    const getNewWord = function() {
            $.ajax({
                url: '/newWord',         /* Куда пойдет запрос */
                method: 'get',             /* Метод передачи (post или get) */
                dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
        //        data: {text: 'Текст'},     /* Параметры передаваемые в запросе. */
                success: function(enWordDTO){   /* функция которая будет выполнена после успешного запроса.  */
                                              /* В переменной data содержится ответ от index.php. */
                    //передаем JSON из контроллера в Java
//                    alert(data.ruWord);

                    //добавили слово на страницу
                    //$('#word-for-translate').text(enWordDTO.enWord);
                    // Получаем элемент input
                    const inputElement = document.getElementById('id-word');
                    const inputElementForOutputWord = document.getElementById('output-en-word');
                    // Устанавливаем значение
                    inputElement.value = enWordDTO.id;
                    inputElementForOutputWord.value = enWordDTO.enWord;

                }
            });
    }

    //функция проверки перевода слова
    const checkTranslateEnWord = function() {
        let ruWord = $('#TagId').text();    //берем слово из формы
        alert(); //ИД слова
        alert(); //русский перевод
        alert(); //английское слово

    }

});







    //GET-запрос получение нового слова
//        $.ajax({
//            url: '/',         /* Куда пойдет запрос */
//            method: 'get',             /* Метод передачи (post или get) */
//    //        dataType: 'html',          /* Тип данных в ответе (xml, json, script, html). */
//    //        data: {text: 'Текст'},     /* Параметры передаваемые в запросе. */
//            success: function(){   /* функция которая будет выполнена после успешного запроса.  */
//                alert('Get-response');            /* В переменной data содержится ответ от index.php. */
//            }
//        });