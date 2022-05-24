package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity
     * @param reservationDao Dao used to update reservations.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation.
     * Increment the CanceledReservationCount metric
     * Update the ReservationRevenue metric with the total cost of the reservation
     *
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {

        // Remove a Reservation from the data store
        Reservation response = reservationDao.cancelReservation(reservationId);

        // Increment the CanceledReservationCount metric
        //                         metric-name(enum.name)       , value , metric-unit
        metricsPublisher.addMetric(MetricsConstants.CANCEL_COUNT,   1   , StandardUnit.Count);

        // Update the ReservationRevenue metric with the total cost of  the reservation
        // the response from the DAO when cancelling the reservation contains the total cost of the
        //                   reservation as a negative value
        //                   as a BigDecimal value which we need to convert to double for addMetric()
        //                         metric-name(enum.name)              , value                                , metric-unit
        metricsPublisher.addMetric(MetricsConstants.RESERVATION_REVENUE, response.getTotalCost().doubleValue(), StandardUnit.None);

        return response;
    }
}
