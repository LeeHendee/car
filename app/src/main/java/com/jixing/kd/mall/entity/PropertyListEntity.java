package com.jixing.kd.mall.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/17.
 * Used to :
 */

public class PropertyListEntity {


    /**
     * err_code : 0
     * err_message : OK
     * result : {"spec_list":[{"id":"bc27bb48e6db11e79bd5180373af859e","category_id":"2","group_id":"2","filter":"A","name":"尺寸","sort":1,"items":[{"id":"49","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"13英寸"},{"id":"50","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"14英寸"},{"id":"51","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"15英寸"},{"id":"52","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"16英寸"},{"id":"53","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"17英寸"},{"id":"54","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"18英寸"},{"id":"55","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"19英寸"},{"id":"56","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"21英寸"},{"id":"57","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"22英寸"},{"id":"58","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"其他"}]},{"id":"d79e54289f4311e79954180373af859e","category_id":"2","group_id":"2","filter":"A","name":"车型类别","sort":2,"items":[{"id":"73","spec_id":"d79e54289f4311e79954180373af859e","item":"SUV轮胎"},{"id":"74","spec_id":"d79e54289f4311e79954180373af859e","item":"卡客车轮胎"},{"id":"75","spec_id":"d79e54289f4311e79954180373af859e","item":"轿车"}]},{"id":"fe3e0e27e6d911e79bd5180373af859e","category_id":"2","group_id":"2","filter":"A","name":"花纹性能","sort":3,"items":[{"id":"28","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"经济耐用型"},{"id":"29","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"节能环保型"},{"id":"30","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"越野型"},{"id":"31","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"运动操控型"},{"id":"32","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"静音舒适型"}]},{"id":"1d7994389f4311e79954180373af859e","category_id":"2","group_id":"2","filter":"A","name":"扁平比","sort":4,"items":[{"id":"33","spec_id":"1d7994389f4311e79954180373af859e","item":"30"},{"id":"34","spec_id":"1d7994389f4311e79954180373af859e","item":"35"},{"id":"35","spec_id":"1d7994389f4311e79954180373af859e","item":"40"},{"id":"36","spec_id":"1d7994389f4311e79954180373af859e","item":"45"},{"id":"37","spec_id":"1d7994389f4311e79954180373af859e","item":"50"},{"id":"38","spec_id":"1d7994389f4311e79954180373af859e","item":"55"},{"id":"39","spec_id":"1d7994389f4311e79954180373af859e","item":"60"},{"id":"40","spec_id":"1d7994389f4311e79954180373af859e","item":"65"},{"id":"41","spec_id":"1d7994389f4311e79954180373af859e","item":"70"},{"id":"42","spec_id":"1d7994389f4311e79954180373af859e","item":"75"},{"id":"43","spec_id":"1d7994389f4311e79954180373af859e","item":"80"},{"id":"44","spec_id":"1d7994389f4311e79954180373af859e","item":"85"}]},{"id":"3d664d53e6da11e79bd5180373af859e","category_id":"2","group_id":"2","filter":"A","name":"轮胎特性","sort":5,"items":[{"id":"45","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"半热熔轮胎"},{"id":"46","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"防爆胎"},{"id":"47","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"雪地胎"},{"id":"48","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"其他"}]},{"id":"a5a68f32e6db11e79bd5180373af859e","category_id":"2","group_id":"2","filter":"A","name":"胎面宽度","sort":6,"items":[{"id":"59","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"155"},{"id":"60","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"165"},{"id":"61","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"175"},{"id":"62","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"185"},{"id":"63","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"195"},{"id":"64","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"205"},{"id":"65","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"215"},{"id":"66","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"225"},{"id":"67","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"235"},{"id":"68","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"245"},{"id":"69","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"255"},{"id":"70","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"265"},{"id":"71","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"275"},{"id":"72","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"其他"}]}],"price_list":[{"id":"68776e70e95a11e7bb5700163e063fa6","category_id":"0","group_id":"0","filter":"A","name":"价格","sort":0,"items":[{"id":"1","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"100"},{"id":"2","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"300"},{"id":"3","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"500"},{"id":"4","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"1000"}]}],"brand_list":[{"id":"dc1dab999f3d11e79954180373af859e","category_id":"2","cn_name":"米其林","en_name":"MICHELIN","create_time":1519376187000,"logo":"http://114.215.71.170:81/shopping/brandlogo/1520471632852.jpg","descrption":"全球轮胎科技的领导者，常年致力于轮胎科技的创新与研发。米其林轮胎为消费者带来静音舒适、运动操控的各种驾乘体验，同时为消费者提供防爆轮胎、冬季/雪地轮胎等产品，满足不同车型、不同季节的驾驶需求。遍布全国的庞大零售商网络随时为您提供专业的服务。"},{"id":"dc1daf7a9f3d11e79954180373af859e","category_id":"2","cn_name":"马牌","en_name":"Continental","create_time":1519376187000,"logo":"logo.png","descrption":"马牌"},{"id":"dc1db0299f3d11e79954180373af859e","category_id":"2","cn_name":"普利司通","en_name":"Bridgestone","create_time":1519376187000,"logo":"logo.png","descrption":"普利司通"},{"id":"dc1db09a9f3d11e79954180373af859e","category_id":"2","cn_name":"邓禄普","en_name":"DUNLOP","create_time":1519376187000,"logo":"logo.png","descrption":"邓禄普"},{"id":"dc1db1019f3d11e79954180373af859e","category_id":"2","cn_name":"固特异","en_name":"Goodyear","create_time":1519376187000,"logo":"logo.png","descrption":"固特异"},{"id":"dc1db1579f3d11e79954180373af859e","category_id":"2","cn_name":"佳通轮胎","en_name":"Giti","create_time":1519376187000,"logo":"logo.png","descrption":"佳通轮胎"},{"id":"dc1db1a99f3d11e79954180373af859e","category_id":"2","cn_name":"韩泰","en_name":"Hankook","create_time":1519376187000,"logo":"logo.png","descrption":"韩泰"},{"id":"dc1db1ff9f3d11e79954180373af859e","category_id":"2","cn_name":"倍耐力","en_name":"Pirelli","create_time":1519376187000,"logo":"logo.png","descrption":"倍耐力"},{"id":"dc1db2a59f3d11e79954180373af859e","category_id":"2","cn_name":"玛吉斯","en_name":"","create_time":1519376187000,"logo":"logo.png","descrption":"玛吉斯"},{"id":"dc1db2f89f3d11e79954180373af859e","category_id":"2","cn_name":"朝阳","en_name":"Continental","create_time":1519376187000,"logo":"logo.png","descrption":"朝阳"},{"id":"dc1db3689f3d11e79954180373af859e","category_id":"2","cn_name":"锦湖","en_name":"Bridgestone","create_time":1519376187000,"logo":"logo.png","descrption":"锦湖"},{"id":"dc1db3be9f3d11e79954180373af859e","category_id":"2","cn_name":"优科豪马","en_name":"yokohama","create_time":1519376187000,"logo":"logo.png","descrption":"优科豪马"},{"id":"dc1db41b9f3d11e79954180373af859e","category_id":"2","cn_name":"玲珑轮胎","en_name":"","create_time":1519376187000,"logo":"logo.png","descrption":"玲珑轮胎"}]}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "PropertyListEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", result=" + result +
                '}';
    }

    public static PropertyListEntity objectFromData(String str) {

        return new Gson().fromJson(str, PropertyListEntity.class);
    }

    public static PropertyListEntity objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PropertyListEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PropertyListEntity> arrayPropertyListEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<PropertyListEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PropertyListEntity> arrayPropertyListEntityFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PropertyListEntity>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<SpecListBean> spec_list;
        private List<PriceListBean> price_list;
        private List<BrandListBean> brand_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "spec_list=" + spec_list +
                    ", price_list=" + price_list +
                    ", brand_list=" + brand_list +
                    '}';
        }

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static ResultBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ResultBean> arrayResultBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ResultBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public List<SpecListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpecListBean> spec_list) {
            this.spec_list = spec_list;
        }

        public List<PriceListBean> getPrice_list() {
            return price_list;
        }

        public void setPrice_list(List<PriceListBean> price_list) {
            this.price_list = price_list;
        }

        public List<BrandListBean> getBrand_list() {
            return brand_list;
        }

        public void setBrand_list(List<BrandListBean> brand_list) {
            this.brand_list = brand_list;
        }

        public static class SpecListBean {
            /**
             * id : bc27bb48e6db11e79bd5180373af859e
             * category_id : 2
             * group_id : 2
             * filter : A
             * name : 尺寸
             * sort : 1
             * items : [{"id":"49","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"13英寸"},{"id":"50","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"14英寸"},{"id":"51","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"15英寸"},{"id":"52","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"16英寸"},{"id":"53","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"17英寸"},{"id":"54","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"18英寸"},{"id":"55","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"19英寸"},{"id":"56","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"21英寸"},{"id":"57","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"22英寸"},{"id":"58","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"其他"}]
             */

