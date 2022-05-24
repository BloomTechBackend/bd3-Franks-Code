package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to book a reservation.
 */
public class BookReservationActivity {

    private ReservationDao   reservationDao;
    private MetricsPublisher metricsPublisher;  // Object to be used to record metrics

    /**
     * Constructs a BookReservationActivity
     * @param reservationDao Dao used to create reservations.
     */
    @Inject
    public BookReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a reservation with the provided details.
     * Increment the BookedReservationCount metric
     *
     * @param reservation Reservation to create.
     * @return
     */
    public Reservation handleRequest(Reservation reservation) {

        // Create a reservation in teh data store
        Reservation response = reservationDao.bookReservation(reservation);

        // Increment the BookedReservationCount metric
        //                         metric-name(enum.name)                   , value , metric-unit
        metricsPublisher.addMetric(MetricsConstants.BOOKED_RESERVATION_COUNT,   1   , StandardUnit.Count);

        // Log the ReservationRevenue metric with the total cost of  the reservation
        // the response from the DAO when creating the reservation contains the total cost of the reservation
        //     as a BigDecimal value which we need to convert to double for addMetric()
        //                         metric-name(enum.name)              , value                                , metric-unit
        metricsPublisher.addMetric(MetricsConstants.RESERVATION_REVENUE, response.getTotalCost().doubleValue(), StandardUnit.None);

        return response;
    }
}
