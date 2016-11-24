import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.PostingsEnum;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class irp2 {
	
	static int x=0;
	static int inc = 0;
	static ArrayList<LinkedList> temp = new ArrayList<LinkedList>(); 
	static String arr[] = new String[66993];
	static int num1[] = new int[66993];
	static LinkedList<Integer>[] postings = new LinkedList [66993];
	
	public static void main(String args[]) throws IOException{
		
		String str_p = args[0];
		String file_output = args[1];
		String file_input = args[2];
		
		createPostings("text_it",str_p);
		createPostings("text_es",str_p);
		createPostings("text_sv",str_p);
		createPostings("text_ru",str_p);
		createPostings("text_de",str_p);
		createPostings("text_nl",str_p);
		createPostings("text_fr",str_p);
		createPostings("text_ja",str_p);
		createPostings("text_pt",str_p);
		createPostings("text_no",str_p);
		createPostings("text_da",str_p);
		
		readFile(file_output,file_input);
		
	}
	
	//-------------------------------------------------------------------
	
	public static void createPostings(String text, String str_p) throws IOException{
		//String str_p = "index";
		Path path = Paths.get(str_p);
	    Directory d = FSDirectory.open(path);
	    IndexReader ir = DirectoryReader.open(d);
	    
	    Terms terms = MultiFields.getTerms(ir, text);
	    TermsEnum termEnum=terms.iterator();
	    BytesRef term;
		try{
	    	 while ( (term = termEnum.next()) != null ) {
	    		arr[x] = term.utf8ToString(); 
	    		num1[x] = x+1;
	   
	 	    	PostingsEnum pe = MultiFields.getTermDocsEnum(ir, text, term);
	 	    	while((pe.nextDoc())!= PostingsEnum.NO_MORE_DOCS){
	 	    		 if (postings[x] == null) {
	 	    		     postings[x] = new LinkedList<Integer>();
	 	    		  }
	 	    		postings[x].add(pe.docID());
	 	    		
	 	    	}
	 	    	x++;
	 	    } 
	    }catch(Exception e){
	    	System.out.println(e);
	    }
    }
	
	//-----------------------------------------------------------------------
	
	public static void getPostings(String q){
		String query = q;
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(query)){
				System.out.println("GetPostings");
				System.out.print(arr[i]+"\nPostings list:");
				for(int a = 0;a<postings[i].size();a++){
					System.out.print(" "+postings[i].get(a));
				}
				System.out.println();
				AddTemp(postings[i]);
			}
		}
		
	}
	
	//------------------------------------------------------------------------
	
	public static void readFile(String file_output, String file_input) throws IOException {
		
		//File f1 = new File("output.txt");
		FileOutputStream fos = new FileOutputStream(file_output);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
	
		//File f = new File("input.txt");
	    BufferedReader reader = new BufferedReader(new FileReader (file_input));
	    String line = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    
	    try {
	        while((line = reader.readLine()) != null){
	            stringBuilder.append(line);
	            String query = stringBuilder.toString();
		        String[] stringArray = query.split(" ");
		        for(int i=0;i<stringArray.length;i++){
		        	getPostings(stringArray[i]);
		        }
		        
		        TaatAnd(stringArray);
		        TaatOr(stringArray);
		        DaatAnd(stringArray);
		        DaatOr(stringArray);
		       
		        temp.clear();
		        stringBuilder.delete(0, line.length());
		        
	        }  
	        
	    } finally {
	        reader.close();
	    }
	}
	
	//-----------------------------------------------------------------------

	public static void AddTemp(LinkedList<Integer> pl){
		temp.add(pl);
	}
	
	//-----------------------------------------------------------------------
	
	public static void TaatAnd(String[] array) throws IOException{
		
		if(temp.size()==1 || temp.size()==0){
			System.out.println("TaatAnd");
			System.out.println(array[0]);
			System.out.print("Results: empty");
			System.out.println("\nNumber of documents in results: 0");
			System.out.println("Number of comparisons: 0");
			
		}else{
			int n=0;
			int match = 0;
			int compare = 0;
			LinkedList<Integer> temp2 = new LinkedList();
			int othercount=temp.size()-1;
			int[] temp1 = new int[temp.get(0).size()];
			for(int i = 0;i<temp.get(0).size();i++){
				temp1[i]=(Integer) temp.get(0).get(i);
			}
			A: for(int i=0;i<temp1.length;i++){
				int cdoc = temp1[i];
				B: for(int j = 1;j<=othercount;j++){
						if(j==2 && match==0)
							break B;
					for(int k=0;k<temp.get(j).size();k++){
						if(cdoc ==(Integer) temp.get(j).get(k)){
							match++;
							compare++;
							break;
						}else
							compare++;
					}
				}
				if(match==othercount){
					temp2.add(cdoc);
					match=0;
				}else{
					match=0;
				}
			
			}
			
			System.out.println("TaatAnd");
			for(int i=0;i<array.length;i++){
				System.out.print(array[i]+" ");
			}
			
			System.out.println();
			if(temp2.size()==0){
				System.out.print("Results: empty");
			}else{
				System.out.print("Results: ");
				for(int t=0;t<temp2.size();t++){
					System.out.print(temp2.get(t)+" ");
				}
			}
			
			System.out.println("\nNumber of documents in results: "+temp2.size());
			System.out.println("Number of comparisons: "+compare);
			
			
		}
		
		
		
	}
	
	//-----------------------------------------------------------------------
	
	public static void TaatOr(String[] array){
		
		if(temp.size()==0){
			System.out.println("TaatOr");
			System.out.println(array[0]);
			System.out.print("Results: empty");
			System.out.println("\nNumber of documents in results: 0");
			System.out.println("Number of comparisons: 0");
			
		}else if(temp.size()==1){
			System.out.println("TaatOr");
			System.out.println(array[0]);
			System.out.print("Results: ");
			for(int t=0;t<temp.get(0).size();t++){
				System.out.print(temp.get(0).get(t)+" ");
			}
			System.out.println("\nNumber of documents in results: "+temp.get(0).size());
			System.out.println("Number of comparisons: 0");
			
		}else{
			
			int n=0;
			int othercount = temp.size()-1;
			LinkedList<Integer> temp1 = new LinkedList();
			LinkedList<Integer> temp2 = new LinkedList();
			for(int i=0;i<temp.get(0).size();i++){
				temp1.add((Integer) temp.get(0).get(i));
			}
			
			int compare = 0;
			int i = 0;int k=0;
			for(int j=1;j<=othercount;j++){
				while(i<=temp1.size()-1 || k<= temp.get(j).size()-1){
					if(k>temp.get(j).size()-1){
						if(i<=temp1.size()-1){
							temp2.add(temp1.get(i));
							i++;
						}
					}else if(i>temp1.size()-1){
						if(k<= temp.get(j).size()-1){
							temp2.add((Integer)temp.get(j).get(k));
							k++;
						}
					}else if(temp1.get(i) < (Integer) temp.get(j).get(k)){
						temp2.add(temp1.get(i));
						compare++;
						i++;
					}else if(temp1.get(i) > (Integer) temp.get(j).get(k)){
						temp2.add((Integer) temp.get(j).get(k));
						compare++;
						k++;
					}else{
						temp2.add(temp1.get(i));
						compare++;
						i++;
						k++;
					}
				}
				i = 0;
				k = 0;
				temp1.clear();
				for(int m=0;m<temp2.size();m++){
					temp1.add(temp2.get(m));
				}
			temp2.clear();
			}
			

			System.out.println("TaatOr");
			for(int z=0;z<array.length;z++){
				System.out.print(array[z]+" ");
			}
			
			System.out.println();
			if(temp1.size()==0){
				System.out.print("Results: empty");
			}else{
				System.out.print("Results: ");
				for(int t=0;t<temp1.size();t++){
					System.out.print(temp1.get(t)+" ");
				}
			}
			System.out.println("\nNumber of documents in results: "+temp1.size());
			System.out.println("Number of comparisons: "+compare);
			
		}
		
		
		
	}
	
	//-----------------------------------------------------------------------
	
	public static void DaatAnd(String[] array){
		
		if(temp.size()==1 || temp.size()==0){
			System.out.println("DaatAnd");
			System.out.println(array[0]);
			System.out.print("Results: empty");
			System.out.println("\nNumber of documents in results: 0");
			System.out.println("Number of comparisons: 0");
			
		}else{
			
			LinkedList<Integer>temp4 = new LinkedList<Integer>();
			int compare = 0;
			int[] k = new int[temp.size()];
			for(int w = 0; w<k.length;w++){
				k[w]=0;
			}
			
			int match = 0;
			int tempcomp=99999;
			int finalcount = 0;
			A: while(finalcount<temp.size()){
				for(int j=0;j<temp.size();j++){
					if(k[j]<temp.get(j).size()){
						if(tempcomp>(Integer)temp.get(j).get(k[j])){
							tempcomp = (Integer) temp.get(j).get(k[j]);
							compare++;
						}
					}
				}
				
				for(int j=0;j<temp.size();j++){
					if(k[j]<temp.get(j).size()){
						if(tempcomp==(Integer)temp.get(j).get(k[j])){
							k[j]++;
							match++;
							if(k[j]>=temp.get(j).size()){
								break A;
							}
						}
					}
				}
				if(match == (Integer)temp.size()){
					temp4.add(tempcomp);
					tempcomp = 99999;	
				}
				
				tempcomp = 99999;
				match=0;
			}
			
			System.out.println("DaatAnd");
			for(int z=0;z<array.length;z++){
				System.out.print(array[z]+" ");
			}
			
			System.out.println();
			if(temp4.size()==0){
				System.out.print("Results: empty");
			}else{
				System.out.print("Results: ");
				for(int t=0;t<temp4.size();t++){
					System.out.print(temp4.get(t)+" ");
				}
			}
			System.out.println("\nNumber of documents in results: "+temp4.size());
			System.out.println("Number of comparisons: "+compare);
			
		}
			
	}
	
	//-----------------------------------------------------------------------
	
	public static void DaatOr(String[] array){
		
		
		if(temp.size()==0){
			System.out.println("DaatOr");
			System.out.println(array[0]);
			System.out.print("Results: empty");
			System.out.println("\nNumber of documents in results: 0");
			System.out.println("Number of comparisons: 0");
			
		}else if(temp.size()==1){
			System.out.println("DaatOr");
			System.out.println(array[0]);
			System.out.print("Results: ");
			for(int t=0;t<temp.get(0).size();t++){
				System.out.print(temp.get(0).get(t)+" ");
			}
			System.out.println("\nNumber of documents in results: "+temp.get(0).size());
			System.out.println("Number of comparisons: 0");
			
		}else{
			LinkedList<Integer>temp5 = new LinkedList<Integer>();
			int compare = 0;
			int[] k = new int[temp.size()];
			for(int w = 0; w<k.length;w++){
				k[w]=0;
			}
			
			int tempcomp=99999;
			int finalcount = 0;
			while(finalcount<temp.size()){
				for(int j=0;j<temp.size();j++){
					if(k[j]<temp.get(j).size()){
						if(tempcomp>(Integer)temp.get(j).get(k[j])){
							tempcomp = (Integer) temp.get(j).get(k[j]);
							compare++;
						}
					}
				}
				
				for(int j=0;j<temp.size();j++){
					if(k[j]<temp.get(j).size()){
						if(tempcomp==(Integer)temp.get(j).get(k[j])){
							k[j]++;
							if(k[j]>=temp.get(j).size()){
								finalcount++;
							}
						}
					}
				}
				
				temp5.add(tempcomp);
				tempcomp = 99999;
			}
			
			System.out.println("DaatOr");
			for(int z=0;z<array.length;z++){
				System.out.print(array[z]+" ");
			}
			
			System.out.println();
			if(temp5.size()==0){
				System.out.print("Results: empty");
			}else{
				System.out.print("Results: ");
				for(int t=0;t<temp5.size();t++){
					System.out.print(temp5.get(t)+" ");
				}
			}
			System.out.println("\nNumber of documents in results: "+temp5.size());
			System.out.println("Number of comparisons: "+compare);
			
		}
	}
		
		
	
	//-----------------------------------------------------------------------
	
}
