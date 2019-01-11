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
 * @Description: ${todo}(消息数量)
 * @date 2018/9/19
 * @email ${18011009889@163.com}
 */
@Table(name = "MessageNumber",onCreated = "")
public class MessageNumber {


    /**
     * g_g : 1
     * x_t : 1
     * j_y : 1
     * d_d : 1
     * k_f : 1
     */

    @Column(name="id",isId=true,autoGen=true,property="NOT NULL" )
    private int id;

    @Column(name="uid")
    private String uid;
    @Column(name="g_g")
    private int g_g;

    @Column(name="x_t")
    private int x_t;

    @Column(name="j_y")
    private int j_y;

    @Column(name="d_d")
    private int d_d;

    @Column(name="k_f")
    private int k_f;


    public int getG_g() {
        return g_g;
    }

    public void setG_g(int g_g) {
        this.g_g = g_g;
    }

    public int getX_t() {
        return x_t;
    }

    public void setX_t(int x_t) {
        this.x_t = x_t;
    }

    public int getJ_y() {
        return j_y;
    }

    public void setJ_y(int j_y) {
        this.j_y = j_y;
    }

    public int getD_d() {
        return d_d;
    }

    public void setD_d(int d_d) {
        this.d_d = d_d;
    }

    public int getK_f() {
        return k_f;
    }

    public void setK_f(int k_f) {
        this.k_f = k_f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
