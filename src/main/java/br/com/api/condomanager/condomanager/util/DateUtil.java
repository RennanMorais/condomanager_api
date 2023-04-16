package br.com.api.condomanager.condomanager.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {

    public static Date toDate(String data) {
        Calendar c = null;

        if(data != null) {
            int dia = Integer.valueOf(data.substring(0, 2));
            int mes = Integer.valueOf(data.substring(3, 5));
            int ano = Integer.valueOf(data.substring(6, 10));
            c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, dia);
            c.set(Calendar.MONTH, mes - 1);
            c.set(Calendar.YEAR, ano);
        }

        return c.getTime();
    }

    public static String dateToString(Date data) {
        String dataFormatada = "";

        if(data != null) {
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
            dataFormatada = simpleDate.format(data);;
        }
        return dataFormatada;
    }

}
