package com.example.gtercn.car.utils;

/**
 * author : LiHang.
 * data : 2017/1/25.
 * description:
 */
public class Constants {

    public static final int WRITE_SD = 0x01;

    public static final int CALL_PHONE = 0x02;

    public static final int LOCATION = 0x03;

    public static final int OPEN_GPS = 0x04;

    public static final String APP_LAUNCH_FLAG = "isFirstLauncher";

//    --------------  以下是电商部分  ---------------

    public static String CITY_CODE = "0411";

    public static void setCityCode(String cityCode) {
        CITY_CODE = cityCode;
    }

    public static String TOKEN = "EEEBFDEE01192DACB057C31E9AD45121C6ADE182FB48834713FBCBBAF01F3D32E05328B6B03A882C6A78C15CD9690D48ACB039E0AC987703";

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018011901977798";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088921700888610";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC2/pgsPEnoC+AO\n" +
            "gT5wuTGUY8yldQQ4xFH8JQ8QwaIz5gIM91qX8VOLVOOR3EnWRE0lWv14V5hwfIxE\n" +
            "OT24OS3yc13SVkARGSJdSlAaE8jzIXVIXqEtH5CKUoOIJiIIe6ZKkb233WDSQOUm\n" +
            "dP8nfP5x7AApNFcJ0g0GwtD1Wb4aMH8k2viip/hR7tJq2zWyf8ua5Vpye3vDZQJw\n" +
            "UJBQYCeg9KzK8o/bO1e6/HlIjzHfSfHZQKUYaAmZF6yB8boFgKgWEj7S/v4tMF8q\n" +
            "9FHfOlMJOLt7f4W3J1nU7Vr9Kqs1N5MZyv8Jxk3/p+T/cR94POlyiBEx1EQSmcdV\n" +
            "WlmAmCWnAgMBAAECggEACQKyRYNWdEmZZ71vcKd9zHu5iPAhHBZ2nv7IJiASt5F8\n" +
            "ylynplGotnSgm/8H17gXQF6hmnpdtDQ9x+I1qsuN+qE5nnDGyNC/A270UMRmoaol\n" +
            "F0Ipl4o1WMpFVb/i7x3/iy5v9Ay/hrrRTbp3Ed+yu7NOMrk0C/S+1XVkSqB3ntxt\n" +
            "HErIDGUVmFTF/aYPheAjGhKJ47iPOcUmAnLnC1arHg65nu3HwbxTxQcP3fT/FMfV\n" +
            "gpK9KQivSXZLX+bYZIPCOmaQRfHA388oKeTCijZoNaJG8lxZJR4gec/CydJWmtTN\n" +
            "8rez6xmAzqRsx6kN+okx0R1ozr9/NIp9IUcE/LU0gQKBgQDldr9pWFKXnRhNlS/Z\n" +
            "sYJWOf+1X8I6P6C9wVh1cB/+r0AQ9aTJoNOU3Ilzh1NZ1q+n26+POKo6K9sJs/cF\n" +
            "EoaAWaCKFz1MA+IDVyuEGA0CdLIy9si4DDPoAPtEt62p0Jkw0HBapKTjjzmXpD3K\n" +
            "5bI5m2/38rFujNYido8GZ9rt5wKBgQDMKCBzgrog5D+bQHHAcQgrgUrfrYqdHY8i\n" +
            "XYDpRnOHr8tSo3PMdE9d2lZkeV77/PKj600Kd+jw0DIEwyxD39iytfSgn1qo6hO8\n" +
            "ShKMA96BhiDJCv9Zzt29g6TuFXuzy1HEnXjlYeyCdv80rXrBrz7l6041UTMFb2rR\n" +
            "3otc9IySQQKBgDw0YtZV+R2Udyrr6KkaXNMY1pGU9VXpGdiPoRD3AT4GZy3L9Zd9\n" +
            "XeYh0uJ/8AuTO3U0O6S9PlSS6x5pnHc1PBry6R8yJ8AJCMTNmzpGQSuSyKp8e39m\n" +
            "nDvT/hkyhBsZAwh+t+vgZO7bxkWWLyxMBe39bWxFve3+f7R9HNuWZiejAoGATXuZ\n" +
            "PV3LGHqoLVsbEQeVKzXJ3fS2XAUA8vD74Q0tAb1VdIq2g01NZfhcu4tY4lhRGqdO\n" +
            "OP/jd6n8odQVM7l8O+9U5bU1KlCUpuyR93PsecoXHVZALayWebvB195tcYJkCTQe\n" +
            "0ygEJEvXdf21TlcdjxNsPk4QCCsOJpLI38GsrEECgYARXrOpmmi9pRdRKvsMkloM\n" +
            "GgXHZsqW0oZvVDV3hGMIF+uAWTmEepBQdHiL5p/DzWgRQHL/kAVqv76o9JKZnmSU\n" +
            "1PLZfPFuK3bxbwU+IExEYrIuxcTNBIGFqFCsTTYipE7zvOHeXFQXdpjQ0cogjkYQ\n" +
            "9AMG+o4hs9CXfKwTwq+ZCQ==";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

}
