package com.example.gtercn.car.mall.entity;

import android.widget.TextView;

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
     * result : {"spec_list":[{"id":"bc27bb48e6db11e79bd5180373af859e","category_id":"7","group_id":"1","filter":"A","name":"尺寸","sort":1,"items":[{"id":"22","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"13英寸"},{"id":"23","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"14英寸"},{"id":"24","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"15英寸"},{"id":"25","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"16英寸"},{"id":"26","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"17英寸"},{"id":"27","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"18英寸"},{"id":"28","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"19英寸"},{"id":"29","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"21英寸"},{"id":"30","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"22英寸"},{"id":"31","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"其他"}]},{"id":"d79e54289f4311e79954180373af859e","category_id":"7","group_id":"1","filter":"A","name":"车型类别","sort":2,"items":[{"id":"46","spec_id":"d79e54289f4311e79954180373af859e","item":"SUV轮胎"},{"id":"47","spec_id":"d79e54289f4311e79954180373af859e","item":"卡客车轮胎"},{"id":"48","spec_id":"d79e54289f4311e79954180373af859e","item":"轿车"}]},{"id":"fe3e0e27e6d911e79bd5180373af859e","category_id":"7","group_id":"1","filter":"A","name":"花纹性能","sort":3,"items":[{"id":"1","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"经济耐用型"},{"id":"2","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"节能环保型"},{"id":"3","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"越野型"},{"id":"4","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"运动操控型"},{"id":"5","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"静音舒适型"}]},{"id":"1d7994389f4311e79954180373af859e","category_id":"7","group_id":"1","filter":"A","name":"扁平比","sort":4,"items":[{"id":"6","spec_id":"1d7994389f4311e79954180373af859e","item":"30"},{"id":"7","spec_id":"1d7994389f4311e79954180373af859e","item":"35"},{"id":"8","spec_id":"1d7994389f4311e79954180373af859e","item":"40"},{"id":"9","spec_id":"1d7994389f4311e79954180373af859e","item":"45"},{"id":"10","spec_id":"1d7994389f4311e79954180373af859e","item":"50"},{"id":"11","spec_id":"1d7994389f4311e79954180373af859e","item":"55"},{"id":"12","spec_id":"1d7994389f4311e79954180373af859e","item":"60"},{"id":"13","spec_id":"1d7994389f4311e79954180373af859e","item":"65"},{"id":"14","spec_id":"1d7994389f4311e79954180373af859e","item":"70"},{"id":"15","spec_id":"1d7994389f4311e79954180373af859e","item":"75"},{"id":"16","spec_id":"1d7994389f4311e79954180373af859e","item":"80"},{"id":"17","spec_id":"1d7994389f4311e79954180373af859e","item":"85"}]},{"id":"3d664d53e6da11e79bd5180373af859e","category_id":"7","group_id":"1","filter":"A","name":"轮胎特性","sort":5,"items":[{"id":"18","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"半热熔轮胎"},{"id":"19","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"防爆胎"},{"id":"20","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"雪地胎"},{"id":"21","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"其他"}]},{"id":"a5a68f32e6db11e79bd5180373af859e","category_id":"7","group_id":"1","filter":"A","name":"胎面宽度","sort":6,"items":[{"id":"32","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"155"},{"id":"33","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"165"},{"id":"34","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"175"},{"id":"35","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"185"},{"id":"36","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"195"},{"id":"37","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"205"},{"id":"38","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"215"},{"id":"39","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"225"},{"id":"40","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"235"},{"id":"41","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"245"},{"id":"42","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"255"},{"id":"43","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"265"},{"id":"44","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"275"},{"id":"45","spec_id":"a5a68f32e6db11e79bd5180373af859e","item":"其他"}]}]}
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

        public List<SpecListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpecListBean> spec_list) {
            this.spec_list = spec_list;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "spec_list=" + spec_list +
                    '}';
        }

        public static class SpecListBean {
            /**
             * id : bc27bb48e6db11e79bd5180373af859e
             * category_id : 7
             * group_id : 1
             * filter : A
             * name : 尺寸
             * sort : 1
             * items : [{"id":"22","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"13英寸"},{"id":"23","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"14英寸"},{"id":"24","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"15英寸"},{"id":"25","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"16英寸"},{"id":"26","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"17英寸"},{"id":"27","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"18英寸"},{"id":"28","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"19英寸"},{"id":"29","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"21英寸"},{"id":"30","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"22英寸"},{"id":"31","spec_id":"bc27bb48e6db11e79bd5180373af859e","item":"其他"}]
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
                 * id : 22
                 * spec_id : bc27bb48e6db11e79bd5180373af859e
                 * item : 13英寸
                 */
                private String id;
                private String spec_id;
                private String item;
                private boolean isSelected;

                @Override
                public String toString() {
                    return "ItemsBean{" +
                            "id='" + id + '\'' +
                            ", spec_id='" + spec_id + '\'' +
                            ", item='" + item + '\'' +
                            ", isSelected=" + isSelected +
                            '}';
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
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
    }
}
