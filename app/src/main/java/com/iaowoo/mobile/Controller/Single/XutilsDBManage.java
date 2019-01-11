package com.iaowoo.mobile.Controller.Single;

import com.iaowoo.mobile.Utils.DBmanage.DatabaseOpenHelper;
import com.iaowoo.mobile.Utils.LogPrint;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(数据库管理)
 * @date 2018/7/11
 */
public class XutilsDBManage {
    private static XutilsDBManage xutilsDBManage = null;
    private DbManager db;
    private boolean succeed;//操作是否成功，true是，false否
    boolean idDesc = true;//是否倒序，默认false
    private List list = null;
    /**
     *
     */
    public XutilsDBManage() {
    }
    /**
     * 单列模式
     *
     * @return 双重校验锁
     */
    public static XutilsDBManage getInstance() {
        if (xutilsDBManage == null) {
            synchronized (XutilsDBManage.class) {
                if (xutilsDBManage == null) {
                    xutilsDBManage = new XutilsDBManage();
                }
            }
        }
        return xutilsDBManage;
    }
    public void Instance() {
        db = DatabaseOpenHelper.getInstance();
    }

    /**
     * 将表的实体实例存进数据库
     * 保存新增
     */
    public <T>boolean saveTabble (T table) {
        try {
            db.save(table);
            LogPrint.printError("数据存储成功");
            return  true;
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
            return false;
        }
    }

    public <T>boolean searchDate(String uid,Class<T> c){
        LogPrint.printError("uid为："+uid);
        try {
            list = db.selector(c).where("uid", "=",uid).findAll();
            if(list!=null){
                if(list.size()==0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
        }
        return false;
    }

    /**
     * 查看账号表是否存在
     * @param name
     * @param c
     * @param <T>
     * @return
     */
    public <T>boolean searchD(String name,Class<T> c){
        LogPrint.printError("name为："+name);
        try {
            list = db.selector(c).where("name", "=",name).findAll();
            if(list!=null){
                if(list.size()==0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
        }
        return false;
    }

    /**
     * 查看账号表是否存在
     * @param name
     * @param c
     * @return
     */
    public <T>Object searchName(String name, Class<T> c){
        LogPrint.printError("name为："+name);
        try {
            list = db.selector(c).where("name", "=",name).findAll();
            if(list!=null){
                if(list.size()!=0){
                    return list.get(0);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
        }
        return null;
    }

    /**
     * 查看账号表是否存在
     * @param groudId
     * @param c
     * @param <T>
     * @return
     */
    public <T>boolean searchByGroudID(int groudId,Class<T> c){
        LogPrint.printError("GroudID为："+groudId);
        try {
            list = db.selector(c).where("groudId", "=",groudId).findAll();
            if(list!=null){
                if(list.size()==0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
        }
        return false;
    }

    public <T>List searchByUid(String uid,Class<T> c){
        LogPrint.printError("uid为："+uid);
        try {
           list = db.selector(c).findAll();
            if(list!=null){
               return list;
            }else{
                return null;
            }
        } catch (DbException e) {
            e.printStackTrace();
            LogPrint.printError("异常了"+e.getMessage());
        }
        return null;
    }

    /**
     * 新增或更新
     *
     * @param
     */
    public <T>boolean saveOrUpdate(T table) {
        try {
            db.saveOrUpdate(table);
            LogPrint.printError("数据更新成功");
            return true;
        } catch (DbException e) {
            e.printStackTrace();
            return  false;
        }
    }

    /**
     * 保存list数据
     * @param tables
     * @param <T>
     * @return
     */
    public <T> boolean saveListTabble(List<T> tables){
        for(int i=0;i<tables.size();i++){
            try {
                db.save(tables.get(i));
            } catch (DbException e) {
                e.printStackTrace();
                return  false;
            }
        }
        return  true;
    }

    /**
     * 读取表里所有信息
     *
     * @return
     */
    public <T>List loadTableAll(Class<T> c) {
        try {
            list = db.selector(c).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 读取表里所有信息
     *
     * @return
     */
    public <T>int loadTableAllSize(Class<T> c) {
        try {
            list = db.selector(c).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list==null?0:list.size();
    }
    /**
     * 根据id查中间数据
     * @param ids
     * @param c
     * @param <T>
     * @return
     */
    public <T>List loadTableByIdsBetween(int[] ids,Class<T> c) {
        try {
            list = db.selector(c).where("id", "between", ids).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public <T>boolean deleteById(int id,Class<T> c) {
        try {
            db.deleteById(c,id);
            succeed = true;
        } catch (DbException e) {
            succeed = false;
            e.printStackTrace();
        }
        return succeed;
    }

    /**
     * 删除表
     *
     * @throws DbException
     */
    public <T>boolean delTable(Class<T> c)  {
        try {
            db.dropTable(c);
            return  true;
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据条件查询表中的数据
     *
     * @param
     * @throws DbException
     */
    public <T>List selelctDB(Class<T> c, int start, int over,String uid) {
        //查询数据库表中第一条数据
        //添加查询条件进行查询
        List all2 = null;
        try {
            WhereBuilder b = WhereBuilder.b();
            LogPrint.printError("uid："+uid);
//            b.and("uid","==",uid);
            all2 = db.selector(c).where(b).findAll();//findAll()：查询所有结果

        } catch (DbException e) {
            e.printStackTrace();
        }
        return all2;
    }

    /**
     * 查询数据库表中的第一条数据
     * @param d
     * @param c
     * @param <T>
     * @return
     */
    public <T>T selectFistoneData(T d,Class<T> c){
        try {
            d  = db.findFirst(c);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 修改表中的数据
     *
     * @throws DbException
     */
    public <T>void updateTable(T d){
        try {
            db.saveOrUpdate(d);
            //        d.setName("zhansan4");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
