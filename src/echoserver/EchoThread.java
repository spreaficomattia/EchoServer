
package echoserver;

import java.net.Socket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread extends Thread {
    private Socket s;
    private boolean fine=false;
    private boolean maiuscole=false;
    
    public EchoThread(Socket s){
        this.s=s;
    }
    public void run(){
         BufferedReader in=null;
         PrintWriter out=null;
         try
         {
           in=new BufferedReader(new InputStreamReader(s.getInputStream()));
           out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
           while(fine==false)
           {
                String s=in.readLine();
                if(s.equals("fine"))
                {
                   fine=true;
                }
                else if(s.equals("maiuscole:on"))
                {
                  maiuscole=true;
                }
                else if(s.equals("maiuscole:off"))
                {
                  maiuscole=false;
                }
                else
                {
                    if(maiuscole==true)
                    {
                        out.println(s.toUpperCase());
                    }
                    else
                    {
                        out.println(s.toLowerCase());
                    }
                }

            }
        }catch(IOException ex)
        {
            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);  
        }
        try
        {
            s.close();
        }catch(IOException ex) 
        {
            ex.printStackTrace();
        } 
    }
}
