package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  /**
   * Create museum response entity.
   *
   * @param museumDto the museum dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumDto museumDto) {
    Museum newMuseum = museumService.createMuseum(convertToModel(museumDto));
    return new ResponseEntity<>(newMuseum, HttpStatus.CREATED);
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
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam(name = "lat") double latitude,
      @RequestParam(name = "lng") double longitude,
      @RequestParam(name = "max_dist_km") double maxDistanceKm) {

    try {
      Coordinate coordinate = new Coordinate(latitude, longitude);
      Museum closestMuseum = museumService.getClosestMuseum(coordinate, maxDistanceKm);

      MuseumDto museumDto = new MuseumDto(
          closestMuseum.getId(),
          closestMuseum.getName(),
          closestMuseum.getDescription(),
          closestMuseum.getAddress(),
          closestMuseum.getCollectionType(),
          closestMuseum.getSubject(),
          closestMuseum.getUrl(),
          closestMuseum.getCoordinate()
      );

      return new ResponseEntity<>(museumDto, HttpStatus.OK);
    } catch (MuseumNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  private Museum convertToModel(MuseumDto museumDto) {
    Museum museum = new Museum();
    museum.setId(museumDto.id());
    museum.setName(museumDto.name());
    museum.setDescription(museumDto.description());
    museum.setAddress(museumDto.address());
    museum.setCollectionType(museumDto.collectionType());
    museum.setSubject(museumDto.subject());
    museum.setUrl(museumDto.url());
    museum.setCoordinate(museumDto.coordinate());
    return museum;
  }
}
