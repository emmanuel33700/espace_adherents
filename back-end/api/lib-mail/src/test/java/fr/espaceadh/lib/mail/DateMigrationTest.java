package fr.espaceadh.lib.mail;

import fr.espaceadh.lib.mail.model.mailjet.Message;
import fr.espaceadh.lib.mail.utilitaires.JSON;
import java.time.OffsetDateTime;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DateMigrationTest {
    @Test
    public void mailjetDatesKeepTheirOffsetAndJsonFormat() {
        JSON json = new JSON();
        String date = "2026-10-26T15:30:00+01:00";
        Message message = json.deserialize("{\"ArrivedAt\":\"" + date + "\"}", Message.class);
        assertEquals(OffsetDateTime.parse(date), message.getArrivedAt());
        Message roundTrip = json.deserialize(json.serialize(message), Message.class);
        assertEquals(message.getArrivedAt(), roundTrip.getArrivedAt());
    }
}
