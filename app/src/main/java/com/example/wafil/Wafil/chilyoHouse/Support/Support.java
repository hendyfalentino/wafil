package com.example.wafil.Wafil.chilyoHouse.Support;

public class Support {
    public static String rupiahFormat(String format){
        int intFormat = Integer.parseInt(format);
        String pattern;

        if(intFormat <= 9999){         return format.replaceAll("(\\d{1})(\\d{3})","$1.$2"); }
        else if(intFormat <= 99999){   return format.replaceAll("(\\d{2})(\\d{3})","$1.$2"); }
        else if(intFormat <= 999999){  return format.replaceAll("(\\d{3})(\\d{3})","$1.$2"); }
        else if(intFormat <= 9999999){ return format.replaceAll("(\\d{1})(\\d{3})(\\d{3})","$1.$2.$3"); }
        return "";
    }
}
