package com.prince.unittesting;


import com.prince.model.Volunteer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A well declared Volunteer object must have a valid name and ID,
 * it must have the ability to query and update the database.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    /**
     * Given that I have a valid Volunteer Object with database connection,
     * When I add a new PickUpPoint into the database--
     * Then I should be allowed to do so and add crates as many times as legal to that PickUpPoint.
     */
    @Test
    void VolunteerAddsCratesMultipleTimesSuccessfully() {
        // Given:
        Volunteer volunteer = new Volunteer("Siyabonga", "99449", URL);
        // When:
        boolean addResult = volunteer.addPickUpPoint("Sbindi", "vosman");
        // The:
        assertTrue(addResult);

        // Multiple additions of crates to the newly added PickUpPoint.
        for(int i = 1; i < 5; i++) {
            assertTrue(volunteer.addCrates("Sbindi",i));
        }
    }
}
