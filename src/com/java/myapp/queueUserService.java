/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Acer
 */
public class queueUserService {
      public static void showTime(JTextField ti) {

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                ti.setText(s.format(d));
            }
        }).start();
    }
    
}
