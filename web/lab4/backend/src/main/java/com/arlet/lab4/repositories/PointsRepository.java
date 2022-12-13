package com.arlet.lab4.repositories;

import com.arlet.lab4.data.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointsRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByOwner(String owner);
}
