public class Test {
    public static void main(String[] args) {
    	char[][] copy = new char[2][2];
    	
    	 for(int i = 0; i < copy.length; i++) {
	        	for (int j = 0; j < copy[i].length; j++) {	        		
	        			copy[i][j] = '1';   
	        			System.out.println(copy[i][j]);
	        	}
	        	
	        }
    	
  }
}