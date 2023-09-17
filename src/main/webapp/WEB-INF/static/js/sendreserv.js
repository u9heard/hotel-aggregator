$(document).ready(function () {
    $(document).on('click', '#reserve-button', function () {
        var roomId = $(this).data('room-id');
        var dateStart = $('#date_start').val();
        var dateEnd = $('#date_end').val();
        reserveRoom(dateStart, dateEnd, roomId);
    });
});

function reserveRoom(dateStart, dateEnd, roomId){
    $.ajax({
        type: 'POST',
        url: '/reserve',
        data: {
            "date_start" : dateStart,
            "date_end" : dateEnd,
            "room_id" : roomId,
        },

        success: function(response) {
            // window.location.href = 'user/orders';
        },
        error: function(error) {
            console.error('Ошибка при выполнении запроса:', error);
        }
    })
}