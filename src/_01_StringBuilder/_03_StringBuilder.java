package _01_StringBuilder;


public class _03_StringBuilder {
    
    public static String append(String str, char[] characters) {
    	for(int i = 0; i < characters.length; i++) {
    		str = str + characters[i];
    	}
        return str;
    }
    
    public static String reverse(String str) {
    	char[] c = str.toCharArray();
    	for(int i = 0; i < str.length()/2; i++) {
    		char x = c[i];
    		c[i] = c[c.length-i-1];
    		c[c.length-i-1] = x;
    	}
    	str = new String(c);
        return str;
    }
    
    public static String insert(String str, int index, char newChar) {
        return str.substring(0, index) + newChar + str.substring(index);
    }
    
    public static String delete(String str, int startIndex, int endIndex) {
        return str.substring(0, startIndex) + str.substring(endIndex);
    }
}