            private String id;
            private String category_id;
            private String group_id;
            private String filter;
            private String name;
            private int sort;
            private List<ItemsBean> items;

            @Override
            public String toString() {
                return "SpecListBean{" +
                        "id='" + id + '\'' +
                        ", category_id='" + category_id + '\'' +
                        ", group_id='" + group_id + '\'' +
                        ", filter='" + filter + '\'' +
                        ", name='" + name + '\'' +
                        ", sort=" + sort +
                        ", items=" + items +
                        '}';
            }

            public static SpecListBean objectFromData(String str) {

                return new Gson().fromJson(str, SpecListBean.class);
            }

            public static SpecListBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), SpecListBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<SpecListBean> arraySpecListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SpecListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<SpecListBean> arraySpecListBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<SpecListBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getFilter() {
                return filter;
            }

            public void setFilter(String filter) {
                this.filter = filter;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * id : 49
                 * spec_id : bc27bb48e6db11e79bd5180373af859e
                 * item : 13英寸
                 */

                private String id;
                private String spec_id;
                private String item;
                private boolean isSelected;

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                @Override
                public String toString() {
                    return "ItemsBean{" +
                            "id='" + id + '\'' +
                            ", spec_id='" + spec_id + '\'' +
                            ", item='" + item + '\'' +
                            ", isSelected='" + isSelected + '\'' +
                            '}';
                }

                public static ItemsBean objectFromData(String str) {

                    return new Gson().fromJson(str, ItemsBean.class);
                }

                public static ItemsBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ItemsBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ItemsBean> arrayItemsBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ItemsBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ItemsBean> arrayItemsBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ItemsBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSpec_id() {
                    return spec_id;
                }

                public void setSpec_id(String spec_id) {
                    this.spec_id = spec_id;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }
            }
        }

        public static class PriceListBean {
            /**
             * id : 68776e70e95a11e7bb5700163e063fa6
             * category_id : 0
             * group_id : 0
             * filter : A
             * name : 价格
             * sort : 0
             * items : [{"id":"1","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"100"},{"id":"2","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"300"},{"id":"3","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"500"},{"id":"4","spec_id":"68776e70e95a11e7bb5700163e063fa6","item":"1000"}]
             */

            private String id;
            private String category_id;
            private String group_id;
            private String filter;
            private String name;
            private int sort;
            private List<ItemsBeanX> items;

            @Override
            public String toString() {
                return "PriceListBean{" +
                        "id='" + id + '\'' +
                        ", category_id='" + category_id + '\'' +
                        ", group_id='" + group_id + '\'' +
                        ", filter='" + filter + '\'' +
                        ", name='" + name + '\'' +
                        ", sort=" + sort +
                        ", items=" + items +
                        '}';
            }

            public static PriceListBean objectFromData(String str) {

                return new Gson().fromJson(str, PriceListBean.class);
            }

            public static PriceListBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), PriceListBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<PriceListBean> arrayPriceListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<PriceListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<PriceListBean> arrayPriceListBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<PriceListBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getFilter() {
                return filter;
            }

            public void setFilter(String filter) {
                this.filter = filter;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ItemsBeanX> getItems() {
                return items;
            }

            public void setItems(List<ItemsBeanX> items) {
                this.items = items;
            }

            public static class ItemsBeanX {
                /**
                 * id : 1
                 * spec_id : 68776e70e95a11e7bb5700163e063fa6
                 * item : 100
                 */

                private String id;
                private String spec_id;
                private String item;

                @Override
                public String toString() {
                    return "ItemsBeanX{" +
                            "id='" + id + '\'' +
                            ", spec_id='" + spec_id + '\'' +
                            ", item='" + item + '\'' +
                            '}';
                }

                public static ItemsBeanX objectFromData(String str) {

                    return new Gson().fromJson(str, ItemsBeanX.class);
                }

                public static ItemsBeanX objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ItemsBeanX.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ItemsBeanX> arrayItemsBeanXFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ItemsBeanX>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ItemsBeanX> arrayItemsBeanXFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ItemsBeanX>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSpec_id() {
                    return spec_id;
                }

                public void setSpec_id(String spec_id) {
                    this.spec_id = spec_id;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }
            }
        }

        public static class BrandListBean {
            /**
             * id : dc1dab999f3d11e79954180373af859e
             * category_id : 2
             * cn_name : 米其林
             * en_name : MICHELIN
             * create_time : 1519376187000
             * logo : http://114.215.71.170:81/shopping/brandlogo/1520471632852.jpg
             * descrption : 全球轮胎科技的领导者，常年致力于轮胎科技的创新与研发。米其林轮胎为消费者带来静音舒适、运动操控的各种驾乘体验，同时为消费者提供防爆轮胎、冬季/雪地轮胎等产品，满足不同车型、不同季节的驾驶需求。遍布全国的庞大零售商网络随时为您提供专业的服务。
             */

            private String id;
            private String category_id;
            private String cn_name;
            private String en_name;
            private long create_time;
            private String logo;
            private String descrption;

            @Override
            public String toString() {
                return "BrandListBean{" +
                        "id='" + id + '\'' +
                        ", category_id='" + category_id + '\'' +
                        ", cn_name='" + cn_name + '\'' +
                        ", en_name='" + en_name + '\'' +
                        ", create_time=" + create_time +
                        ", logo='" + logo + '\'' +
                        ", descrption='" + descrption + '\'' +
                        '}';
            }

            public static BrandListBean objectFromData(String str) {

                return new Gson().fromJson(str, BrandListBean.class);
            }

            public static BrandListBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), BrandListBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<BrandListBean> arrayBrandListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<BrandListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<BrandListBean> arrayBrandListBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<BrandListBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCn_name() {
                return cn_name;
            }

            public void setCn_name(String cn_name) {
                this.cn_name = cn_name;
            }

            public String getEn_name() {
                return en_name;
            }

            public void setEn_name(String en_name) {
                this.en_name = en_name;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getDescrption() {
                return descrption;
            }

            public void setDescrption(String descrption) {
                this.descrption = descrption;
            }
        }
    }
}
