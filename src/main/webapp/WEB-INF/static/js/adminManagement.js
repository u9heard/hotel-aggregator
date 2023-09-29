$(document).ready(function () {
    $(document).on("click", '#showRooms', function () {
        let hotelId = $(this).data("hotel-id");
        showRoomPanel(hotelId);
    });

    $(document).on("click", "#deleteHotelButton", function () {
        let hotelId = $(this).data("hotel-id");
        deleteHotel(hotelId);
    });

    $(document).on("click", "#deleteRoomButton", function () {
        let roomId = $(this).data("room-id");
        let hotelId = $(this).data("hotel-id")
        deleteRoom(roomId).then(function () {
            showRoomPanel(hotelId);
        })
        .catch(function (error) {
            console.error("Произошла ошибка при удалении комнаты:", error);
        });

    });

    $(document).on("click", "#addRoomFormButton", function () {
        let hotelId = $(this).data("hotel-id");
        addRoomForm(hotelId);
    });

    $(document).on("click", "#closeButton", function () {
        let hotelId = $(this).data("hotel-id");
        clearRoomForm(hotelId);
    });

    $(document).on("click", "#sendNewRooms", function () {
        let hotelId = $(this).data("hotel-id");
        sendNewRooms(hotelId).then(function (){
            clearRoomForm(hotelId);
        }).catch(function (error) {

        });

    })
});

function getPanel(url){
    $.ajax({
        type: 'GET',
        url: url,

        success: function(response) {
            document.getElementById("main-container").innerHTML = response;
        },
        error: function(error) {
            console.error('Ошибка при выполнении запроса:', error);
            return "";
        }
    });

    return "";
}

function sendNewRooms(hotel_id){
    const roomData = [];
    const roomsContainer = $("#newRooms"+hotel_id);
    const roomCount = roomsContainer.data("room-count");

    for (let i = 0; i < roomCount; i++) {
        const roomNumber = $(`#room_number_${i}`);
        const price = $(`#price_${i}`);

        if (roomNumber.length && price.length) {
            const room = {
                hotelId: hotel_id,
                roomNumber: roomNumber.val(),
                price: price.val()
            };

            roomData.push(room);
        }
    }

    const jsonData = JSON.stringify(roomData);

    console.log(jsonData);

    return new Promise( function (resolve, reject) {
            $.ajax({
                accept: "application/json",
                type: 'POST',
                url: '/api/rooms',
                data: jsonData,
                contentType: "application/json",

                success: function (response) {
                    resolve()
                },
                error: function (error) {
                    reject();
                    console.error('Ошибка при выполнении запроса:', error);
                }
            })
        }
    )
}

function openAddHotelForm(){
    document.getElementById("addPanel").style.display="block";
}

function showRoomPanel(hotel_id){
    document.getElementById("roomPanel"+hotel_id).style.display="block";
    getExistingRooms(hotel_id);
}

function getExistingRooms(hotel_id){
    $.ajax({
        type: 'GET',
        url: '/rooms/' + hotel_id,

        success: function(response){
            $("#existsRooms" + hotel_id).html(response);
        },

        error: function (error) {
            console.error(error);
        }
    })
}

function addRoomForm(hotel_id){
    const roomContainer = $('#newRooms' + hotel_id);

    const roomCount = parseInt(roomContainer.data("room-count"));

    const roomDiv = $('<div id="newRoom">').html(`
            <label for="room_number">Номер:</label>
            <input type="text" id="room_number_${roomCount}" name="room_number_${roomCount}" />

            <label for="price">Цена:</label>
            <input type="text" id="price_${roomCount}" name="price_${roomCount}" />
    `);

    roomContainer.append(roomDiv);
    roomContainer.data("room-count", roomCount + 1);
}

function clearRoomForm(hotel_id){
    let roomListSelector = $("#newRooms" + hotel_id);
    roomListSelector.empty();
    roomListSelector.data("room-count", 0);
}

function deleteRoom(id){

    return new Promise(function (resolve, reject){
        $.ajax({
            type: 'DELETE',
            url: '/api/rooms/' + id,

            success: function(response) {
                resolve();
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}

function sendNewUser(){
    let name = document.getElementById('username').value;
    let pass = document.getElementById('password').value;
    let role = document.getElementById('role').value;

    let data = {
        "username" : name,
        "password" : pass,
        "role" : role
    }

    $.ajax({
        accept: "application/json",
        type: 'POST',
        url: '/api/users',
        data: JSON.stringify(data),
        contentType: "application/json",

        success: function(response) {
            getPanel("/users");
        },
        error: function(error) {
            console.error('Ошибка при выполнении запроса:', error);
            return "";
        }
    });
}

function sendNewHotel(){
    let name = document.getElementById('hotel_name').value;
    let city = document.getElementById('city').value;

    let data = {
        "name" : name,
        "city" : city
    }

    $.ajax({
        type: 'POST',
        url: '/api/hotels',
        data: JSON.stringify(data),
        contentType: "application/json",

        success: function(response) {
            getPanel("/admin_hotel_list");
        },
        error: function(error) {
            console.error('Ошибка при выполнении запроса:', error);
            return "";
        }
    });
}

function deleteHotel(id){

    $.ajax({
        type: 'DELETE',
        url: '/api/hotels/' + id,

        success: function(response) {
            getPanel("/admin_hotel_list");
        },
        error: function(error) {

            console.error('Ошибка при выполнении запроса:', error);
            return "";
        }
    });
}

function deleteUser(id){
    $.ajax({
        type: 'DELETE',
        url: "/api/users/" + id,
        success: function(response) {
            getPanel("/users");
        },
        error: function(error) {
            console.error('Ошибка при выполнении запроса:', error);
            return "";
        }
    });
}
