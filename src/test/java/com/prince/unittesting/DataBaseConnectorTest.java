package com.prince.unittesting;

import com.prince.database.DataBaseConnector;
import com.prince.model.PickUpPoint;
import org.junit.jupiter.api.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A valid DataBaseConnector should act as a reliable mediator between
 * the database and whoever authorized to interact with it.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataBaseConnectorTest {

    private DataBaseConnector dataBase;

    /**
     * Each test will have its own clean database that is the exact
     * replica of the project's but in memory of the machine.
     */
    @BeforeEach
    void Start() {
        dataBase = new DataBaseConnector("jdbc:sqlite:memory");
    }

    /**
     * After each test close the connection so that in won't interfere with
     * the start of the next test.
     * N.B also I noticed that when I don't close after each, somehow the next one's
     * connection closes midway.
     */
    @AfterEach
    void close() {
        dataBase.killConnection();
    }

    /**
     * Given that I have Instantiated a valid DatabaseConnector object,
     * When I add a valid PickUpPoint into the database--
     * Then the database should store the added PickUpPoint.
     */
    @Test
    void databaseStorePickUpPointSuccessfully() {

        // Give:
        PickUpPoint store = new PickUpPoint("thubelisha", "soweto", 10);
        dataBase.addPickUpPoint(store);

        // When:
        PickUpPoint resultStore = dataBase.getPickUpPoint("thubelisha");

        // Then:
        assertEquals("thubelisha", resultStore.getNAME());
        assertEquals("soweto", resultStore.getADDRESS());
        assertEquals(10, resultStore.getCurrentUsage());
    }


    /**
     * Given that I have added a PickUpPoint into the database,
     * When I want to update the usage of that database--
     * Then I should be able to update the usage of that particular PickUpPoint.
     */
    @Test
    void dataBaseShouldUpdateUsageSuccessfully() {

        // Given:
        PickUpPoint store = new PickUpPoint("homeSafety", "soweto", 0);
        dataBase.addPickUpPoint(store);

        // When:
        dataBase.updatePickUpPoint("homeSafety", 70);

        // Then:
        PickUpPoint resultStore = dataBase.getPickUpPoint("homeSafety");
        assertEquals("homeSafety", resultStore.getNAME());
        assertEquals(70, resultStore.getCurrentUsage());
    }


    /**
     * Given That I have instantiated a valid database instance,
     * When I add multiple PickUpPoints--
     * Then the database should store all the PickUpPoints added.
     */
    @Test
    void databaseStoresMultiplePickUpPointsSuccessfully() {

        // Give && When:
        for(int i = 1; i <= 4; i++){

            String name = "Store" + i;
            String address = "Location" + i;
            dataBase.addPickUpPoint(new PickUpPoint(name, address,0));
        }

        // Then:
        ArrayList<PickUpPoint> totalStores = dataBase.getPickUpPoints();
        assertEquals(4,totalStores.size());


    }
}
