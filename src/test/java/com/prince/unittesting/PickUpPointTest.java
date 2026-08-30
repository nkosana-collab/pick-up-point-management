package com.prince.unittesting;

import com.prince.model.PickUpPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


/**
 * A well declared PickUpPoint object must have a unique name and an address,
 * It should never take in a stock that exceeds its capacity.
 */
public class PickUpPointTest {

    /**
     * Given that I have a valid new PickUpPointObject with 0 usage
     * When I increase its capacity by 100--
     * Then it should succeed and have a usage of 100 in total.
     */
    @Test
    @DisplayName("Test MIN-BOUNDARY 0")
    void initialPickUpPointZeroWithMaxIntakeTest() {

        // Given:
        PickUpPoint store = new PickUpPoint("vosman", "vosie",0);
        // When:
        boolean result=  store.addUsage(100);
        // Then:
        assertTrue(result);
        assertEquals(100, store.getCurrentUsage());
    }


    /**
     * Given that I have a valid PickUpPoint Object with initial with 1
     * When I increase its capacity by 100-
     * Then it should fail and still have its initial usage of
     */
    @Test
    @DisplayName("Test MIN-BOUNDARY 1")
    void initialPickUpPointOneWithMaxIntakeTest() {

        // Given:
        PickUpPoint store = new PickUpPoint("clewerStation", "clewer", 1);
        // When:
        boolean result = store.addUsage(100);
        // Then:
        assertFalse(result);
        assertEquals(1,store.getCurrentUsage());

    }


    /**
     * Given that I have a valid name and address
     * When I try to instantiate PickUpPoint with other
     * multiple values within the valid range other than those around
     * the boundary--
     * Then I should be allowed to do so.
     */
    @ParameterizedTest(name = "Store: {index} | Usage: {arguments}")
    @ValueSource(ints = {30,50,78,42})
    void multipleValidUsagesShouldInstantiateSuccessfully(int param) {

        // Given && When:
        PickUpPoint store = new PickUpPoint("MockName", "MockAddress", param);

        // Then:
        assertEquals(param, store.getCurrentUsage());
    }


    /**
     * Instantiating a PickUpPoint object with a less than zero usage
     * should throw an IllegalArgumentException.
     */
    @Test
    @DisplayName("Test MIN-BOUNDARY -1")
    void negativeUsageInstantiationShouldFail() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PickUpPoint("Kamajola", "ext4", -1);
        });
    }
}
