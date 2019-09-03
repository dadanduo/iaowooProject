package com.iaowoo.mobile.Ui.classification.Model;

import java.util.List;

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
 * @Description: ${todo}(分类数据)
 * @date 2018/8/28
 * @email ${18011009889@163.com}
 */
public class FenLei {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":[{"id":1,"code":"456073377145159680","name":"酒水食品","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":2,"code":"456073377161936896","name":"休闲零食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073236753416192.jpg","specificationInfos":null},{"id":3,"code":"456073377187102720","name":"果蔬生鲜","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073249537654784.jpg","specificationInfos":null},{"id":4,"code":"456073377208074240","name":"粮油副食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073296115400704.jpg","specificationInfos":null},{"id":5,"code":"456073377220657152","name":"乳饮酒速食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073325467140096.jpg","specificationInfos":null},{"id":6,"code":"456073377229045760","name":"滋补保健","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073340390473728.jpg","specificationInfos":null},{"id":29,"code":"459372140429312000","name":"瓜果饮料","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-21/459372130459451392.png","specificationInfos":null}]},{"id":7,"code":"456074162163679232","name":"日用百货","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":8,"code":"456074162176262144","name":"家居厨卫","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074024280129536.jpg","specificationInfos":null},{"id":9,"code":"456074162193039360","name":"日用清洁","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074056504967168.jpg","specificationInfos":null},{"id":10,"code":"456074162209816576","name":"家纺生活","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074139950645248.jpg","specificationInfos":null},{"id":30,"code":"463655724027740160","name":"test4","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-07-03/463655855334621184.jpg","specificationInfos":null},{"id":31,"code":"463655724044517376","name":"test2","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-07-03/463655664611229696.jpg","specificationInfos":null}]},{"id":11,"code":"456074803766362112","name":"美妆护肤","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":12,"code":"456074803787333632","name":"国产美妆","parentCode":"456074803766362112","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074776377556992.jpg","specificationInfos":null},{"id":13,"code":"456074803799916544","name":"进口美妆","parentCode":"456074803766362112","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074789761581056.jpg","specificationInfos":null}]},{"id":14,"code":"456077462346924032","name":"数码家电","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":15,"code":"456077462359506944","name":"生活电器","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077440624623616.jpg","specificationInfos":null},{"id":16,"code":"456077462376284160","name":"进口电器","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456075747782557696.jpg","specificationInfos":null},{"id":17,"code":"456077462388867072","name":"数码电子","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456075767378345984.jpg","specificationInfos":null}]},{"id":18,"code":"456077877469773824","name":"钟表珠宝","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":19,"code":"456077877486551040","name":"时尚腕表","parentCode":"456077877469773824","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077833983229952.jpg","specificationInfos":null},{"id":20,"code":"456077877507522560","name":"时尚饰品","parentCode":"456077877469773824","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077867986452480.jpg","specificationInfos":null}]},{"id":21,"code":"456078098769641472","name":"母婴玩具","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":22,"code":"456078098786418688","name":"母婴用品","parentCode":"456078098769641472","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078064288268288.jpg","specificationInfos":null},{"id":23,"code":"456078098799001600","name":"玩具图书","parentCode":"456078098769641472","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078092184584192.jpg","specificationInfos":null}]},{"id":24,"code":"456078283457429504","name":"皮具箱包","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":25,"code":"456078283474206720","name":"箱包手袋","parentCode":"456078283457429504","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078258551652352.jpg","specificationInfos":null},{"id":26,"code":"456078283486789632","name":"服饰配件","parentCode":"456078283457429504","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078273957330944.jpg","specificationInfos":null}]},{"id":27,"code":"459366734281834496","name":"test","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":28,"code":"459366734302806016","name":"test11","parentCode":"459366734281834496","img":"https://test-files.shzhuoji.com/images/category/2018-06-21/459366722680389632.png","specificationInfos":null}]}]}
     * tail : {"channel":null,"product":null,"system":null}
     * describe : 操作成功！
     * code : 00000000
     * costs : null
     */

