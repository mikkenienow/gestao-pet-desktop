
package gestaopet.classes;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class InputTools {
    
    
    public static void validate(String input,KeyEvent evt){
        if (!input.contains(evt.getKeyChar()+ "")){
            evt.consume();
        }
    }
    
    public static String makeDecimal(String input, KeyEvent evt, String c){
        if(input.isEmpty()){
            return "0,0" + c;
        }
        String output = input;
        input = input.replaceAll(",", "");
        
        if("0123456789".contains(c)){
            input = input + c;
            for(int i = 0; i < input.length(); i++){
                if((input.charAt(i) + "").equals("0")){
                    if(i == 0){
                        input = input.substring(1);
                        break;
                    }
                }
            }
        }
        if(input.length() == 2){
            input = "0" + input;
        }
        
        try {
            output = input.substring(0, input.length()-2) + "," + input.substring(input.length()-2);
        } catch (Exception e) {
        }

        return output;
    }
    
    public static String acceptInteger(String input, KeyEvent evt, String c){
        String output = input;
        if("0123456789".contains(c)){
            input = input + c;
            output = input;
        }
        evt.consume();
        return output;
    }
    
    
    
    public static String getStringValor(double valor) {  
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(valor);
    }
    
    public static String docFormat(String input, KeyEvent evt, String c, boolean cpf){
        
        if(cpf){
            if("0123456789".contains(c) && input.length()<14){
                if(input.length() == 3 || input.length() == 7){
                    input = input + "." + c;
                } else if(input.length() == 11){
                    input = input + "-" + c;
                } else {
                    input = input + c;
                }
            }
        } else {
            if("0123456789".contains(c) && input.length()<18){
                if(input.length() == 2 || input.length() == 6){
                    input = input + "." + c;
                } else if(input.length() == 10){
                    input = input + "/" + c;
                } else if(input.length() == 15){
                    input = input + "-" + c;
                } else {
                    input = input + c;
                }
            }
        }
        
        
        
        
        evt.consume();
        String output = input;
        return output;
    }
    
    
}


