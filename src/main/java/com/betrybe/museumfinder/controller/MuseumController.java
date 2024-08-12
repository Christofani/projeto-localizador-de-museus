package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
