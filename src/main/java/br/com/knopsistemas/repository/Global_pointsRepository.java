package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vividsolutions.jts.geom.Point;

import br.com.knopsistemas.entities.Global_points;



public interface Global_pointsRepository extends JpaRepository<Global_points, Long> {
	 public Global_points findByLocation(Point location);

}
