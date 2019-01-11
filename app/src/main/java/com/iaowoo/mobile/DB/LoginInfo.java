package com.iaowoo.mobile.DB;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

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
 * @Description: ${todo}(账号切换)
 * @date 2018/11/19
 * @email ${18011009889@163.com}
 */
@Table(name = "LoginInfo",onCreated = "")
public class LoginInfo {
    @Column(name="id",isId=true,autoGen=true,property="NOT NULL" )
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="passWord")
    private String passWord;

    @Column(name="headImage")
    private String headImage;

    @Column(name="groudId")
    private int groudId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public int getGroudId() {
        return groudId;
    }

    public void setGroudId(int groudId) {
        this.groudId = groudId;
    }
}
