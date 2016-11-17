import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import twitter4j.HashtagEntity;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;


public class Main {
	
	public static void main(String args[]) throws Exception{
		ConfigurationBuilder cf = new ConfigurationBuilder();
		
		cf.setDebugEnabled(true)
			.setOAuthConsumerKey("nBjhmVqSwZtHkZApxtOBNjTg4")
			.setOAuthConsumerSecret("JoF6O12DkUzMgcB3DVjC9jGYMnOAnbh3YD2dcppmAE8rKmrmAd")
			.setOAuthAccessToken("1246572168-rJD29rihXLy0NyCIjWuinYBwIErjnWlVSS1xVcw")
			.setOAuthAccessTokenSecret("O8VXNztaKZ67aw7EhdS90aHg9tu2mF8jHkd0Q4IBgn9YS")
			.setJSONStoreEnabled(true);
			
		
		TwitterFactory tf = new TwitterFactory(cf.build());
		twitter4j.Twitter twitter = tf.getInstance();
		File file = new File("/home/ashwin/workspace/twitter/test.json");
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		JSONObject obj = new JSONObject();
		
		String maintext="";
		String  hash = "";
		String url = "";
		String umen = "";
		String durl = "";
		String eurl = "";
		
		    String[] masterEmoticonList ={":-)",":)",":D",":o)",":]",":3",":c)",":>","=]","8)","=)","='(",":}",":^)",":っ)",":-D","8-D","8D","x-D","xD","X-D","XD","=-D","=D","=-3",
            "=3","B^D",":-))",">:[",":-(",":(",":-c",":c",":-<",":っC",":<",":-[",":[",":{",";(",":-||",":@",">:(",":'-(",":'(",":'-)",":')","D:<","D:","D8","D;","D=","DX","v.v","D-':",
            ">:O",":-O",":O",":-o",":o","8-","O_O","o-o","O_o","o_O","o_o","O-O",":*",":^*",";-)",";)","*-)","*)",";-]",";]",";D",";^)",">:P",":-P",":P","X-P","x-p","xp","XP",":-p",":p",
            "=p",":-Þ",":Þ",":þ",":-þ",":-b",":b","d:",">:\\",">:/",":-/",":-.",":\\","=/","=\\",":L","=L",":S",">.<",":|",":-|",":$",":-X",":X",":-#",":#","O:-)",":-3",":3",":-)",
            ";^)",">:)",">;)",">:-)","}:-)","}:)","3:-)","3:)","|;-)","|-O",":-J","<3","</3","T_T","@-)","B-)",
            ":-)",";-)",":-(",":->",">:->",">;->","%-)","#-)",":-I","(-:",":-D",":-O",":-o",":*)","8-)",":'-(",":'-)","(:-)",":-@",":-#",":-0",":-P",":-S",":-X","8-O","~~:-(",":-&",":-$",
            ":-","|-I","|-o","|-O","-:-)","-:-(","=:-)","=:-(","8:-)",":-)-8",":-)8<","':-)",",:-)",":-Q",":-?",":-{",":-{)","{:-)","}:-(","B-)",":-{}","::-)","[:-)","[:]","[]","]:->",")8-)","+-:-)",
            ";^)","!-(","#:-)","$-)","%-\\","%-6","&-|","&-e","&:-)","(-)","(-:|:-)","(:+)","(:-(","(:-D","):-(","):-)",")O-)","*<:-)","*<:-)>","*<|:-)","+:-)","+<:-|",",-)",",-}",".-)",":#)",":'(",":(",
            ":*",":,(",":-!",":-'|",":-(*)",":-(=)",":-)'",":-)))",":-)-o",":-)~",":-*",":-----}",":--8----",":-/",":-:",":-<",":-[",":-\\",":-]",":-`",":-`|",":-|",":-|-----=",":-|:-|",":-|><",":-}",":-}X",
            ":-~)",":-3",":-6",":-7",":-8(",":-9",":-a",":-c",":-C",":-d",":-E",":-F",":-o>-[]",":-r",":-T",":-t",":-TM",":-V",":-w",":-y",":/\\)",":=",":=)",":=|",":>",":@",":[",":\\/",":]",":^)",":^D",":_)",
            ":{",":}",":~(~~",":~-(",":~i",":8)",":C",":D",":I",":O",":Q",":v)","<:-|","<:-I","<<<<:-)","<|-)","<|-(","<||:-)","<|||:|||)","=|:-)=","><*:oDX","><:>==",">>:-)",">>:-<<",">>>>(:-)","?-(","@:-)",
            "@:-}","@:i","@=","^o","{@:)}","|-(","|-)","|-D","|:-O","|^o","|~(","|I","}:^#)","~:o","~~:-[","0-)","3:-o","3:[","8-#","8:]","8<:-)","b:-)","B:-)","C=:-)","d:-)","d8=","E-:-)","E-:-I","K:P","O-)",
            "o-<:-{{{","O|-)","P-(","P-)","R-)","X-(","X:-)" };
            String[] masterKaomojiList={"▼=ΦωΦ=▼","(ó㉨ò)ﾉ♡","(-̩__-̩)","(❁´◡`❁)","￢o(￣-￣ﾒ)","(￣(∵エ∵)￣)","✖‿✖","ᕙ(⇀‸↼‶)ᕗ","(°ヘ°)","ヾ(*￣O￣)ツ","(◐‿◑)﻿","(✿◠‿◠)","(″･ิ_･ิ)っ","ᶘ ᵒᴥᵒᶅ","(づ￣ ³￣)づ","(☄ฺ◣д◢)☄ฺ","・㉨・","(=ＴェＴ=)","（=´∇｀=","(／(ｴ)＼)","⊙﹏⊙","ミ●﹏☉ミ","＿〆(。。)","(-’๏_๏’-)","<(￣︶￣)>","๏[-ิ_•ิ]๏","ヾ(´〇｀)ﾉ","(╯ಊ╰)","(╯_╰)","Σ(￣。￣ノ)","（／_＼）","(╥_╥)","(=^_^=)","ლ(╹◡╹ლ)","(。_＋)","(⋋▂⋌)","( • ̀ω•́ )","(•ิ_•ิ)","ʕ •́؈•̀ ₎","(✪◡✪)","‾̴̴͡͡ ▿ ‾̴̴͡͡","(✘o✘)","(〃･ิ‿･ิ)ゞ","(•̥̀ ̫ •̥́)","(≧‿≦)","(〃ω〃)","(｡◕ˇ‿ˇ◕｡)","ต(≋°ัω°ั≋)ต","ლ(❤ʚ❤ლ)","(\\___(\\ ☂","ʕ•̫͡•ʔ❤ʕ•̫͡•ʔ","ต(≋°ัω°ั≋)ต","ﾍ(+＿+ﾍ)","／人◕‿‿◕人＼","( ’◉◞౪◟◉`)","(ﾐ◎-◎)ﾉ","／人◕ ‿‿ ◕人＼","ლ(╹◡╹ლ)","╭( •̀؁•́ )╮","(㇏(•̀ᵥᵥ•́)ノ)","(ﾉ●◉▽◉●)ﾉ","ლ(●ʘ‿ʘ● ლ)","( ’◉◞౪◟◉`)","/＼ ^._.^ ／\\","ლ(◉◞౪◟◉ )ლ　","ლ(･ิω･ิლ)","꒰ू ऀ•̥́ꈊ͒ੁ•ૅू॰˳ऀ꒱ ͟͟͞ ̊ ̥ ̥","ლ(⁰⊖⁰ლ)","ლ(╹ε╹ლ)","(๑• .̫ •๑)","ʕ♡˙ᴥ˙♡ʔ","ʕ≧ᴥ≦ʔ","ʕ´•ᴥ•`ʔ","ʕᴥ• ʔ☝","(⁀ᗢ⁀)","(ŏ﹏ŏ)","(｡•ˇ‸ˇ•｡)","(✧◑็அ◑็✧)❤","(ó㉨ò)ﾉ♡","凸(━へ━)","凸(｀0´)凸","(･_･ ) ( ･_･)","(━┳━ _ ━┳━)","[^._.^]ﾉ彡","凸(｀⌒´メ)凸","(•.•ิ)","ᕦ(ò_óˇ)ᕤ","(ノಠ益ಠ)ノ","(〠_〠)","ლ(´ڡ`ლ)","(=^・ェ・^=)","⁝⁞⁝⁞ʕु•̫͡•ʔु☂⁝⁞⁝⁝","(｀ ・ ω ・ ‘)","( -_-)凸","(づ｡◕‿‿◕｡)づ","(づ￣ ³￣)づ","(◣_◢)","(●♡∀♡)","༼ ༎ຶ ෴ ༎ຶ༽","༼´༎ຶ ۝ ༎ຶ༽","(•ˋ _ ˊ•)","⊂(￣(ｴ)￣)⊃","(◕㉨◕✿)","( ❝̆ ·̫̮ ❝̆ )✧","(◡‿◡✿)","(✧≖‿ゝ≖)","(゜。゜)","(*｀へ´*)","(◕‿◕✿)","（▼へ▼メ）","ヾ(-_- )ゞ","ᕙ(⇀‸↼‶)ᕗ","(º_º)","(⊙_◎)","(￣(エ)￣)","(✖╭╮✖)","＜(。_。)＞","m(._.)m","(=ＴェＴ=)","(●￣(ｴ)￣●)","༼´◓ɷ◔`༽","(✪ ㉨ ✪)","( ´ ▽ ` )ﾉ","~(=^‥^)ノ","(￣▼￣)","(=^･ｪ･^=)","༼ꉺ.̫ꉺ༽","༼ꉺ✺ꉺ༽","༼ƪɷ∫༽","٩◔̯◔۶","༼*☁ɷ☁*༽","༼ꉺ✪ꉺ༽","༼ꉺεꉺ༽","(ಥ﹏ಥ)","༼•̃͡ ɷ•̃͡༽","༼๑ɷ๑༽","ミ(° °)ミ","(´ε｀ )♡","ʕ•̀ω•́ʔ✧","(｡♥‿♥｡)","༼ꉺ.̫ꉺ༽","༼☉ɷ⊙༽","(•ิ_•ิ)","༼•̀ɷ•́༽","（＾ｖ＾）","●～*","༼=ꉺ✺ꉺ=༽","(^-人-^)","(•ˋ _ ˊ•)","( ^-^)_旦””","٩꒰ ˘ ³˘꒱۶~♡","(^._.^)ﾉ","ლ(╹◡╹ლ)","༼இɷஇ༽","( ༎ຶ ۝ ༎ຶ )","ヽ(*⌒∇⌒*)ﾉ","༼❁ɷ❁༽",
                    "（*＾ワ＾*）","（*＾＾*)","ヾ(＠^∇^＠)ノ","o((*^▽^*))o","ヾ(＠⌒ー⌒＠)ノ","(＝⌒▽⌒＝)","(≡^∇^≡)","o(〃＾▽＾〃)o","(。・ω・。)","ヾ(＾-＾)ノ","(❁´◡`❁)","˙˚ʚ(´◡`)ɞ˚˙","(≧∇≦)/","☆*: .｡. o(≧▽≦)o .｡.:*☆","(((o(*ﾟ▽ﾟ*)o)))","ヽ(*⌒∇⌒*)ﾉ","ヽ(＾Д＾)ﾉ"," o(≧∇≦o)","(((＼（＠v＠）／)))","(((o(*ﾟ▽ﾟ*)o)))","✖‿✖","(ﾉ´ヮ´)ﾉ*: ･ﾟ","✧ ─=≡Σ((( つ•̀ω•́)つ","( ✧Д✧) YES!!","(๑˃̵　ᴗ　˂̵)و","(*°∀°)=3","(●♡∀♡)","(♥_♥)","≧﹏≦","（｡>‿‿<｡ ）","ﾄﾞｷﾄﾞｷ(ﾟ∀ﾟ*)(*ﾟ∀ﾟ)ﾄﾞｷﾄﾞｷ","┣¨ｷ(〃ﾟ3ﾟ〃)┣¨ｷ","(^^)ｂ","(＾＾)ｂ OK!","（´∀`）ｂ","( ´ ▽ ` )b","(b^_^)b","b(~_^)d","(b ~_^)b d(^_^ d)","(・ω・)b","(･ω･)bｸﾞｯ","((o･д･)bｸﾞｯｼﾞｮﾌﾞ♪","(ﾟ∇^d) ｸﾞｯ!!","(〃 ω 〃)","(/ω＼)","(^///^)","（*/∇＼*）ｷｬ"
            };
		
		String q = "#USOpenFinal"; //Our main query
		int a=100;    //Counter to set number of tweets
		Query query = new Query(q);
		query.setCount(a);
		String language="en";
		query.setLang(language);
		int number=50;
		int i=0;
		pw.append("[");
		boolean firstnumber=false;
		for(int w=0;w<number;w++){
		QueryResult result = twitter.search(query);
		List<Status> tweets = result.getTweets();
		long min = Long.MAX_VALUE; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:'00':'00Z'");
		for(Status st : tweets){
		 //if(st.isRetweet()==true)
			 //continue;
			
			maintext = st.getText();
			
			if(st.getId()<min){
				min=st.getId();
			}
			
			JSONArray hj = new JSONArray();
			JSONArray uma = new JSONArray();
			
			String demo = st.getText();
			String emo = "";
			for(int count=0;count<masterEmoticonList.length;count++){
				if(demo.contains("https://")){
					demo.replace("https://", "");
				}
				if(demo.contains(masterEmoticonList[count])){	
					emo = emo+masterEmoticonList[count]+" ";
				}
			}
			
			for(Emoji emg: EmojiManager.getAll()){
				if(st.getText().contains(emg.getUnicode())){
					emo+=emg.getUnicode()+"";
				}
			}
			
			HashtagEntity[] z= st.getHashtagEntities();
			for(int count=0;count<st.getHashtagEntities().length;count++){
				String abc = "#";
				String hsh=z[count].getText();
				maintext=maintext.replace(abc, "");
				maintext=maintext.replace(hsh,"");
				hj.put(hj.length(), hsh);
			}
			
			URLEntity[] u = st.getURLEntities();
			for(int count=0;count<st.getURLEntities().length;count++){
				url=url+u[count].getText();
				maintext=maintext.replace(u[count].getText(), "");
				if(count<st.getURLEntities().length-1)
					url=url+" ";
			}
			
			URLEntity[] du = st.getURLEntities();
			for(int count=0;count<st.getURLEntities().length;count++){
				durl=durl+du[count].getDisplayURL();
				maintext=maintext.replace(du[count].getDisplayURL(), "");
				if(count<st.getURLEntities().length-1)
					durl=durl+" ";
			}
			
			URLEntity[] eu = st.getURLEntities();
			for(int count=0;count<st.getURLEntities().length;count++){
				eurl=eurl+eu[count].getExpandedURL();
				maintext=maintext.replace(eu[count].getExpandedURL(), "");
				if(count<st.getURLEntities().length-1)
					eurl=eurl+" ";
			}

			UserMentionEntity[] um = st.getUserMentionEntities();
			for(int count=0;count<st.getUserMentionEntities().length;count++){
				umen = umen+um[count].getScreenName();
				String abc = "@";
				String hsh=um[count].getText();
				maintext=maintext.replace(abc, "");
				maintext=maintext.replace(hsh,"");
				uma.put(uma.length(), hsh);
				if(count<st.getUserMentionEntities().length-1)
					umen=umen+" ";
			}
			String zzz = "RT :";
			if(maintext.contains(zzz))
				maintext=maintext.replace(zzz, "");
			maintext=maintext.replaceAll("@", "");
			maintext=maintext.replaceAll("https","" );
			//maintext.replaceAll("[^a-z]", "");
			//maintext.replaceAll("[^A-Z]", "");
			String space = "[ ]+";
			String[] tokens = maintext.split(space);
			
			obj.put("username", st.getUser().getName());
			obj.put("screen_name", st.getUser().getScreenName());
			obj.put("tweet_text", st.getText());
			obj.put("topic","Sports");
			obj.put("tweet_lang", language);
			//obj.put("tokens", tokens);
			if(language.equals("en"))
				obj.put("text_en", maintext);
			else if(st.getLang().equals("es"))
				obj.put("text_es", maintext);
			else if(st.getLang().equals("ko"))
				obj.put("text_ko", maintext);
			else if(st.getLang().equals("tr"))
				obj.put("text_tr", maintext);  
			
			obj.put("tweet_emoticons", emo);
			obj.put("tweet_date", sdf.format(st.getCreatedAt()));
			
			
			if(st.getGeoLocation()!=null){
				String location = st.getGeoLocation().getLatitude()+","+st.getGeoLocation().getLongitude();	
				obj.put("tweet_loc", location);
			}else{
				obj.put("tweet_loc", "0,0");
			}
				
			
			obj.put("mentions", uma);
			obj.put("tweet_urls", url);
			//obj.put("display_urls", durl);
			//obj.put("expanded_urls", eurl);
			obj.put("hashtags", hj);
			obj.put("retweet", st.isRetweet());
			
			
			String js = TwitterObjectFactory.getRawJSON(obj.toString());
			System.out.println(obj);
			//System.out.println(emo);
			if(firstnumber==false){
				pw.append(obj.toString());
				firstnumber=true;
				hash = "";
				url = "";
				umen = "";
				durl = "";
				eurl = "";
				i++;
			}else{
				pw.append(","+obj);
				i++;
				hash = "";
				url = "";
				umen = "";
				durl = "";
				eurl = "";
			}
			
		/*	if((i==(a-1) || i==(tweets.size()-1)) && w==number-1){
				pw.append(obj+"]");
				i++;
				hash = "";
				url = "";
				umen = "";
				durl = "";
				eurl = "";
				
				break;
				}   */
			
			}
		
		query.setMaxId(min);
		
		}
		pw.append("]");
		pw.close();
		System.out.println(i);
	}
	

}
