package com.prince.unittesting;

import com.prince.model.Volunteer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A well declared Volunteer object must have a valid name and ID,
 * it must have the ability to query and update the database.
 */
public class VolunteerTest {

    private final String URL = "jdbc:sqlite:memory";

    /**
     * Given that I have a valid Volunteer Object with database connection,
     * When I add a new PickUpPoint into the database--
     * Then I should be able to do so.
     */
    @Test
    @DisplayName("Volunteer adds PickUpPoint successfully.")
    void volunteerAddsPickUpPointSuccessfully() {

        // Given:
        Volunteer volunteer = new Volunteer("Tom", "3342", URL);
        // When:
        boolean result = volunteer.addPickUpPoint("Siphosensimbi", "EXT5");
        // Then:
        assertTrue(result);
    }

}
