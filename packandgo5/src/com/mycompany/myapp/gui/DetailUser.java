/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author raslen
 */
public class DetailUser extends BaseForm {

    BaseForm current;

    public DetailUser(User p, Resources res) {
        super(new BorderLayout());

        //ajout image
        //ajout informations
        Label email = new Label("Object : " + p.getEmail());
        Label firstname = new Label("Description : " + p.getFirstName());
        Label lastname = new Label("Type : " + p.getLastName());

        addAll(email, firstname, lastname);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListUser(previous).showBack());

    }

}
