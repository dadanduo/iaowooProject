package com.iaowoo.mobile.Utils.DBmanage;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Utils.LogPrint;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}(数据库帮助类)
 * @date ${date}
 */
public class DatabaseOpenHelper {

    private DbManager.DaoConfig daoConfig;
    private static DbManager db;
    private final int VERSION = 1;

    private DatabaseOpenHelper() {
        LogPrint.printError("数据库路径:"+ ZApplication.SD_PATH);
        daoConfig = new DbManager.DaoConfig()
                .setDbName(ZApplication.DATABASE_NAME)
                .setDbVersion(VERSION)
//                .setDbDir(new File(Papplication.SD_PATH)) // 数据库存储路径
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                        //开启WAL, 对写入加速提升巨大(作者原话)
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //数据库升级操作TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
//                        try {
//                            db.addColumn(Child.class, "REGTIME");//Child表，新增列名。记得修改之后，VERSION增加数值
//                            //db.saveOrUpdate(Child.class);
//                        } catch (DbException e) {
//                            e.printStackTrace();
//                        }
                    }
                });
        db = x.getDb(daoConfig);
    }
    public static DbManager getInstance() {
        if (db == null) {
            DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper();
        }
        return db;
    }
}
