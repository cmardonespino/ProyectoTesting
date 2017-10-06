/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladordecredito;

/**
 *
 * @author Carlos
 */

import java.util.*;

public class SimuladorDeCredito {

    public static String quitaEspacios(String rut){
        String run;
        run = (rut.replaceAll("\\s",""));
        return run;
    }
    
    public static String sacapuntos(String rut){
        String run;
        run = (rut.replaceAll("\\.",""));
        return run;
    }
    
    public static String sacaComas(String rut){
        String run;
        run = (rut.replaceAll("\\,",""));
        return run;
    }
    
    public static String quitarGuiones(String rut){
        String run;
        run = (rut.replaceAll("\\-",""));
        return run;
    }
    
    public static boolean digitoVerificador(String rut) {
 
        boolean validacion = false;
        try {
            int ah = rut.length();
            rut =  rut.toUpperCase();
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
       }
    
    public static Boolean verificarCaracteres(String rut){
        try{
            Integer.parseInt(rut);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     *
     * @param rut
     * @return
     */
    public static Boolean validadorRut(String rut){
        boolean mutex = true;
	String run = rut;
	run = quitaEspacios(run);
        run = quitarGuiones(run);
		
	String tmpRut2;
	String tmpDv;
	String dv;
        
	tmpRut2 = sacapuntos(run);
        if(verificarCaracteres(tmpRut2)==true){
            if(tmpRut2.length()>9 || tmpRut2.length()<3){
                System.out.println("ERROR: El largo máximo del RUT es de 10 carácteres y el mínimo es de 3 carácteres");
                return false;
            }else{
                if(digitoVerificador(tmpRut2)==true){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            System.out.println("El RUT no debe tener letras");
            return false;
        }
    }
    
    public static Boolean validateMontoLiquido(String monto){
	String montoOriginal = monto;
        String montoAux = sacapuntos(monto);
        montoAux = sacaComas(montoAux);
        if(verificarCaracteres(montoAux)==true){
            if (Integer.parseInt(montoAux) >= 500000 && Integer.parseInt(montoAux) <= 100000000){
		return true;
            }else{
                System.out.println("ERROR: El monto no debe tener letras");
                return false;
            }
        }
	//monto = monto.replace("/\./g","");
	
	//$("#montoLiquido").val(montoOriginal);
	return false;

    }
    
    public static void Menu(){
        System.out.print("RUT: ");
        Scanner entradaEscaner = new Scanner (System.in);
        String run = entradaEscaner.nextLine ();
        if(validadorRut(run)==true){
            System.out.print("Monto Líquido Solicitado: ");
            String monto = entradaEscaner.nextLine();
            if(validateMontoLiquido(monto)==true){
                System.out.println("WENA");
            }else{
                System.out.println("Vuelva a intentarlo");
            }
        }else{
            System.out.println("Vuelva a intentarlo");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu();
    }
    
}
