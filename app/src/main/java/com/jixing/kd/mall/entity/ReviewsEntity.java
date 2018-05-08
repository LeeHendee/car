package com.jixing.kd.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/24.
 * Used to :
 */

public class ReviewsEntity {

    /**
     * err_code : 0
     * err_message : OK
     * result : {"goods_id":"1","total_count":2,"good_count":2,"good_percent":"100%","middle_count":0,"bad_count":0,"picture_count":2,"append_count":1,"reply_count":1,"comment_list":[{"id":"1","goods_id":"1","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","tag_ids":"1,2","anonymous":"Y","status":"G","describe_status":5,"service_attitude":3,"service_logistics":3,"content":"好评，非常好","is_picture":"Y","is_append":"Y","picture":"a,c,b","create_time":1515306198000,"commend":1,"nickname":"测试(昵称)","picture_list":["a","c","b"]},{"id":"c0c5d15b05e5440e9db545866fe2acc1","goods_id":"1","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","anonymous":"Y","status":"G","describe_status":5,"service_attitude":5,"service_logistics":5,"content":"商品好评测试","is_picture":"Y","is_append":"N","picture":"\\carInn\\comment\\1\\1516756298678.PNG,\\carInn\\comment\\1\\1516756378861.PNG","create_time":1516756476000,"commend":0,"nickname":"测试(昵称)","picture_list":["\\carInn\\comment\\1\\1516756298678.PNG","\\carInn\\comment\\1\\1516756378861.PNG"]}]}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "ReviewsEntity{" +
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
        /**
         * goods_id : 1
         * total_count : 2
         * good_count : 2
         * good_percent : 100%
         * middle_count : 0
         * bad_count : 0
         * picture_count : 2
         * append_count : 1
         * reply_count : 1
         * comment_list : [{"id":"1","goods_id":"1","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","tag_ids":"1,2","anonymous":"Y","status":"G","describe_status":5,"service_attitude":3,"service_logistics":3,"content":"好评，非常好","is_picture":"Y","is_append":"Y","picture":"a,c,b","create_time":1515306198000,"commend":1,"nickname":"测试(昵称)","picture_list":["a","c","b"]},{"id":"c0c5d15b05e5440e9db545866fe2acc1","goods_id":"1","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","anonymous":"Y","status":"G","describe_status":5,"service_attitude":5,"service_logistics":5,"content":"商品好评测试","is_picture":"Y","is_append":"N","picture":"\\carInn\\comment\\1\\1516756298678.PNG,\\carInn\\comment\\1\\1516756378861.PNG","create_time":1516756476000,"commend":0,"nickname":"测试(昵称)","picture_list":["\\carInn\\comment\\1\\1516756298678.PNG","\\carInn\\comment\\1\\1516756378861.PNG"]}]
         */

        private String goods_id;
        private int total_count;
        private int good_count;
        private String good_percent;
        private int middle_count;
        private int bad_count;
        private int picture_count;
        private int append_count;
        private int reply_count;
        private List<CommentListBean> comment_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", total_count=" + total_count +
                    ", good_count=" + good_count +
                    ", good_percent='" + good_percent + '\'' +
                    ", middle_count=" + middle_count +
                    ", bad_count=" + bad_count +
                    ", picture_count=" + picture_count +
                    ", append_count=" + append_count +
                    ", reply_count=" + reply_count +
                    ", comment_list=" + comment_list +
                    '}';
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getGood_count() {
            return good_count;
        }

        public void setGood_count(int good_count) {
            this.good_count = good_count;
        }

        public String getGood_percent() {
            return good_percent;
        }

        public void setGood_percent(String good_percent) {
            this.good_percent = good_percent;
        }

        public int getMiddle_count() {
            return middle_count;
        }

        public void setMiddle_count(int middle_count) {
            this.middle_count = middle_count;
        }

        public int getBad_count() {
            return bad_count;
        }

        public void setBad_count(int bad_count) {
            this.bad_count = bad_count;
        }

        public int getPicture_count() {
            return picture_count;
        }

        public void setPicture_count(int picture_count) {
            this.picture_count = picture_count;
        }

        public int getAppend_count() {
            return append_count;
        }

        public void setAppend_count(int append_count) {
            this.append_count = append_count;
        }

        public int getReply_count() {
            return reply_count;
        }

        public void setReply_count(int reply_count) {
            this.reply_count = reply_count;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class CommentListBean {
            /**
             * id : 1
             * goods_id : 1
             * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
             * tag_ids : 1,2
             * anonymous : Y
             * status : G
             * describe_status : 5
             * service_attitude : 3
             * service_logistics : 3
             * content : 好评，非常好
             * is_picture : Y
             * is_append : Y
             * picture : a,c,b
             * create_time : 1515306198000
             * commend : 1
             * nickname : 测试(昵称)
             * picture_list : ["a","c","b"]
             */

            private String id;
            private String goods_id;
            private String user_id;
            private String tag_ids;
            private String anonymous;
            private String status;
            private int describe_status;
            private int service_attitude;
            private int service_logistics;
            private String content;
            private String is_picture;
            private String is_append;
            private String picture;
            private long create_time;
            private int commend;
            private String nickname;
            private List<String> picture_list;

            @Override
            public String toString() {
                return "CommentListBean{" +
                        "id='" + id + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        ", user_id='" + user_id + '\'' +
                        ", tag_ids='" + tag_ids + '\'' +
                        ", anonymous='" + anonymous + '\'' +
                        ", status='" + status + '\'' +
                        ", describe_status=" + describe_status +
                        ", service_attitude=" + service_attitude +
                        ", service_logistics=" + service_logistics +
                        ", content='" + content + '\'' +
                        ", is_picture='" + is_picture + '\'' +
                        ", is_append='" + is_append + '\'' +
                        ", picture='" + picture + '\'' +
                        ", create_time=" + create_time +
                        ", commend=" + commend +
                        ", nickname='" + nickname + '\'' +
                        ", picture_list=" + picture_list +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTag_ids() {
                return tag_ids;
            }

            public void setTag_ids(String tag_ids) {
                this.tag_ids = tag_ids;
            }

            public String getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(String anonymous) {
                this.anonymous = anonymous;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getDescribe_status() {
                return describe_status;
            }

            public void setDescribe_status(int describe_status) {
                this.describe_status = describe_status;
            }

            public int getService_attitude() {
                return service_attitude;
            }

            public void setService_attitude(int service_attitude) {
                this.service_attitude = service_attitude;
            }

            public int getService_logistics() {
                return service_logistics;
            }

            public void setService_logistics(int service_logistics) {
                this.service_logistics = service_logistics;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_picture() {
                return is_picture;
            }

            public void setIs_picture(String is_picture) {
                this.is_picture = is_picture;
            }

            public String getIs_append() {
                return is_append;
            }

            public void setIs_append(String is_append) {
                this.is_append = is_append;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getCommend() {
                return commend;
            }

            public void setCommend(int commend) {
                this.commend = commend;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public List<String> getPicture_list() {
                return picture_list;
            }

            public void setPicture_list(List<String> picture_list) {
                this.picture_list = picture_list;
            }
        }
    }
}
