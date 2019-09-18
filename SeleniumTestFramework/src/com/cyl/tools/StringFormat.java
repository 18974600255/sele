package com.cyl.tools;

public class StringFormat {
	String fh ="-";
	boolean key =true;
    //补充文本长度
	//实现原理：定一个max长度，多退少补！！！(传两个参数，一个是字符串，一个是max长度，
	//判断字符串如果字符串大于max则不改变，如果字符串长度小于max，根据字符串长度在字符串后面补充空格)
	//！！！字符串长度根据以下计算
	public StringFormat(boolean key){
		this.key=key;
	}
    public StringFormat(boolean key,String fh){
    	this.key=key;
    	this.fh=fh;
	}
	
	public String textFormat(String text,int max) {
		if(!key)fh=" ";
		//key=true：先=1
		//key=false:
		int one=0,ydw=0,kgc=0;//key=true:代表1和1.5,数字字母符号：汉字部分汉字符号       key=false:代表1和2,数字字母符号：汉字部分汉字符号
		String bc="";
		String kg="";
		//这里计算one和ydw数量
		char[] array = text.toCharArray();
		for(char c:array){
			if(c>='0'&&c<='9'){
				one++;
			}else if ((c>='a'&&c<='z')||(c>='A'&&c<='Z')) {
				one++;
				
			}else if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){
				ydw++;
			}else{
				//目前的1.5符号为：     。,，,【,】,；,（,）,￥,！,：,《,》,？,、,☆,★
				switch(c){
				case '。':ydw++;break;
				case '，':ydw++;break;
				case '【':ydw++;break;
				case '】':ydw++;break;
				case '；':ydw++;break;
				case '（':ydw++;break;
				case '）':ydw++;break;
				case '￥':ydw++;break;
				case '！':ydw++;break;
				case '：':ydw++;break;
				case '《':ydw++;break;
				case '》':ydw++;break;
				case '？':ydw++;break;
				case '★':ydw++;break;
				case '、':ydw++;break;
				case '☆':ydw++;break;
				case ' ':kgc++;break;
				default:one++;
				}
			}
		}
		//System.out.println("空格---------------"+kgc);
		//System.out.println("汉字---------------"+ydw);
		if(ydw % 2 == 1 ) {
			ydw++;
			one--;
			kg=" ";
		}
		//System.out.println("汉字--------------"+ydw);
		int length=0;
		if(key) {
		 length=one+ydw*3+kgc*2/5;
		}else {
		  length=one+ydw*2+kgc;
		}
		int bccount=max-length;
		//System.out.println("length--------------------"+length);
		//System.out.print("---------------------------哈哈---------");
		if(length<max) {//当长度小于max长度时，才会填充空格
		
		//System.out.println("填充空格数量："+bccount);
		for(int i=0;i<bccount;i++) {
			bc=bc+fh;                                        //这里用-来补充！
		}
		
		text=text+kg+bc;
		}
		else {//当长度大于登陆max长度时，返回原text
			return text;
		}
		return text;
	}
	

	//得到文本的数字,字母,汉字,符号数量
		public int[] getlength(String text) {
			char[] array = text.toCharArray();      
			int mNum = 0, eNum = 0, cNum = 0,fNum=0;//数字,字母,汉字,符号.备注,两个汉字算3个长度，数字，字母，符号算1个长度
		for(char c:array){
				if(c>='0'&&c<='9'){
					mNum++;
				}else if ((c>='a'&&c<='z')||(c>='A'&&c<='Z')) {
					eNum++;
				}else if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){
					cNum++;
				}else{
					fNum++;
				}
			}
		   int[] arr= {mNum,eNum,cNum,fNum};
			System.out.println("mNum:"+mNum+" eNum:"+eNum+" cNum:"+cNum+" fNum:"+fNum);
			return arr;
		}
}
