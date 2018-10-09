package com.example.cinema.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.cinema.model.Reservation;
import com.example.cinema.model.Screening;
import com.example.cinema.service.ReservationDaoImp;
import com.example.cinema.service.ScreeningDaoImp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReservationController {

	@NonNull
	private final ReservationDaoImp reservationDao;

	@NonNull
	private final ScreeningDaoImp screeningDao;

	@NonNull
	private final JavaMailSender sender;

	@RequestMapping(value = "/reservation/", method = POST)
	@Transactional
	public ResponseEntity<?> createReservation(@RequestBody Reservation reservation, UriComponentsBuilder ucBuilder) {

		Optional<Screening> screening = screeningDao.get(reservation.getScreening().getId());
		if (!screening.isPresent() || (screening.get().getFreeSeats() < reservation.getNumberOfTickets())) {
			return new ResponseEntity(CONFLICT);
		}

		screeningDao.updateScreening(screening.get().getId(), reservation.getNumberOfTickets());
		reservationDao.saveReservation(reservation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/reservation/{id}").buildAndExpand(reservation.getId()).toUri());

		try {
			sendEmail(reservation.getId(), reservation.getNumberOfTickets());
		} catch (Exception ex) {
			System.out.println("Error in sending email: " + ex);
		}

		return new ResponseEntity<String>(headers, CREATED);
	}

	@RequestMapping(value = "/reservation/", method = GET)
	@ResponseBody
	public ResponseEntity<List<Reservation>> listAllReservations() {
		List<Reservation> reservations = reservationDao.getAll();
		if (reservations.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(NO_CONTENT);
		}
		return new ResponseEntity<List<Reservation>>(reservations, OK);
	}

	private void sendEmail(long bookingId, int ticketNumber) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo("balatonyigabor@gmail.com");
		helper.setText("Your booking reference number is: " + bookingId + "\nBooked tickets: " + ticketNumber);
		helper.setSubject("Booking");
		sender.send(message);
	}
}
