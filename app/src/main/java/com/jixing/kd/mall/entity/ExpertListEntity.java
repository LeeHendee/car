package com.jixing.kd.mall.entity;

import java.util.List;

/**
 * Created by Yan on 2018/3/18.
 */

public class ExpertListEntity {

    /**
     * result : [{"id":"fd30fc905fa44a03b5afc99793c39f70","user_id":"e4cb90abc13c4630b7d44189d2418934","top_title":"7","expert_name":"达人001","expert_discription_short":"美容装饰","expert_portrait_url":"/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/portrait/1512008262295.jpg","expert_wechat_number":"18341850055","expert_experience":"8","expert_tel_number":"18341850055","motto":"美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰","expert_discription_detail":"美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰","delete_flag":"1","insert_time":1512008262000,"update_time":1520949874000,"is_inn":1,"display_list":["/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262234.jpg","/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262254.jpg","/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262274.jpg"],"expert_category_name":"车载电器"}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "ExpertListEntity{" +
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : fd30fc905fa44a03b5afc99793c39f70
         * user_id : e4cb90abc13c4630b7d44189d2418934
         * top_title : 7
         * expert_name : 达人001
         * expert_discription_short : 美容装饰
         * expert_portrait_url : /carhome/expert/fd30fc905fa44a03b5afc99793c39f70/portrait/1512008262295.jpg
         * expert_wechat_number : 18341850055
         * expert_experience : 8
         * expert_tel_number : 18341850055
         * motto : 美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰
         * expert_discription_detail : 美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰美容装饰
         * delete_flag : 1
         * insert_time : 1512008262000
         * update_time : 1520949874000
         * is_inn : 1
         * display_list : ["/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262234.jpg","/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262254.jpg","/carhome/expert/fd30fc905fa44a03b5afc99793c39f70/display/1512008262274.jpg"]
         * expert_category_name : 车载电器
         */

        private String id;
        private String user_id;
        private String top_title;
        private String expert_name;
        private String expert_discription_short;
        private String expert_portrait_url;
        private String expert_wechat_number;
        private String expert_experience;
        private String expert_tel_number;
        private String motto;
        private String expert_discription_detail;
        private String delete_flag;
        private long insert_time;
        private long update_time;
        private int is_inn;
        private String expert_category_name;
        private List<String> display_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", top_title='" + top_title + '\'' +
                    ", expert_name='" + expert_name + '\'' +
                    ", expert_discription_short='" + expert_discription_short + '\'' +
                    ", expert_portrait_url='" + expert_portrait_url + '\'' +
                    ", expert_wechat_number='" + expert_wechat_number + '\'' +
                    ", expert_experience='" + expert_experience + '\'' +
                    ", expert_tel_number='" + expert_tel_number + '\'' +
                    ", motto='" + motto + '\'' +
                    ", expert_discription_detail='" + expert_discription_detail + '\'' +
                    ", delete_flag='" + delete_flag + '\'' +
                    ", insert_time=" + insert_time +
                    ", update_time=" + update_time +
                    ", is_inn=" + is_inn +
                    ", expert_category_name='" + expert_category_name + '\'' +
                    ", display_list=" + display_list +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTop_title() {
            return top_title;
        }

        public void setTop_title(String top_title) {
            this.top_title = top_title;
        }

        public String getExpert_name() {
            return expert_name;
        }

        public void setExpert_name(String expert_name) {
            this.expert_name = expert_name;
        }

        public String getExpert_discription_short() {
            return expert_discription_short;
        }

        public void setExpert_discription_short(String expert_discription_short) {
            this.expert_discription_short = expert_discription_short;
        }

        public String getExpert_portrait_url() {
            return expert_portrait_url;
        }

        public void setExpert_portrait_url(String expert_portrait_url) {
            this.expert_portrait_url = expert_portrait_url;
        }

        public String getExpert_wechat_number() {
            return expert_wechat_number;
        }

        public void setExpert_wechat_number(String expert_wechat_number) {
            this.expert_wechat_number = expert_wechat_number;
        }

        public String getExpert_experience() {
            return expert_experience;
        }

        public void setExpert_experience(String expert_experience) {
            this.expert_experience = expert_experience;
        }

        public String getExpert_tel_number() {
            return expert_tel_number;
        }

        public void setExpert_tel_number(String expert_tel_number) {
            this.expert_tel_number = expert_tel_number;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

        public String getExpert_discription_detail() {
            return expert_discription_detail;
        }

        public void setExpert_discription_detail(String expert_discription_detail) {
            this.expert_discription_detail = expert_discription_detail;
        }

        public String getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(String delete_flag) {
            this.delete_flag = delete_flag;
        }

        public long getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(long insert_time) {
            this.insert_time = insert_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public int getIs_inn() {
            return is_inn;
        }

        public void setIs_inn(int is_inn) {
            this.is_inn = is_inn;
        }

        public String getExpert_category_name() {
            return expert_category_name;
        }

        public void setExpert_category_name(String expert_category_name) {
            this.expert_category_name = expert_category_name;
        }

        public List<String> getDisplay_list() {
            return display_list;
        }

        public void setDisplay_list(List<String> display_list) {
            this.display_list = display_list;
        }
    }
}
