package com.oze.medicalsystem.core.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:10 PM
 */
public interface AppConstant {

    public interface DateFormatters {
        public SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        public static List<Integer> AGES = List.of(18, 17, 20, 22, 19, 25, 30, 27, 12, 11, 45);
    }

    public interface DownloadFile {
        public String ATTACHMENT_FILENAME = "";
    }

    public interface ResponseCode {
        public String OK = "Success";
        public String CREATED = "Success created";
        public String UNAUTHORIZED = "You are not authorized";
        public String UNKNOWN_STAFF = "Unknown staff trying to access the system. Please enter your UUID";
    }

    public interface Headers {
        public String AUTHORIZATION = "Authorization";
    }
}
