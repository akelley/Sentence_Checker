import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SentenceMaker{
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);		
		System.out.print("Please enter a string: ");
		String str = br.readLine();
		
		char[] sample = new char[str.length()];
		for(int i = 0; i < str.length(); i++){
			sample[i] = str.charAt(i);
		}
		
		int[] startsAt = new int[100];
		String[] phrases = new String[100];
		for(int i = 0; i < 100; i++){
			phrases[i] = "";
			startsAt[i] = -1;
		}
		
		int counter = 0;
		for(int i = 0; i < sample.length; i++){
			if(sample[i] == 'a'){
				phrases[counter] =  "" + sample[i];
				startsAt[counter] = i;
				counter++;
			
				if(i+1 < sample.length && (sample[i+1] == 'c')){
					phrases[counter] = "" + sample[i] + "" + sample[i+1];
					startsAt[counter] = i;
					counter++;
				}
				
				else if(i+3 < sample.length && (sample[i+1] == 'b' && sample[i+2] == 'c' && sample[i+3] == 'd')){
					phrases[counter] = sample[i] + "" + sample[i+1] + "" + sample[i+2] + "" + sample[i+3];
					startsAt[counter] = i;
					counter++;
				}
			}
			
			else if(i+1 < sample.length && (sample[i] == 'b' && sample[i+1] == 'c')){
				phrases[counter] = sample[i] + "" + sample[i+1];
				startsAt[counter] = i;
				counter++;
				
				if(i+2 < sample.length && (sample[i+2] == 'd')){
					phrases[counter] = sample[i] + "" + sample[i+1] + "" + sample[i+2];
					startsAt[counter] = i;
					counter++;
				}
			}
			
			else if(sample[i] == 'c'){
				phrases[counter] = "" + sample[i];
				startsAt[counter] = i;
				counter++;
					
				if(i+2 < sample.length && (sample[i+1] == 'd' && sample[i+2] == 'e')){
					phrases[counter] = sample[i] + "" + sample[i+1] + sample[i+2];
					startsAt[counter] = i;
					counter++;
				}
			}
			
			else if(i+2 < sample.length && (sample[i] == 'd' && sample[i + 1] == 'e' && sample[i + 2] == 'f')){
				phrases[counter] = sample[i] + "" + sample[i + 1] + sample[i + 2];
				startsAt[counter] = i;
				counter++;
			}
			
			else if(sample[i] == 'e'){
				phrases[counter] = "" + sample[i];
				startsAt[counter] = i;
				counter++;
			}
			
			else if(i+1 < sample.length && (sample[i] == 'f' && sample[i + 1] == 'g')){
				phrases[counter] = sample[i] + "" + sample[i+1];
				startsAt[counter] = i;
				counter++;
			}
			
			else if(sample[i] == 'g'){
				phrases[counter] = "" + sample[i];
				startsAt[counter] = i;
				counter++;
			}
				
			else if(sample[i] == 'h'){
				phrases[counter] = sample[i] + " ";
				startsAt[counter] = i;
				counter++;
					
				if(i+2 < str.length() && (sample[i + 1] == 'i' && sample[i + 2] == 'j')){
					phrases[counter] = sample[i] + "" + sample[i + 1] + sample[i + 2];
					startsAt[counter] = i;
					counter++;
				}
			}
	
			else if(i+1 < sample.length && (sample[i] == 'i' && sample[i + 1] == 'j')){ 
				phrases[counter] = sample[i] + "" + sample[i + 1];
				startsAt[counter] = i;
				counter++;
			}
		}
		
		int[] howMany = new int[counter];
		for(int i = 0; i < counter; i ++)	// how many are at the first index
			howMany[startsAt[i]]++;
		
		int total = 1;
		for(int i = 0; i < counter; i++){
			if(howMany[i] != 0)
				total *= howMany[i]; 
		}	
		total += howMany.length;
		
		String[] results = new String[total];	
		int[] spaces = new int[total];
		for(int i = 0; i < total; i++){
			spaces[i] = 0;
			results[i] = "";
		}
		
		int count = 0;
		for(int i = 0; i < howMany[0]; i++){
			results[count] = phrases[i];
			results[++count] = phrases[i];
			
			for(int j = howMany[0]; j < counter; j++){
				int xx = 0;
				while(xx <= count){
					if(startsAt[j] == results[xx].length() - spaces[xx]){
						results[xx] += " " + phrases[j];
						spaces[xx]++;
						break;
					}
						
					else if(startsAt[j] == results[xx].length() - spaces[xx]){
						results[xx] += " " + phrases[j];
						spaces[xx]++;
						break;
					}
					xx++;
				}
			}
			count++;
		}
		
		for(int i = 0; i < total; i++){
			int aCount = 0;
			int vCount = 0;
			int nCount = 0;
		
			if(results[i] != ""){
				String[] temp = results[i].split("\\s");
				
				for(int j = 0; j < temp.length; j++){
					if(temp[j].equals("abcd") || temp[j].equals("c") || temp[j].equals("def") 
					|| temp[j].equals("h") || temp[j].equals("ij") || temp[j].equals("cde")){
						nCount++;
					}
					
					else if(temp[j].equals("bc") || temp[j].equals("fg") || temp[j].equals("g") 
							|| temp[j].equals("hij") || temp[j].equals("bcd")){
								vCount++;
					}
					
					else if(temp[j].equals("a") || temp[j].equals("ac") || temp[j].equals("e")){
						aCount++;
					}		
				}				
				if((vCount >= 1 && nCount >= 1) || (vCount >= 1 && aCount >= 2))
					System.out.println("[\"" + results[i] + "\"]");
			}
		}
	}
}