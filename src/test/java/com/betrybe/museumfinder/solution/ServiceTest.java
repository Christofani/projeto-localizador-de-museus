package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceTest {

  @Autowired
  private MuseumService museumService;

  @Autowired
  private MuseumFakeDatabase museumFakeDatabase;

  @BeforeEach
  public void setUp() {
    // Clear the museum database before each test
    museumFakeDatabase.getAllMuseums()
        .forEach(museum -> museumFakeDatabase.deleteMuseum(museum.getId()));
  }

  @Test
  public void shouldCreateMuseumSuccessfully() {
    // Arrange
    Museum museum = new Museum();
    museum.setName("Museu de Arte Moderna");
    museum.setCoordinate(new Coordinate(-23.561684, -46.655981));

    // Act
    Museum savedMuseum = museumService.createMuseum(museum);

    // Assert
    assertNotNull(savedMuseum);
    assertEquals("Museu de Arte Moderna", savedMuseum.getName());
    assertNotNull(savedMuseum.getId()); // Ensure an ID was assigned
  }

  @Test
  public void shouldThrowInvalidCoordinateExceptionForInvalidMuseumCoordinate() {
    // Arrange
    Museum museum = new Museum();
    museum.setName("Museu de Arte Moderna");
    museum.setCoordinate(new Coordinate(-100.0, 200.0)); // Invalid coordinates

    // Act & Assert
    assertThrows(InvalidCoordinateException.class, () -> museumService.createMuseum(museum));
  }

  @Test
  public void shouldFindClosestMuseumSuccessfully() {
    // Arrange
    Coordinate userLocation = new Coordinate(-23.561684, -46.655981);
    Double maxDistance = 10.0;
    Museum museum = new Museum();
    museum.setName("Museu de História Natural");
    museum.setCoordinate(userLocation);

    // Save museum to the database
    museumService.createMuseum(museum);

    // Act
    Museum closestMuseum = museumService.getClosestMuseum(userLocation, maxDistance);

    // Assert
    assertNotNull(closestMuseum);
    assertEquals("Museu de História Natural", closestMuseum.getName());
  }

  @Test
  public void shouldThrowMuseumNotFoundExceptionForFarAwayLocation() {
    // Arrange
    Coordinate farAwayLocation = new Coordinate(-90.0, 0.0);
    Double maxDistance = 1.0;

    // Act & Assert
    assertThrows(MuseumNotFoundException.class,
        () -> museumService.getClosestMuseum(farAwayLocation, maxDistance));
  }

  @Test
  public void shouldGetMuseumSuccessfully() {
    // Arrange
    Museum museum = new Museum();
    museum.setName("Museu de Arte Moderna");
    museum.setCoordinate(new Coordinate(-23.561684, -46.655981));

    // Save museum to the database
    Museum savedMuseum = museumService.createMuseum(museum);

    // Act
    Museum retrievedMuseum = museumService.getMuseum(savedMuseum.getId());

    // Assert
    assertNotNull(retrievedMuseum);
    assertEquals(savedMuseum.getId(), retrievedMuseum.getId());
    assertEquals("Museu de Arte Moderna", retrievedMuseum.getName());
    assertEquals(new Coordinate(-23.561684, -46.655981), retrievedMuseum.getCoordinate());
  }

  @Test
  public void shouldThrowMuseumNotFoundExceptionForNonExistentMuseum() {
    // Arrange
    Long nonExistentId = 999L;

    // Act & Assert
    assertThrows(MuseumNotFoundException.class,
        () -> museumService.getMuseum(nonExistentId));
  }
}
