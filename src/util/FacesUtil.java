package util;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/** Esta é uma Classe utilitaria que implementa alguns metodos que servirão para ser reutilizados em algumas classes,
*   
* @author silas
* @since 17-08-2016
*/

public class FacesUtil {
	
	/** Este metodo captura o atributo da requisição pelo nome .
	* 	
	* 	@param name, Este name é o nome do atributo que você vai pegar.
	* 	@return retorna o objeto referente aquele name.
	*/
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}
	
	
	/** Este metodo acrescenta qtd dias na data.
	* 	
	* 	@param data, Esta data é a data que você quer que seja acrescida dias nela.
	* 	@param qtd, Este qtd é a quantidade de dias que vocês quer adicionar a data.
	* 	@return retorna a data com os qtd dias acrescentados.
	*/
	public static Date addDia(Date data, int qtd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, qtd);
		return cal.getTime();
		}
}