package com.oze.medicalsystem.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oze.medicalsystem.core.constants.AppConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:14 PM
 */
@Slf4j
public class AppUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Type type) {
        try {
            return mapper.writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }

    public static <T> String toJson(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error occurred deserializing object to json string >> {}", e.getMessage());
        }
        return null;
    }

    public static Timestamp parseDate(String dateStr) {
        if (dateStr == null) {
            return new Timestamp(System.currentTimeMillis());
        }
        try {
            return new Timestamp(AppConstant.DateFormatters.dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            log.error("error occurred converting string to date, please check you date format>> {}", e.getMessage());
        }
        return null;
    }

    public static Date parseDateUtil(String dateStr) {
        if (dateStr == null) {
            return new Date();
        }
        try {
            return new Date(AppConstant.DateFormatters.dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            log.error("error occurred converting string to date, please check you date format >> {}", e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(parseDate("2022-04-18"));
    }
}
