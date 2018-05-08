package com.jixing.kd.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/2/1.
 * Used to : 搜索页的entity
 */

public class SearchPageEntity {
    /**
     * err_code : 0
     * err_message : OK
     * result : {"hot_search":["轮胎","防冻液"],"search_list":[]}
     */
    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "SearchPageEntity{" +
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
        private List<String> hot_search;
        private List<String> search_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "hot_search=" + hot_search +
                    ", search_list=" + search_list +
                    '}';
        }

        public List<String> getHot_search() {
            return hot_search;
        }

        public void setHot_search(List<String> hot_search) {
            this.hot_search = hot_search;
        }

        public List<String> getSearch_list() {
            return search_list;
        }

        public void setSearch_list(List<String> search_list) {
            this.search_list = search_list;
        }
    }
}
