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
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCc59utM4eme+D+\n" +
            "dr0jrmW8Mo4jgh3cp6rarcsBbTcyEjZM6/DC/zcPmIzaPz3N27PEs7Nc5peZGWSN\n" +
            "dalRfiHUiyo5CatmzzberIB4RJm0uJl+SQ6litUfRUhamguC2VTamweiI9MI2LjH\n" +
            "sO2XVuclXmopi6o6H7rQxv5xOEklM8XQl1et1+oDODWgipKsbHSPwxcQA+olRU5c\n" +
            "P/MxG/BNSMkPapYQ0Rdca52Aaw/mXv4Ka/QMK2jWQr+QDfGbn499iyOnISHZebYL\n" +
            "Cfouwc33T/37LtLYlO2A9RuuCVCBvUGOfeJIPdHuu14xAt7oAJVc4/ELi9Pp1THy\n" +
            "quW9/GNvAgMBAAECggEAKb35EDPkQ6MzMR8vpD1yLUYZ0FJ+VUR5zKFtn1yJ0ZVM\n" +
            "wtwF+iphYJHJ7wniDFEFmy/faLij3gQGXyfNOrPelifLvM7YvYok48pqhxRg+9Yc\n" +
            "Z59R+TJdDmulGw1eQJpG271go376VqEpT16/ZoKlDaj3f0P7rmjgj0hRSpeZxf58\n" +
            "TFh5e5uzFkhJ5kVOHfaATATE0+FNSBLWXajzi3c+D9TJqtq91/riirP2WyYfeqXN\n" +
            "M2N6Bs1ObLskjs9VCCx0HUm6wtTAaEhLHzdOby++16pOefMaXHbkTSA87yhOMt9Y\n" +
            "qovSAaqoUbR3OlVoqPoKOLL8EECFAznrGGxyI2TjiQKBgQDQEXyHOQNqch4L3pXp\n" +
            "6Azhenk3m8NdRKXlO0xAU3uS77y9I5UCFgKKdSL0vmkABoL3QB/N9QM8AzK633XO\n" +
            "h5ExG4TaKMe7nonYJZed9vPfIWEABvxupTGogpyYWnCSksAw2XH3+/Zs9fHTpAQB\n" +
            "2s6MHpAq2+elDFm2t3jJ+XThvQKBgQDBDSJVpUgaV3rsENDP5MUW+VD3kaVVMY3j\n" +
            "I1cz/18PqUym+uhn/kLoCGqMBXhLvaiMvc6O3XKKragHPtEFjtCBKAOOKIEVk03n\n" +
            "+eEa+UQ0eLzY3FBIGoaKsami47FyyGSw0/3L8NojOvAV2jRl777b4GG2QF8cHlp0\n" +
            "T3rXIGLumwKBgQCJ8V3m5HSQSLIE2n/3QEHx5tmgdWMTXaTCkgqehvphUxGFL6bl\n" +
            "aCQtGTWgnvBL9QU02M19JRgM3G3wmJ+o2hX3QbBVVj389EGdDGc1cfkCkEWX2AnZ\n" +
            "bSm790PMegEyG5rRiAfYCbK5UGEQGCgT9rWDDew0jfBIayS7T24EMZ883QKBgHWV\n" +
            "N11s/z60G7WfBGArmfPFRRCVUFtUhHRdOSb9mvviBUMLPfThRAUp4yOHzV5slTWs\n" +
            "Ahy4OqTG4roPA6bb9plEMu4qM31drCzn4LjaLDW9eGCpx+gy59GgJo+FGbas4c9m\n" +
            "P4YxG9wrcMIOlq2j2d74iutDx+pLfpKrOkm/eyKTAoGAVsXS6xc/2dnrUWwv8uq7\n" +
            "dqs7xyUJSRTiBwIn75uWgwbqgGFtEEg62UIZ8r3gwYoLov6UjwIJ5uQvuMJIQkrE\n" +
            "U4hKh8nQuPP6YLGcfGeYRws/qsKj7lReC5xd3yab6WDhVdogpstzvJu07g9OwMpU\n" +
            "lBIdI8UJBVpoHmmruD7F8wQ=";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

}
