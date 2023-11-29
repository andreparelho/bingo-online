package com.app.bingoonline.service;

import com.app.bingoonline.model.extds.*;
import com.app.bingoonline.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceImplTest {
    private B b = new B();
    private I i = new I();
    private N n = new N();
    private G g = new G();
    private O o = new O();
    private TicketServiceImpl ticketServiceImpl = new TicketServiceImpl(this.b, this.i, this.n, this.g, this.o);

    @Test
    @DisplayName("This test when return one ticket with 24 index")
    public void testDeveRetornarUmaCartelaComVinteEQuatroNumeros(){
        Map<String, Set<Integer>> ticket = ticketServiceImpl.generateTicket();
        Iterator<Map.Entry<String, Set<Integer>>> iterator = ticket.entrySet().iterator();

        int actual = 0;
        while (iterator.hasNext()) {
            actual += iterator.next().getValue().size();
        }

        int expected = 24;
        assertNotNull(ticket);
        assertEquals(expected, actual);
    }
}
