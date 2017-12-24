package com.example.gtercn.car.mall.entity;

import com.example.gtercn.car.R;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/20.
 * Used to :
 */

public class ClassifyEntity {

    /**
     * result : [{"id":"1","title":"汽车坐垫","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"2","title":"行车记录仪","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"3","title":"机油","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"4","title":"汽车水枪","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"5","title":"应急救援","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"6","title":"车载电器","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"7","title":"轮胎","parent_code":"0","delete_flag":0,"descriptiion":null},{"id":"8","title":"美容清洗","parent_code":"0","delete_flag":0,"descriptiion":null}]
     * err_code : 0
     * err_message : OK
     * message : null
     */

    private String err_code;
    private String err_message;
    private Object message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "ClassifyEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", message=" + message +
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * title : 汽车坐垫
         * parent_code : 0
         * delete_flag : 0
         * descriptiion : null
         */

        private String id;
        private String title;
        private String parent_code;
        private int delete_flag;
        private String descriptiion;
        private int res = R.drawable.ic_launcher;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", parent_code='" + parent_code + '\'' +
                    ", delete_flag=" + delete_flag +
                    ", descriptiion=" + descriptiion +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getParent_code() {
            return parent_code;
        }

        public void setParent_code(String parent_code) {
            this.parent_code = parent_code;
        }

        public int getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(int delete_flag) {
            this.delete_flag = delete_flag;
        }

        public String getDescriptiion() {
            return descriptiion;
        }

        public void setDescriptiion(String descriptiion) {
            this.descriptiion = descriptiion;
        }
    }
}
