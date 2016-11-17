
public class test {
	
	public static void main(String args[]){
		String ash = "Enter to win a brand new  from  and XavierWoodsPHD!";
		String space = "[ ]+";
		String[] tokens = ash.split(space);
		
		for(int i=0;i<tokens.length;i++){
			System.out.println(tokens[i]);
		}
	}

}
