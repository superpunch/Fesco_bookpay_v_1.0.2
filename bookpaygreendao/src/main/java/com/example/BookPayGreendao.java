package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class BookPayGreendao {

    public  static  void  main(String[] args) throws Exception {

        Schema schema= new Schema(6,"me.bookpay.greendao");

        //  add(schema);
        addComusmption(schema);
        addImagePhotos(schema);
        //       new DaoGenerator().generateAll(schema, "/Users/tangqi/android-dev/AndroidStudioProjects/MyGreenDAO/app/src/main/java-gen");
        new DaoGenerator().generateAll(schema, "/AndroidStudioProjects_workspace001/Fesco_bookpay_v_1.0/app/src/main/java-gen");


    }

    private static void add(Schema schema) {
        //一个实体（类）就关联到数据库中的一张表,表名为[Contact]即类名
        Entity contact=schema.addEntity("Contact");
        Entity photos=schema.addEntity("AddressBook");
        // 可以重新给表命名
        // contact.setTableName("NODE");
    }
    private static void addComusmption(Schema schema) {
        Entity mData = schema.addEntity("Consume");
        mData.addIdProperty().primaryKey().autoincrement();
        mData.addStringProperty("detail_Id");
        mData.addStringProperty("typeIcon");//"fontIcon"
        mData.addStringProperty("typeName");
        mData.addStringProperty("typeID");
        mData.addStringProperty("pic_Ids");//图片ID  "71,72 "
        mData.addStringProperty("money");
        mData.addStringProperty("starDate");
        mData.addStringProperty("endDate");  //addDateProperty
        mData.addStringProperty("city");
        mData.addIntProperty("count");
        mData.addStringProperty("description");

    }
    private static void addImagePhotos(Schema schema) {
        Entity mData = schema.addEntity("ImagePhotos");
        mData.addIdProperty().primaryKey().autoincrement();
        mData.addStringProperty("version_No");
        mData.addStringProperty("emp_Id");//"fontIcon"
        mData.addStringProperty("image_Url");


    }
}