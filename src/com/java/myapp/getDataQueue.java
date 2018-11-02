/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myapp;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

/**
 *
 * @author Acer
 */
public class getDataQueue implements Runnable {
    private JTable DisplayTB;
    private DefaultTableModel model;
    private JFrame F;
    @Override
    public void run() {
        while(true){
        model = new DefaultTableModel();
        model.addColumn("#");
        model.addColumn("FirstTime");
        model.addColumn("Number");
        model.addColumn("ID CARD");
        model.addColumn("passenger");
        BasicDBObject document = new BasicDBObject();
        MongoClient mongo;
        
        try {
            mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("mini");
            DBCollection table = db.getCollection("queues");

           
                
            DBCursor c = table.find();
            int i =0;
            while (c.hasNext()) {
                i++;
                String Row = c.next().toString();
                JSONObject obj = new JSONObject(Row);
                System.out.println(Row);
                model.addRow(new Object[]{
                    i,
                    obj.getString("FirstTime"),
                    obj.getString("Number"),
                    obj.getString("ID CARD"),
                    obj.getString("passenger")
                });
            }
            

        } catch (Exception ex) {

        }
        DisplayTB.setModel(model);
//        F.validate();
//        F.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(getDataQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    public getDataQueue(JTable DisplayTB,JFrame F) {
        this.DisplayTB = DisplayTB;
        this.F = F;
    }
    
    
    
}
