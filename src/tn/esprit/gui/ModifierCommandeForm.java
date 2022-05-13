/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import tn.esprit.entities.Commande;
import tn.esprit.services.Services_commande;

/**
 *
 * @author ameni
 */
public class ModifierCommandeForm extends BaseForm{
    Form current;
    public ModifierCommandeForm(Resources res , Commande c) {
        
         super("Artisanet",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Modification");
        getContentPane().setScrollVisible(false);
        
        
//        super.addSideMenu(res);
        
    
               TextField reference = new TextField(String.valueOf(c.getReference()) , "Reference" , 20 , TextField.ANY);
                       TextField valider = new TextField(String.valueOf(c.getValider()) , "Valider" , 20 , TextField.ANY);
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox referenceCombo = new ComboBox();
        
        referenceCombo.addItem("Non Traiter");
        
        referenceCombo.addItem("Traiter");
        
        if(c.getReference() == 0 ) {
            referenceCombo.setSelectedIndex(0);
        }
        else 
            referenceCombo.setSelectedIndex(1);
        
        
        
        
        
        reference.setUIID("NewsTopLine");
        valider.setUIID("NewsTopLine");
       
        
         reference.setSingleLineTextArea(true);
        valider.setSingleLineTextArea(true);
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
          
           
           if(referenceCombo.getSelectedIndex() == 0 ) {
               c.setReference(0);
           }
           else 
               c.setReference(1);
      
       
       //appel fonction modfier reclamation men service
       
       if(Services_commande.getInstance().modifierCommande(c)) { // if true
           new ListCommandeForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCommandeForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(reference),
                createLineSeparator(),
                new FloatingHint(valider),
                createLineSeparator(),
                referenceCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
