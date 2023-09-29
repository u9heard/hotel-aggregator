

function sendData() {
    let list_block = document.getElementById("list");

    var city = document.getElementById('city').value;
    var date_start = document.getElementById('date_start').value;
    var date_end = document.getElementById('date_end').value;


    $.ajax({
        type: 'GET',
        url: '/hotel_list',
        data: {
            "dateStart" : date_start,
            "dateEnd" : date_end,
            "city" : city,
        },

        success: function(response) {
            list_block.innerHTML = response;
        },
        error: function(error) {
            list_block.innerHTML = error.responseText;
        }
    });
}