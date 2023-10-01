package org.zotov.hotel_aggregator.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zotov.hotel_aggregator.dto.reservation.ReservationRequestDTO;
import org.zotov.hotel_aggregator.dto.reservation.ReservationResponseDTO;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.repositories.HotelArchiveRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.HotelRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.RoomArchiveRepository;
import org.zotov.hotel_aggregator.interfaces.repositories.RoomRepository;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.models.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationMapperService implements ModelMapperService<ReservationRequestDTO, ReservationResponseDTO, Reservation> {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final HotelArchiveRepository hotelArchiveRepository;
    private final RoomArchiveRepository roomArchiveRepository;

    @Autowired
    public ReservationMapperService(HotelRepository hotelRepository, RoomRepository roomRepository, HotelArchiveRepository hotelArchiveRepository, RoomArchiveRepository roomArchiveRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.hotelArchiveRepository = hotelArchiveRepository;
        this.roomArchiveRepository = roomArchiveRepository;
    }

    @Override
    public Reservation RequestDTOtoModel(ReservationRequestDTO reservationRequestDTO) {

        BigDecimal price = roomRepository.findById(
                reservationRequestDTO.getRoomId()
        ).orElseThrow(() -> new ModelNotFoundException("Room not found")).getPrice();

        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(reservationRequestDTO.getDateStart(), reservationRequestDTO.getDateEnd())));

        return new Reservation(null, reservationRequestDTO.getUserId(), reservationRequestDTO.getRoomId(),
                reservationRequestDTO.getDateStart(), reservationRequestDTO.getDateEnd(), totalPrice);
    }

    @Override
    public ReservationResponseDTO modelToResponseDTO(Reservation reservation) throws ModelNotFoundException {
        try {
            ReservationResponseDTO responseDTO = new ReservationResponseDTO();
            Room reservedRoom = this.roomRepository.findById(reservation.getRoomId()).orElse(null);
            Hotel hotel;
            if(reservedRoom == null){
                ArchivedRoom archivedRoom = this.roomArchiveRepository.getRoomByIdFromArchive(reservation.getRoomId()).orElseThrow(() -> new ModelNotFoundException("Room not found in archive"));
                reservedRoom = archivedRoomToRoom(archivedRoom);
                ArchivedHotel archivedHotel = this.hotelArchiveRepository.getHotelByIdFromArchive(reservedRoom.getHotelId()).orElseThrow(() -> new ModelNotFoundException("Hotel not found in archive"));
                hotel = this.archivedHotelToHotel(archivedHotel);
                responseDTO.setDescription("This hotel or room has been removed");
            }
            else {
                hotel = this.hotelRepository.findById(reservedRoom.getHotelId()).orElseThrow(() -> new ModelNotFoundException("Hotel not found"));
            }
            responseDTO.setId(reservation.getId());
            responseDTO.setCity(hotel.getCity());
            responseDTO.setHotelName(hotel.getName());
            responseDTO.setDateStart(reservation.getDateStart());
            responseDTO.setDateEnd(reservation.getDateEnd());
            responseDTO.setTotalPrice(reservation.getTotalPrice());
            responseDTO.setRoomNumber(reservedRoom.getRoomNumber());
            return responseDTO;

        } catch (ModelNotFoundException e){
            e.printStackTrace();
            return new ReservationResponseDTO(
                    reservation.getId(), "", "",
                    reservation.getDateStart(), reservation.getDateEnd(), 0,
                    reservation.getTotalPrice(), "This hotel has been removed. Contact support");
        }

    }

    private Hotel archivedHotelToHotel(ArchivedHotel archivedHotel){
        return new Hotel(archivedHotel.getHotelId(), archivedHotel.getName(), archivedHotel.getCity());
    }

    private Room archivedRoomToRoom(ArchivedRoom room){
        return new Room(room.getId(), room.getHotelId(), room.getRoomNumber(), room.getPrice());
    }
}
