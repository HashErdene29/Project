package cs489.project.dto.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        int numberOfPeople,
        String reserveDate,
        String reserveTime
) {
}
