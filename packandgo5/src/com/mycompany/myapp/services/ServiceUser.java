/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raslen
 */
public class ServiceUser {

    public static ServiceUser instance = null;

    public static boolean resultOk = true;
    String json;
    // init conncect request
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();
    }
    ArrayList u = new ArrayList<User>();

    public boolean addUser(User U, Resources res) {
        System.out.println(U);
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + U.getName() + "&status=" + U.getStatus();
        String url = Statics.BASE_URL + "add?FirstName=" + U.getFirstName() + "&LastName=" + U.getLastName()
                + "&Email=" + U.getEmail() + "&Password=" + U.getPassword() + "&Number=" + U.getNumber()
                + "&Birthday=" + U.getBirthday();

        req.setUrl(url);

        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public void signup(TextField FirstName, TextField password, TextField email, TextField lastname, ComboBox<String> roles, Resources res) {

        String url = Statics.BASE_URL + "/user/signup?username=" + FirstName.getText().toString() + "&email=" + email.getText().toString()
                + "&password=" + password.getText().toString() + "&roles=" + roles.getSelectedItem().toString();

        req.setUrl(url);

        //Control saisi
        if (FirstName.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField
            String responseData = new String(data);//ba3dika na5o content

            System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField username, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/user/signin?username=" + username.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setEmail(user.get("email").toString());

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public String getPasswordByEmail(String email, Resources rs) {

        String url = Statics.BASE_URL + "/user/getPasswordByEmail?email=" + email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            json = new String(req.getResponseData()) + "";

            try {

                System.out.println("data ==" + json);

                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

    public void UpdateUser(TextField email, TextField password, TextField firstname, TextField lastname, Resources res) {
        int id = SessionManager.getId();
        String url = "http://127.0.0.1:8000/user/update-user=" + id + "&email=" + email.getText().toString() + "&password=" + password.getText().toString()
                + "&firstname=" + firstname.getText().toString() + "&lastname=" + lastname.getText().toString();

        req.setUrl(url);
        // vrify form if needed --- here ---

        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.print("data ===>" + responseData);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        // new ProfileForm(res).show() ;
    }

    public User UserDetails() {

        User user = new User();
        int id = SessionManager.getId();   // get id saved in SessionManager
        String url = "http://127.0.0.1:8000/user/get-user=" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                float uid = Float.parseFloat(obj.get("id").toString());
                user.setId((int) uid);

                user.setEmail(obj.get("email").toString());
                user.setPassword(obj.get("password").toString());
                user.setFirstName(obj.get("firstName").toString());
                user.setLastName(obj.get("lastName").toString());
            } catch (IOException ex) {
                System.out.println("catch error");
            }
            System.out.println("Data 2 ===>" + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

    public ArrayList<User> affichageUser() {
        ArrayList<User> result = new ArrayList<>();

        String url = Statics.BASE_URL + "get-all";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUser.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        User re = new User();

                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());

                        String email = obj.get("email").toString();

                        String FirstName = obj.get("username").toString();
                        String LastName = obj.get("password").toString();

                        re.setId((int) id);
                        re.setEmail(email);
                        re.setFirstName(FirstName);
                        re.setLastName(LastName);

                        //Date
                        //String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().lastIndexOf("}"));
                        // Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //  String dateString = formatter.format(currentTime);
                        result.add(re);
// re.setDate(dateString);

//                     insert data into ArrayList result
//                       result.addUser(re);
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }

    public boolean deleteUser(int id) {
        String url = Statics.BASE_URL + "/deleteReclamation?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) productsListJson.get("root");
            for (Map<String, Object> obj : list) {
                User p = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                String Email = obj.get("Email").toString();
                p.setEmail(Email);
                String FirstName = obj.get("FirstName").toString();
                p.setFirstName(FirstName);
                String LastName = obj.get("LastName").toString();
                p.setLastName(LastName);
                int Number = Integer.parseInt(obj.get("Number").toString());
                p.setNumber((int) Number);

                u.add(p);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public ArrayList<User> getAllUsers() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "get-all";
        //System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                u = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        /*
    public User loginUser(user u) {
        String url = Statics.BASE_URL + "/signinmobile?email=" + u.getEmail() + "&password=" + u.getPassword();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";

            try {
                if (json.equals("Echec")) {
                    //Dialog.show("Echec", "Veuillez verifier vos informations","OK" ,null);
                    System.out.println("user matejbedech");
                } else {

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    if (user.size() > 0) {
                        u.setId(((int) Float.parseFloat(user.get("id").toString())));
                        u.setLastName(user.get("LastName").toString());
                        u.setFirstName(user.get("FirstName").toString());

                        // new HomeForm(r).show();
                        System.out.println("Bienvenue");

                    }
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
         */
        NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }

}
