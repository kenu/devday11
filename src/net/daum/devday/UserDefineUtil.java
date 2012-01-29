package net.daum.devday;

public class UserDefineUtil {
   /**
    * from: http://bardoj.egloos.com/10681181
 * @param inp
 * @return String
 */
public static String unescape(String inp) {
      String rtnStr = new String();
      char [] arrInp = inp.toCharArray();
      int i;
      for(i=0;i<arrInp.length;i++) {
          if(arrInp[i] == '%') {
              String hex;
              if(arrInp[i+1] == 'u') {    //유니코드.
                  hex = inp.substring(i+2, i+6);
                   i += 5;
               } else {    //ascii
                   hex = inp.substring(i+1, i+3);
                   i += 2;
               }
               try{
                   rtnStr += new String(Character.toChars(Integer.parseInt(hex, 16)));
               } catch(NumberFormatException e) {
                   rtnStr += "%";
                   i -= (hex.length()>2 ? 5 : 2);
               }
           } else {
               rtnStr += arrInp[i];
           }
       }
       return rtnStr;
   }
}
