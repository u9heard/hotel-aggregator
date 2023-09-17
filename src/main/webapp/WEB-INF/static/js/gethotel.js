

function sendData() {
    var city = document.getElementById('city').value;
    var date_start = document.getElementById('date_start').value;
    var date_end = document.getElementById('date_end').value;

    var data = {
        name: city,
        email: date_start,
        birthdate: date_end
    };
    $.ajax({
        type: 'GET',
        url: '/hotels/hotel_list',
        data: {
            "date_start" : date_start,
            "date_end" : date_end,
            "city" : city,
        },

        success: function(response) {
            // Обработка успешного ответа от сервера
            document.getElementById('list').innerHTML = response;
        },
        error: function(error) {
            // Обработка ошибки
            console.error('Ошибка при выполнении запроса:', error);
        }
    });
}