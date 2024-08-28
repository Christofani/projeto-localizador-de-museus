package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface museumService;

  /**
   * Instantiates a new Museum controller.
   *
   * @param museumService the museum service
   */
  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Museum> getMuseumById(@PathVariable Long id) {
    Museum museum = museumService.getMuseum(id);
    return ResponseEntity.status(HttpStatus.OK).body(museum);
  }

  /**
   * Create museum response entity.
   *
   * @param newMuseum the museum dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody Museum newMuseum) {
    Museum createdMuseum = museumService.createMuseum(newMuseum);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMuseum);
  }

  /**
   * Gets closest museum.
   *
   * @param latitude      the latitude
   * @param longitude     the longitude
   * @param maxDistanceKm the max distance km
   * @return the closest museum
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumCreationDto> getClosestMuseum(
      @RequestParam(name = "lat") double latitude,
      @RequestParam(name = "lng") double longitude,
      @RequestParam(name = "max_dist_km") double maxDistanceKm) {

    Coordinate coordinate = new Coordinate(latitude, longitude);

    Museum closestMuseum = museumService.getClosestMuseum(coordinate, maxDistanceKm);

    MuseumCreationDto museumDto = new MuseumCreationDto(
        closestMuseum.getName(),
        closestMuseum.getDescription(),
        closestMuseum.getAddress(),
        closestMuseum.getCollectionType(),
        closestMuseum.getSubject(),
        closestMuseum.getUrl(),
        closestMuseum.getCoordinate()
    );
    return ResponseEntity.status(HttpStatus.OK).body(museumDto);
  }
}
