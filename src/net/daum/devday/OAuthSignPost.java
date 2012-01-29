package net.daum.devday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.commonshttp.HttpResponseAdapter;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class OAuthSignPost {
	public static String PROTECTED_URL ="http://apis.daum.net/blog/post/write.do?blogName=be-_-happy-_-v&output=xml&title=뿌잉뿌잉&content=";
	public static String CONSUMER_KEY = "eafefec5-6585-4b5f-a966-6e5e323e4b24";
	public static String CONSUMER_SECRET = "SnyKrL5vn2i68OpGUShgC7zPH3mJCn-eZMSUknOgUf-aIcoDCn9wUw00";
	public static String CALLBACK_URL = "http://blog.daum.net/be-_-happy-_-v";	
	
	
//	String OAUTH_TOKEN="6159078d-8b26-4b35-a998-3e51b65b3b4b";
	public String send(String p) {
		try {
			String postId = sendToBlog(p);
			modifyBlog(p, postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	public static String sendToBlog(String htmlTag) throws OAuthMessageSignerException,
				OAuthNotAuthorizedException, OAuthExpectationFailedException,
				OAuthCommunicationException, IOException,
				UnsupportedEncodingException, ClientProtocolException {
			//http://github.com/kaeppler/signpost-examples/blob/e4b32245eab2c2520d104f409c2314adcb0892e8/OAuthFireEagleExample/src/FireEagleMain.java#
			//http://kaeppler.github.com/signpost/signpost-core-apidocs/index.html
			
			String ACCESS_TOKEN = null;
			String TOKEN_SECRET = null;
			
			if("2".equals("1")){
				/** A : Consumer Request Token */
				OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
				//OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
				
		        String authUrl = provider.retrieveRequestToken(consumer, CALLBACK_URL);
		
		        /** C,D : Consumer Directs User to Service provider. and Service provider Directs User to Consumer  */
		        System.out.println("verify");
		        System.out.println(authUrl);
		    
		        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String verifier = in.readLine();
	        
				provider.retrieveAccessToken(consumer, verifier.trim());
			
		//		/** E : Consumer Requests Access Token */
				ACCESS_TOKEN = consumer.getToken();
				TOKEN_SECRET = consumer.getTokenSecret();
				System.out.println(ACCESS_TOKEN);
				System.out.println(TOKEN_SECRET);
			
			}		
			
			ACCESS_TOKEN = "ec2cfe0b-d17a-4a78-9023-bb634fd75d2e";
			TOKEN_SECRET = "mD_pWCQHPHzAmaasYbKHWuHmOE8j9jAoTnjJHiPnSWbOgLn4FkeOpQ00";
			
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		
	//		String ACCESS_TOKEN = consumer.getToken();
	//		String TOKEN_SECRET = consumer.getTokenSecret();
	//		System.out.println(consumer.getToken());
	//		System.out.println(consumer.getTokenSecret());
			
	        // if not yet done, load the token and token secret for
	        // the current user and set them
	        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
	        
	
	//         create a request that requires authentication
	        String content = URLEncoder.encode(htmlTag, "UTF-8");
	        HttpPost request = new HttpPost(PROTECTED_URL + content);
	        
	        
	        
	//        StringEntity body = new StringEntity("city=hamburg&label=" + URLEncoder.encode("Send via Signpost!", "UTF-8"));
	        StringEntity body = new StringEntity("");
	        body.setContentType("application/x-www-form-urlencoded");
	        request.setEntity(body);
	
	        consumer.sign(request);
	        
	        HttpClient httpClient = new DefaultHttpClient();
	        HttpResponse response = httpClient.execute(request);
	        
	        System.out.println("Response: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
	        HttpResponseAdapter d = new HttpResponseAdapter(response);
	        
	        
	//        BufferedReader br = new BufferedReader(new InputStreamReader(re.getMessagePayload() ,"UTF-8"));
	        BufferedReader br = new BufferedReader(new InputStreamReader(d.getContent() ,"UTF-8"));
	        String strPostId = null;
	        while(br.read() > 0){
	        	String readLine = br.readLine();
	        	if(readLine.contains("postId>")) {
	        		int idx = readLine.indexOf('>');
	        		int idxe = readLine.indexOf('<', idx);
	        		strPostId = readLine.substring(idx+1, idxe);
	        	}
	        	if(readLine.contains("url>")) {
	        		int idx = readLine.indexOf('>');
	        		int idxe = readLine.indexOf('<', idx);
	        		url = readLine.substring(idx+1, idxe);
	        	}
	        	System.out.println(strPostId+" "+readLine);
	        }
	        
	        return strPostId;
	        
	        
	//        URL url = new URL(PROTECTED_URL);
	//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
	//        consumer.sign(request);
	//        
	//        request.connect();
		}
	public static OAuthProvider provider = new CommonsHttpOAuthProvider(
			"https://apis.daum.net/oauth/requestToken",
			"https://apis.daum.net/oauth/accessToken",
			"https://apis.daum.net/oauth/authorize"
			);
	private static String url;
	
	public static String getUrl() {
		return url;
	}
	public static void modifyBlog(String htmlTag, String postId) throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException, IOException,
			UnsupportedEncodingException, ClientProtocolException {
		//http://github.com/kaeppler/signpost-examples/blob/e4b32245eab2c2520d104f409c2314adcb0892e8/OAuthFireEagleExample/src/FireEagleMain.java#
		//http://kaeppler.github.com/signpost/signpost-core-apidocs/index.html
		
		String ACCESS_TOKEN = null;
		String TOKEN_SECRET = null;
		
		if("2".equals("1")){
			/** A : Consumer Request Token */
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			//OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			
	        String authUrl = provider.retrieveRequestToken(consumer, CALLBACK_URL);
	
	        /** C,D : Consumer Directs User to Service provider. and Service provider Directs User to Consumer  */
	        System.out.println("verify");
	        System.out.println(authUrl);
	    
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String verifier = in.readLine();
        
			provider.retrieveAccessToken(consumer, verifier.trim());
		
	//		/** E : Consumer Requests Access Token */
			ACCESS_TOKEN = consumer.getToken();
			TOKEN_SECRET = consumer.getTokenSecret();
			System.out.println(ACCESS_TOKEN);
			System.out.println(TOKEN_SECRET);
		
		}		
		
		ACCESS_TOKEN = "ec2cfe0b-d17a-4a78-9023-bb634fd75d2e";
		TOKEN_SECRET = "mD_pWCQHPHzAmaasYbKHWuHmOE8j9jAoTnjJHiPnSWbOgLn4FkeOpQ00";
		
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

		// if not yet done, load the token and token secret for
        // the current user and set them
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
        

//         create a request that requires authentication
        String content = URLEncoder.encode(htmlTag, "UTF-8");
        PROTECTED_URL = "https://apis.daum.net/blog/post/modify.do?blogName=be-_-happy-_-v" +
        		"&title=뿌잉" +
        		"&postId=" + postId +
        		"&content=";
        HttpPost request = new HttpPost(PROTECTED_URL + content);
        
        
        StringEntity body = new StringEntity("");
        body.setContentType("application/x-www-form-urlencoded");
        request.setEntity(body);

        consumer.sign(request);
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        
        System.out.println("Response: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
        HttpResponseAdapter d = new HttpResponseAdapter(response);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(d.getContent() ,"UTF-8"));
        String strPostId = null;
        while(br.read() > 0){
        	String readLine = br.readLine();
        }
        
	}

}
