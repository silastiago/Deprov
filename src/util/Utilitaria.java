package util;

import java.util.Date;
import java.util.Calendar;

public class Utilitaria {
	public Date addDia(Date data, int qtd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, qtd);
		return cal.getTime();
		}
}
