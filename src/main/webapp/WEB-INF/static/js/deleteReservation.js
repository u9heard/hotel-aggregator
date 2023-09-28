function deleteReservation(reserveId) {
    if(confirm("Вы уверены?")) {

        $.ajax({
            type: 'DELETE',
            url: '/api/reservations/' + reserveId,
            success: function (response) {
                location.reload();
            },
            error: function (error) {
                console.error('Ошибка при выполнении запроса:', error);
            }
        });
    }
}