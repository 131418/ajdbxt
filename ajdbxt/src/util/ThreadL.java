package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.struts2.ServletActionContext;

/**
 * Application Lifecycle Listener implementation class ThreadL
 *
 */
@WebListener
public class ThreadL implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ThreadL() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    
    public void contextDestroyed(ServletContextEvent arg0) {//服务器关闭时保存
    	System.out.println("服务器销毁");
    	Object o=arg0.getServletContext().getAttribute("threadMap");
		HashMap<String,Object> ho= (HashMap)o;
		System.out.println(ho);
		if(ho.size()>0) {
			Set<String> keys=ho.keySet();
			ObjectOutputStream out=null;
			for(String key:keys) {//可能会有异常，因为理论上ho的更改会影响到keys
				if(ho.get(key)==null) {
					ho.remove(key);
				}
			}
			try {
				File file=new File("thread.txt");
				if(!file.exists()) {
					file.createNewFile();
				}
				out=new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(ho);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(out!=null) {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
			}
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { //服务器启动读取
    	if(arg0.getServletContext().getAttribute("threadMap")==null){
    		ObjectInputStream in=null;
    		try {
            	System.out.println("服务器启动");
            	File file=new File("thread.txt");
    			if(file.exists()) {
    				in=new ObjectInputStream(new FileInputStream(file));
    				HashMap<String,Object> ho=(HashMap<String, Object>) in.readObject();
    				arg0.getServletContext().setAttribute("threadMap",ho);
    			}else {
    				HashMap<String,SMSThread> ho=new HashMap();
    				arg0.getServletContext().setAttribute("threadMap", ho);
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}finally {
				if(in!=null) {
					try {
						in.close();
					} catch (IOException e) {
						
					}
				}
			}
    	}
        
    }
	
}
