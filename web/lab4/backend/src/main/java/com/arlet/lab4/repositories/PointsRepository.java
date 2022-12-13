package com.arlet.lab4.repositories;

import com.arlet.lab4.data.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Point, Long> {
}
