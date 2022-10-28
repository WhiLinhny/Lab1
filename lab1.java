package softwareEngineering;
import java.io.*;
import java.util.Iterator;
import java.util.Stack;
/*
 * | The Link Your Class |https://bbs.csdn.net/forums/MUEE308FZU202201 |
    | ----------------- |--------------- | 
    | The Link of Requirement of This Assignment | https://bbs.csdn.net/topics/608734907 | 
    | The Aim of This Assignment | find keyword num and structure num |
    | MU STU ID and FZU STU ID | 20123809_832002202 |
 */
public class lab1 {
	public static void main(String[] args) throws IOException {
		String word="abstract  default  goto*  switch  boolean  do  if  package  nchronzed  break  double  implements  private  this  byte  else  import  protected  throw  throws  case  extends  instanceof  public  transient  catch  int  return  char  final  interface  short  try  class  finally  long  static  void  const*  float  native  strictfp  volatile  continue  for  new  super  while  assert  enum";
		String arr[]=word.split("  ");
		String arr1[]= {"if","else","else if"};
		File file=new File("C:\\Users\\lenovo\\Desktop\\lab1code.txt");
		int times = 0;//出现的次数
		LineNumberReader lineReader = null;
		lineReader = new LineNumberReader(new FileReader(file));
		String readLine = null;
		while((readLine =lineReader.readLine()) != null){  
			//判断每一行中,出现关键词的次数
			String keyword;
			for(int i=0;i<arr.length;i++) {
				keyword=arr[i];
				int next = 0;  //定义开始查找关键字的序列号
				int index = 0; //获得readLine的对象值
				while((index = readLine.indexOf(keyword,next)) != -1&&readLine.charAt(index+keyword.length())!='u') {  //从每行的第0个索引开始遍历关键字
					next = index + keyword.length();  //下一次的遍历序号为序列号+关键字长度
					times++;//次数加1
					}
			}		
	}
		lineReader.close();
		int totalNum=lineReader.getLineNumber();
		System.out.println("total num is "+times);	
		findSwitchCase(file,totalNum);
		System.out.println("if-else num: "+findIfElse(file,arr1));
		System.out.println("if-elseif-else num: "+findIfElseIfElse(file,arr1));
}
	
	public static int findIfElse(File file,String arr1[]) throws IOException {
		LineNumberReader lineReader;
		lineReader = new LineNumberReader(new FileReader(file));
		String readLine = null;
		Stack<String> stack = new Stack<String>();
		int count=0;
				while((readLine =lineReader.readLine()) != null){  
					int next=0;
					int index=0;
				if((index = readLine.indexOf("if",next)) != -1&&readLine.charAt(index-2)!='e') {  //从每行的第0个索引开始遍历关键字 
						stack.push("if");
				}		
				else if((index = readLine.indexOf("else",next)) != -1&&readLine.charAt(index+5)!='i'){
						if(stack.isEmpty()) continue;
						String top=stack.pop();
						if(top=="if")
							count++;
					}
				else if((index = readLine.indexOf("else if",next)) != -1){
					stack.push("else if");
				}
				}	
				lineReader.close();
		return count;
	}
	public static int findIfElseIfElse(File file,String arr1[]) throws IOException {
		LineNumberReader lineReader;
		lineReader = new LineNumberReader(new FileReader(file));
		String readLine = null;
		Stack<String> stack = new Stack<String>();
		int count=0;
		int count1=0;
				while((readLine =lineReader.readLine()) != null){  
					int next=0;
					int index=0;
				if((index = readLine.indexOf("if",next)) != -1&&readLine.charAt(index-2)!='e') {  //从每行的第0个索引开始遍历关键字 
						stack.push("if");
						//System.out.println("if");
				}		
				else if((index = readLine.indexOf("else",next)) != -1&&readLine.charAt(index+5)!='i'){
				//	System.out.println("else");
						if(stack.isEmpty()) continue;
						String top=null; 
						while((top=stack.pop())=="else if") {
							count1++;
						}
						if(count1>=1&&top=="if") {
							count++;
							count1=0;
						}					
					}
				else if((index = readLine.indexOf("else if",next)) != -1){
				//	System.out.println("else if");
					stack.push("else if");
				}
				}	
				lineReader.close();
		return count;
	}
	public static void findSwitchCase(File file,int totalNum) throws IOException {
		int lineNum=0;
		int countSwitch=0;
		while(lineNum<=totalNum) {
		int lineNumS=findSwitch(file,lineNum);
		if(lineNumS!=0) {
			lineNum=lineNumS;
			countSwitch++;
			}else {
				System.out.println();
				System.out.println("Switch num: "+countSwitch);
				break;
			}
		int lineNumD=findDefault(file,lineNumS);
		if(lineNumS>=1&&lineNumD>lineNumS) {
			if(countSwitch==1) {
				System.out.print("case num: ");
				System.out.print(countCase(file,lineNumS,lineNumD)+" ");
			}else {
				System.out.print(countCase(file,lineNumS,lineNumD)+" ");
			}
		}
	}	
}
	public static int countCase(File file,int lineNumS,int lineNumD) throws IOException {
		String readLine=null;
		LineNumberReader lineReader;
		lineReader = new LineNumberReader(new FileReader(file));
		int count=0;
		int numCase=0;
		String keyword;
		keyword="case";
		while((readLine =lineReader.readLine()) != null){ 
			count++;
			if(count>lineNumS&&count<lineNumD) {// 确保case在switch 和 default 之间
			int next = 0;  //定义开始查找关键字的序列号
			int index = 0; //获得readLine的对象值
			while((index = readLine.indexOf(keyword,next)) != -1) {  //从每行的第0个索引开始遍历关键字
				numCase++;//次数加1
				break;
			}
		  }
		}
		lineReader.close();
	return numCase;	
	}
	public static int findDefault(File file,int lineNum) throws IOException {
		String readLine=null;
		LineNumberReader lineReader;
		lineReader = new LineNumberReader(new FileReader(file));
		int count=0;
		int numDefault=0;
		String keyword="default";
		while((readLine =lineReader.readLine()) != null){  
			count++;
			if(count>lineNum) {
			int next = 0;  //定义开始查找关键字的序列号
			int index = 0; //获得readLine的对象值
			while((index = readLine.indexOf(keyword,next)) != -1) {  //从每行的第0个索引开始遍历关键字
				numDefault++;//次数加1
				break;
				}
			}
			if(numDefault>=1) {
				break;
			}
}  lineReader.close();
		return count;
	}
	public static int findSwitch(File file,int lineNum) throws IOException {
		LineNumberReader lineReader;
		lineReader=new LineNumberReader(new FileReader(file));
		String readLine=null;
		int count=0;
		int numSwitch=0;
		String keyword="switch";
		while((readLine =lineReader.readLine()) != null){  
				count++;
				if(count>lineNum){
				int next = 0;  //定义开始查找关键字的序列号
				int index = 0; //获得readLine的对象值
				while((index = readLine.indexOf(keyword,next)) != -1) {  //从每行的第0个索引开始遍历关键字
					numSwitch++;//次数加1
					break;
				}
			 } 
				if(numSwitch>=1) {
					break;
				}	
		}
		lineReader.close();
		if(numSwitch!=0) {
			return count;
	}else {
		return numSwitch;
	}
	}
}
