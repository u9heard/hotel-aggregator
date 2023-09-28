$(document).ready(function () {
    $(document).on('click', '#reserve-button', function () {
        var roomId = $(this).data('room-id');
        var dateStart = $('#date_start').val();
        var dateEnd = $('#date_end').val();
        reserveRoom(dateStart, dateEnd, roomId);
    });
});

function reserveRoom(dateStart, dateEnd, roomId){
    let data = {
        "dateStart": dateStart,
        "dateEnd": dateEnd,
        "roomId": roomId
    }
    $.ajax({
        type: 'POST',
        url: '/api/reservations',
        data: JSON.stringify(data),
        contentType: "application/json",

        success: function(response) {
            alert(response);
            // window.location.href = 'user/orders';
        },
        error: function(error) {
            alert(error);
            console.error('Ошибка при выполнении запроса:', error);
        }
    })
}