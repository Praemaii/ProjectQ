package com.java.myapp;

import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;



public class userDao {
    static DBCollection table;
 public static void getPassanger(JTextField ti) {
        BasicDBObject document = new BasicDBObject();
        MongoClient mongo;

        //เอาข้อมูล user มาแสดงที่ combobox
        try {
            mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("mini");
            DBCollection table = db.getCollection("passenger");

            DBCursor c = table.find();
            int i = 0;
            while (c.hasNext()) {
                i++;

                String Row = c.next().toString();
                JSONObject obj = new JSONObject(Row);
                cb.addItem(obj.getString("Username"));

            } 
        } catch (Exception e) {

        }
    }
 public static void addUser(JTextField ti,JTextField n,JRadioButton g1,JRadioButton r1,JSONObject Data){
     BasicDBObject document = new BasicDBObject();
        MongoClient mongo;
        try {
            mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("mini");
             table = db.getCollection("queues");
        } catch (UnknownHostException ex) {
            
        }
        //เมื่อกดเข้าต่อคิว ข้อมูลจะบันทึกลงฐานข้อมูล
        try{
        document.put("FirstTime", ti.getText());
        document.put("Number", n.getText());
        document.put("ID CARD", Data.getString("ID CARD"));
        document.put("passenger", cb.getItemCount());
        }catch(Exception e){
            
        }

        if (g1.isSelected()) {
            document.put("passenger", g1.getText());
        } else {
            document.put("passenger", r1.getText());
        }
        table.insert(document);
        
 }
 
    
}