import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testAdd() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityC", "CityD", 150, 1100, 1300);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] result = aviaSouls.findAll();
        assertArrayEquals(new Ticket[]{ticket1, ticket2}, result);
    }

    @Test
    public void testSearch() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityC", "CityD", 150, 1100, 1300);
        Ticket ticket3 = new Ticket("CityA", "CityB", 120, 1300, 1500);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] result = aviaSouls.search("CityA", "CityB");
        assertArrayEquals(new Ticket[]{ticket1, ticket3}, result);
    }

    @Test
    public void testSearchAndSortBy() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityC", "CityD", 150, 1100, 1300);
        Ticket ticket3 = new Ticket("CityA", "CityB", 120, 1300, 1500);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Comparator<Ticket> priceComparator = Comparator.comparingInt(Ticket::getPrice);
        Ticket[] result = aviaSouls.searchAndSortBy("CityA", "CityB", priceComparator);

        assertArrayEquals(new Ticket[]{ticket1, ticket3}, result);
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityC", "CityD", 150, 1100, 1300);
        Ticket ticket3 = new Ticket("CityA", "CityB", 120, 1300, 1500);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Comparator<Ticket> timeComparator = Comparator.comparingInt(Ticket::getTimeFrom);
        Ticket[] result = aviaSouls.searchAndSortBy("CityA", "CityB", timeComparator);

        assertArrayEquals(new Ticket[]{ticket1, ticket3}, result);
    }

    @Test
    public void testSearchNoMatch() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityC", "CityD", 150, 1100, 1300);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] result = aviaSouls.search("CityX", "CityY");
        assertEquals(0, result.length);
    }

    @Test
    public void testTicketComparison() {
        Ticket ticket1 = new Ticket("CityA", "CityB", 100, 1200, 1400);
        Ticket ticket2 = new Ticket("CityA", "CityB", 150, 1100, 1300);
        Ticket ticket3 = new Ticket("CityA", "CityB", 120, 1300, 1500);

        assertTrue(ticket1.compareTo(ticket2) < 0);
        assertTrue(ticket2.compareTo(ticket3) > 0);
        assertTrue(ticket1.compareTo(ticket3) < 0);
    }
}