//package evaluation.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Metrics2 {
	
	public static void main(String[] args){
		//Please change this path to the path of student's outputs
		String tool_outputs_path = "C:/Assignment3/test2.txt";
				
		//Please change this path to the path of your local answer set file
		String answer_set_path = "C:/Assignment3/trace-4nfr-150fr.txt";
		
		Map<String,Integer[]> answer_set=read(answer_set_path);
		Map<String,Integer[]> tool_outputs=read(tool_outputs_path);
		double recall_average=0.0;
		double precision_average=0.0;
		double f2_average=0.0;
		for(int i=0;i<4;i++) {
			int tp=0;
			int answer_size=0;
			int result_size=0;
			for(Entry<String,Integer[]> entry:answer_set.entrySet()) {
				int answer = entry.getValue()[i];
				int output=0;
				if(tool_outputs.containsKey(entry.getKey())) {
					output=tool_outputs.get(entry.getKey())[i];
				}
				if(answer==1&&output==1) {
					tp++;
				}
				if(answer==1) {
					answer_size++;
				}
				if(output==1) {
					result_size++;
				}
			}
			double recall = answer_size==0?0:(double)tp/(double)answer_size;
			recall_average+=recall;
			double precision = result_size==0?0:(double)tp/(double)result_size;
			precision_average+=precision;
			double f2 = (4*precision+recall)==0.0? 0.0:5*(precision*recall)/(4*precision+recall);
			int order=i+1;
			System.out.println("NFR"+order+": recall: "+recall+"; precision: "+precision+"; f2: "+f2);
		}
		recall_average=recall_average/(double)4;
		precision_average=precision_average/(double)4;
		f2_average= (4*precision_average+recall_average)==0.0? 0.0:5*(precision_average*recall_average)/(4*precision_average+recall_average);
		System.out.println("Overall performance: recall: "+recall_average+"; precision: "+precision_average+"; f2: "+f2_average);
	}
	
	public static int getOrder(String strFR) {
		try{
			String str=strFR.substring(2);
			int id=Integer.parseInt(str.trim());
			return id;
		}catch(Exception ex){
			return -1;
		}
	}
	
	public static Map<String,Integer[]> read(String path){
		File file = new File(path);
		Map<String,Integer[]> matrix= new HashMap<String,Integer[]>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String p=line.trim();
				String[] trace= p.split(",");
				if(trace.length==5){
					if(isFRNumber(trace[0])&&isNumber(trace[1])&&isNumber(trace[2])&&isNumber(trace[3])&&isNumber(trace[4])) {
						if(!matrix.containsKey(trace[0].toLowerCase())) {
							Integer[] links=new Integer[4];
							links[0]= getTrace(trace[1]);
							links[1]= getTrace(trace[2]);
							links[2]= getTrace(trace[3]);
							links[3]= getTrace(trace[4]);
							matrix.put(trace[0].toLowerCase(), links);
						}
					}
				}
			}
			return matrix;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isFRNumber(String str) {
		boolean isFR = str.substring(0, 2).equalsIgnoreCase("fr");
		String strNumber = str.substring(2);
		return isNumber(strNumber)&&isFR;
	}
	
	public static boolean isNumber(String str){
		try{
			Integer.parseInt(str.trim());
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public static int getTrace(String strInt) {
		try{
			int link = Integer.parseInt(strInt);
			if(link>1) link=1;
			if(link<0) link=0;
			return link;
		}catch(Exception ex){
			return -1;
		}
	}
}
