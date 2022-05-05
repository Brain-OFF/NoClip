/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookException;
import com.restfb.types.Page;
import com.restfb.types.Post;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


/**
 *
 * @author hp
 */
public class ServiceFacebook {
    private String token ;
    private FacebookClient client=null;
    private final String pageid="101652742543054" ;
    private Page page =null;

    User me =null;
    public ServiceFacebook(){
        if(client==null){
            try{            
            token="EAAd5NeRw6h8BAC515x3gyVQnGHPu0xKLIeVTfTvqtxBKEbNX455aQQLebLrQwcpVuCT52o2dghzwkKfZCxycCfYAtB8a6Qkgd34ttxpB7J7nAkt9gJsZCZC4Fv9hzMCobpHZCjrPJv3KhmTo0GB3nZBp4s3GkG8NZAVEOfAVxWPlF3mnIattFRoaT0N8SZBRLkMhu9Hbcscl6rfWS9Lvwux";
            client = new DefaultFacebookClient(token);
            me= client.fetchObject("me",User.class);
            page=client.fetchObject(pageid,Page.class);
            System.out.print("Facebook name:"+me.getName()+me.getBirthday());
            }
            catch(FacebookException ex){
                ex.printStackTrace();
            }
        }
        
        
    }
    public void publish(String post,String imgfilepath)throws FileNotFoundException{
        
        String path = "C:\\xampp\\htdocs\\NoClip-web-main\\public\\images\\logo.png";
        FileInputStream file = new FileInputStream(new File(path));
        FacebookType img = client.publish(pageid+"/photos",FacebookType.class,BinaryAttachment.with("logo.png", file),Parameter.with("message", "Testing api"));
       // client.publish(pageid+"/feed",FacebookType.class,Parameter.with("message",post));
        
        
    }
    public void fetch(){
        Connection<Post> myFeed = client.fetchConnection("me/feed",Post.class);
        for(List<Post> myfeedpage: myFeed){
            for(Post post: myfeedpage){
                System.out.println("Post: "+post);
            }
        }
    }
}
