package com.epam.cdp.dao.impl.storageloader.impl;

import com.epam.cdp.dao.impl.storageloader.StorageLoader;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.model.impl.UserImpl;
import com.epam.cdp.util.DateParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yevhenii_Tolstolutsk on 1/3/2017.
 */
public class JsonStorageLoader implements StorageLoader {
    private static final Logger LOGGER = Logger.getLogger(JsonStorageLoader.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> loadStorage(String pathToStorage) throws IOException {
        Map<String, Object> result = new HashMap<>();

        Map<String, LinkedHashMap<String, String>> mapFromJson = mapper.readValue(new File(pathToStorage), Map.class);

        for (Map.Entry<String, LinkedHashMap<String, String>> entry : mapFromJson.entrySet()) {
            Matcher matcher = Pattern.compile("\\d+$").matcher(entry.getKey());
            if (matcher.find() && entry.getValue() instanceof LinkedHashMap) {
                long id = Long.parseLong(matcher.group());
                if (entry.getKey().contains("event")) {
                    addEvent(result, entry, id);
                } else if (entry.getKey().contains("ticket")) {
                    addTicket(result, entry, id);
                } else if (entry.getKey().contains("user")) {
                    addUser(result, entry, id);
                }
            }
        }

        return result;
    }

    /**
     * Puts event to map, if all data is valid.
     *
     * @param result event should be added to this map
     * @param entry  data of event
     * @param id     event id
     */
    private void addEvent(Map<String, Object> result, Map.Entry<String, LinkedHashMap<String, String>> entry, long id) {
        String title = entry.getValue().get("title");
        String dateString = entry.getValue().get("date");
        Date date;
        try {
            date = DateParser.parse(dateString);
            result.put(entry.getKey(), new EventImpl(id, title, date));
        } catch (ParseException e) {
            LOGGER.error(String.format("cannot parse date: %s", dateString));
        }
    }

    /**
     * Puts ticket to map, if all data is valid.
     *
     * @param result ticket should be added to this map
     * @param entry  data of ticket
     * @param id     ticket id
     */
    private void addTicket(Map<String, Object> result, Map.Entry<String, LinkedHashMap<String, String>> entry, long id) {
        String eventIdString = entry.getValue().get("eventId");
        String userIdString = entry.getValue().get("userId");
        String placeString = entry.getValue().get("place");
        if (eventIdString.matches("\\d+") && userIdString.matches("\\d+")
                && placeString.matches("\\d+")) {
            int eventId = Integer.parseInt(eventIdString);
            int userId = Integer.parseInt(userIdString);
            Ticket.Category category = Ticket.Category.valueOf(entry.getValue().get("category"));
            int place = Integer.parseInt(placeString);
            result.put(entry.getKey(), new TicketImpl(id, eventId, userId, category, place));
        }
    }

    /**
     * Puts user to map, if all data is valid.
     *
     * @param result user should be added to this map
     * @param entry  data of user
     * @param id     user id
     */
    private void addUser(Map<String, Object> result, Map.Entry<String, LinkedHashMap<String, String>> entry, long id) {
        String name = entry.getValue().get("name");
        String email = entry.getValue().get("email");
        result.put(entry.getKey(), new UserImpl(id, name, email));
    }
}
