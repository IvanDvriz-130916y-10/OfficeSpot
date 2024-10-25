package ru.officespot.DemoOfficeSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.officespot.DemoOfficeSpot.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}