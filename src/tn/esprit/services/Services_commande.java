/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.Commande;
import tn.esprit.utils.Statics;

/**
 *
 * @author ameni
 */
public class Services_commande {
    //singleton 
    public static Services_commande instance = null ;
    
    public static boolean resultOk = true;

    
    
  
    
    //initialisation connection request
    private ConnectionRequest req;
    
    public static Services_commande getInstance(){
    if(instance ==null)
    instance=new Services_commande();
    return instance;
    }
    
    public Services_commande(){
        req=new ConnectionRequest();
    }
        
        public void ajoutCommande(Commande commande){
            String url=Statics.BASE_URL+"/addCommande?reference="+commande.getReference()+"&valider="+commande.getValider();
      
        
        req.setUrl(url);
        req.addResponseListener((e) ->{
            
            String str =new String (req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
        }
        
        //affichage
    
    public ArrayList<Commande>affichageCommandes() {
        
        ArrayList<Commande> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayCommande";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommandes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commande co = new Commande();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                         float reference = Float.parseFloat(obj.get("reference").toString());
//                          float valider = Float.parseFloat(obj.get("valider").toString());
                          
                        
                       
                        
                        
                        
                        co.setId((int)id);
                        co.setReference((int)reference);
                         
                       
                         
                        
                        //Date 
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        co.setDate(dateString);
                        
                        //insert data into ArrayList result
                        result.add(co);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
     NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Commande DetailCommande( int id , Commande commande) {
        
        String url = Statics.BASE_URL+"/detailCommande?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
               
                commande.setReference(Integer.parseInt(obj.get("reference").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return commande;
        
        
    }
    
    
    
    //Delete 
    public boolean deleteCommande(int id ) {
        String url = Statics.BASE_URL +"/deleteCommande?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierCommande(Commande commande) {
        String url = Statics.BASE_URL +"/updateCommande?id="+commande.getId()+"&reference="+commande.getReference()+"&valider="+commande.getValider();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    


    
}
    
    

