package userapplication.utils;

import com.google.gson.Gson;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


import java.beans.PropertyDescriptor;
import java.util.*;


public class CommonUtils {

    public static String toJson(Object request) {
        Gson gson = new Gson();
        return gson.toJson(request);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateTrackUUID() {
        return UUID.randomUUID().toString().substring(0, 32).toLowerCase();
    }

    public static String generateEightDigitUniqueId() {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        int i = generator.nextInt(10000000) % 10000000;
        java.text.DecimalFormat f = new java.text.DecimalFormat("00000000");
        return f.format(i);
    }

    public static boolean checkListEmpty(List<?> list) {
        return list != null && list.isEmpty();
    }


    public static List<String> getTripTypeKeys(String type) {
        if (type.equalsIgnoreCase("DR"))
            return new ArrayList<>(Arrays.asList("Ongoing", "Return", "RoundTrip"));
        else
            return new ArrayList<>(Collections.singletonList("Ongoing"));
    }

    public static boolean checkIsNullOrEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }

    public static boolean checkIsNullOrEmpty(String[] data) {
        return data == null || data.length <= 0;
    }

    public static boolean checkIsNullOrEmpty(Collection<?> data) {
        return data == null || data.isEmpty();
    }

    public static boolean checkIsNull(Object data) {
        return data == null;
    }

    public static boolean isAlphaNumeric(String data) {
        return data.matches("[A-Za-z0-9]+");
    }

    public static boolean isString(String data) {
        return data.matches("[A-Za-z]+");
    }

    public static boolean isNumber(String data) {
        return data.matches("[0-9]+");
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public static boolean isValidEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\n");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("/^[0]?[789]\\d{9}$/");
    }


}