    private HeadBean head;
    private BodyBean body;
    private TailBean tail;
    private String describe;
    private String code;
    private Object costs;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public TailBean getTail() {
        return tail;
    }

    public void setTail(TailBean tail) {
        this.tail = tail;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCosts() {
        return costs;
    }

    public void setCosts(Object costs) {
        this.costs = costs;
    }

    public static class HeadBean {
        /**
         * traceId : null
         * service : null
         * version : null
         * security : null
         * type : RESPONSE
         * direction : null
         */

        private Object traceId;
        private Object service;
        private Object version;
        private Object security;
        private String type;
        private Object direction;

        public Object getTraceId() {
            return traceId;
        }

        public void setTraceId(Object traceId) {
            this.traceId = traceId;
        }

        public Object getService() {
            return service;
        }

        public void setService(Object service) {
            this.service = service;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getSecurity() {
            return security;
        }

        public void setSecurity(Object security) {
            this.security = security;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }
    }

    public static class BodyBean {
        /**
         * comment : null
         * content : [{"id":1,"code":"456073377145159680","name":"酒水食品","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":2,"code":"456073377161936896","name":"休闲零食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073236753416192.jpg","specificationInfos":null},{"id":3,"code":"456073377187102720","name":"果蔬生鲜","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073249537654784.jpg","specificationInfos":null},{"id":4,"code":"456073377208074240","name":"粮油副食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073296115400704.jpg","specificationInfos":null},{"id":5,"code":"456073377220657152","name":"乳饮酒速食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073325467140096.jpg","specificationInfos":null},{"id":6,"code":"456073377229045760","name":"滋补保健","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073340390473728.jpg","specificationInfos":null},{"id":29,"code":"459372140429312000","name":"瓜果饮料","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-21/459372130459451392.png","specificationInfos":null}]},{"id":7,"code":"456074162163679232","name":"日用百货","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":8,"code":"456074162176262144","name":"家居厨卫","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074024280129536.jpg","specificationInfos":null},{"id":9,"code":"456074162193039360","name":"日用清洁","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074056504967168.jpg","specificationInfos":null},{"id":10,"code":"456074162209816576","name":"家纺生活","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074139950645248.jpg","specificationInfos":null},{"id":30,"code":"463655724027740160","name":"test4","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-07-03/463655855334621184.jpg","specificationInfos":null},{"id":31,"code":"463655724044517376","name":"test2","parentCode":"456074162163679232","img":"https://test-files.shzhuoji.com/images/category/2018-07-03/463655664611229696.jpg","specificationInfos":null}]},{"id":11,"code":"456074803766362112","name":"美妆护肤","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":12,"code":"456074803787333632","name":"国产美妆","parentCode":"456074803766362112","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074776377556992.jpg","specificationInfos":null},{"id":13,"code":"456074803799916544","name":"进口美妆","parentCode":"456074803766362112","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456074789761581056.jpg","specificationInfos":null}]},{"id":14,"code":"456077462346924032","name":"数码家电","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":15,"code":"456077462359506944","name":"生活电器","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077440624623616.jpg","specificationInfos":null},{"id":16,"code":"456077462376284160","name":"进口电器","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456075747782557696.jpg","specificationInfos":null},{"id":17,"code":"456077462388867072","name":"数码电子","parentCode":"456077462346924032","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456075767378345984.jpg","specificationInfos":null}]},{"id":18,"code":"456077877469773824","name":"钟表珠宝","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":19,"code":"456077877486551040","name":"时尚腕表","parentCode":"456077877469773824","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077833983229952.jpg","specificationInfos":null},{"id":20,"code":"456077877507522560","name":"时尚饰品","parentCode":"456077877469773824","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456077867986452480.jpg","specificationInfos":null}]},{"id":21,"code":"456078098769641472","name":"母婴玩具","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":22,"code":"456078098786418688","name":"母婴用品","parentCode":"456078098769641472","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078064288268288.jpg","specificationInfos":null},{"id":23,"code":"456078098799001600","name":"玩具图书","parentCode":"456078098769641472","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078092184584192.jpg","specificationInfos":null}]},{"id":24,"code":"456078283457429504","name":"皮具箱包","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":25,"code":"456078283474206720","name":"箱包手袋","parentCode":"456078283457429504","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078258551652352.jpg","specificationInfos":null},{"id":26,"code":"456078283486789632","name":"服饰配件","parentCode":"456078283457429504","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456078273957330944.jpg","specificationInfos":null}]},{"id":27,"code":"459366734281834496","name":"test","parentCode":"0","img":null,"childProductCategoryInfos":[{"id":28,"code":"459366734302806016","name":"test11","parentCode":"459366734281834496","img":"https://test-files.shzhuoji.com/images/category/2018-06-21/459366722680389632.png","specificationInfos":null}]}]
         */

        private Object comment;
        private List<ContentBean> content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * id : 1
             * code : 456073377145159680
             * name : 酒水食品
             * parentCode : 0
             * img : null
             * childProductCategoryInfos : [{"id":2,"code":"456073377161936896","name":"休闲零食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073236753416192.jpg","specificationInfos":null},{"id":3,"code":"456073377187102720","name":"果蔬生鲜","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073249537654784.jpg","specificationInfos":null},{"id":4,"code":"456073377208074240","name":"粮油副食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073296115400704.jpg","specificationInfos":null},{"id":5,"code":"456073377220657152","name":"乳饮酒速食","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073325467140096.jpg","specificationInfos":null},{"id":6,"code":"456073377229045760","name":"滋补保健","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-12/456073340390473728.jpg","specificationInfos":null},{"id":29,"code":"459372140429312000","name":"瓜果饮料","parentCode":"456073377145159680","img":"https://test-files.shzhuoji.com/images/category/2018-06-21/459372130459451392.png","specificationInfos":null}]
             */

            private int id;
            private String code;
            private String name;
            private String parentCode;
            private Object img;
            private List<ChildProductCategoryInfosBean> childProductCategoryInfos;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
            }

            public Object getImg() {
                return img;
            }

            public void setImg(Object img) {
                this.img = img;
            }

            public List<ChildProductCategoryInfosBean> getChildProductCategoryInfos() {
                return childProductCategoryInfos;
            }

            public void setChildProductCategoryInfos(List<ChildProductCategoryInfosBean> childProductCategoryInfos) {
                this.childProductCategoryInfos = childProductCategoryInfos;
            }

            public static class ChildProductCategoryInfosBean {
                /**
                 * id : 2
                 * code : 456073377161936896
                 * name : 休闲零食
                 * parentCode : 456073377145159680
                 * img : https://test-files.shzhuoji.com/images/category/2018-06-12/456073236753416192.jpg
                 * specificationInfos : null
                 */

                private int id;
                private String code;
                private String name;
                private String parentCode;
                private String img;
                private Object specificationInfos;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getParentCode() {
                    return parentCode;
                }

                public void setParentCode(String parentCode) {
                    this.parentCode = parentCode;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public Object getSpecificationInfos() {
                    return specificationInfos;
                }

                public void setSpecificationInfos(Object specificationInfos) {
                    this.specificationInfos = specificationInfos;
                }
            }
        }
    }

    public static class TailBean {
        /**
         * channel : null
         * product : null
         * system : null
         */

        private Object channel;
        private Object product;
        private Object system;

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public Object getProduct() {
            return product;
        }

        public void setProduct(Object product) {
            this.product = product;
        }

        public Object getSystem() {
            return system;
        }

        public void setSystem(Object system) {
            this.system = system;
        }
    }
}
