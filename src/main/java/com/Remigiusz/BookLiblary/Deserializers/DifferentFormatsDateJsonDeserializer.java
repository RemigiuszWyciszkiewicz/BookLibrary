package com.Remigiusz.BookLiblary.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class DifferentFormatsDateJsonDeserializer extends JsonDeserializer<Long> {

    private Timestamp timestamp;

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        String date = jsonParser.getValueAsString();

        try {
            if (date.length() == 4) {
                Date date1 = new SimpleDateFormat("yyyy").parse(date);
                timestamp = new Timestamp(date1.getTime());
                return timestamp.getTime();
            } else if (date.length() == 7) {
                Date date1 = new SimpleDateFormat("yyyy-MM").parse(date);
                timestamp = new Timestamp(date1.getTime());
                return timestamp.getTime();
            } else {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                timestamp = new Timestamp(date1.getTime());
                return timestamp.getTime();
            }
        }catch (ParseException e) {e.printStackTrace();}

        return Long.valueOf(9999999);
    }
}
