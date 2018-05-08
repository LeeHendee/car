package com.jixing.kd.mall.entity;

/**
 * Created by Yan on 2018/1/8.
 */

public class ResultEntity {
//    /**
//     * err_code : 0
//     * err_message : OK
//     * result : {}
//     * message : 修改成功
//     */
//
//    private String err_code;
//    private String err_message;
//    private ResultBean result;
//    private String message;
//
//
//    @Override
//    public String toString() {
//        return "ResultEntity{" +
//                "err_code='" + err_code + '\'' +
//                ", err_message='" + err_message + '\'' +
//                ", result=" + result +
//                ", message='" + message + '\'' +
//                '}';
//    }
//
//    public static ResultEntity objectFromData(String str) {
//
//        return new Gson().fromJson(str, ResultEntity.class);
//    }
//
//    public static ResultEntity objectFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//
//            return new Gson().fromJson(jsonObject.getString(str), ResultEntity.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static List<ResultEntity> arrayResultEntityFromData(String str) {
//
//        Type listType = new TypeToken<ArrayList<ResultEntity>>() {
//        }.getType();
//
//        return new Gson().fromJson(str, listType);
//    }
//
//    public static List<ResultEntity> arrayResultEntityFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//            Type listType = new TypeToken<ArrayList<ResultEntity>>() {
//            }.getType();
//
//            return new Gson().fromJson(jsonObject.getString(str), listType);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return new ArrayList();
//
//
//    }
//
//    public String getErr_code() {
//        return err_code;
//    }
//
//    public void setErr_code(String err_code) {
//        this.err_code = err_code;
//    }
//
//    public String getErr_message() {
//        return err_message;
//    }
//
//    public void setErr_message(String err_message) {
//        this.err_message = err_message;
//    }
//
//    public ResultBean getResult() {
//        return result;
//    }
//
//    public void setResult(ResultBean result) {
//        this.result = result;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public static class ResultBean {
//        public static ResultBean objectFromData(String str) {
//
//            return new Gson().fromJson(str, ResultBean.class);
//        }
//
//        public static ResultBean objectFromData(String str, String key) {
//
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//
//                return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        public static List<ResultBean> arrayResultBeanFromData(String str) {
//
//            Type listType = new TypeToken<ArrayList<ResultBean>>() {
//            }.getType();
//
//            return new Gson().fromJson(str, listType);
//        }
//
//        public static List<ResultBean> arrayResultBeanFromData(String str, String key) {
//
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//                Type listType = new TypeToken<ArrayList<ResultBean>>() {
//                }.getType();
//
//                return new Gson().fromJson(jsonObject.getString(str), listType);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return new ArrayList();
//
//
//        }
//    }


    /**
     * err_code : 0
     * err_message : OK
     * message : 更改成功
     */

    private String err_code;
    private String err_message;
    private String message;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
