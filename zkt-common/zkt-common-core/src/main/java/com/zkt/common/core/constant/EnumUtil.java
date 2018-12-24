/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title: MESSAGE.java
 * @Package ierp.common
 * @Description: 用于各个方法返回的参数或者一些静态常量。
 * @author 钰鹏
 * @date 2013年10月9日 下午1:43:00
 * @version V1.0
 */
package com.zkt.common.core.constant;

/**
 * @author 钰鹏
 * @ClassName: MESSAGE
 * @Description: 用于各个方法返回的参数或者一些静态常量。
 * @date 2013年10月9日 下午1:43:00
 */

public class EnumUtil {

    /**
     * 消息状态
     */
    public enum MessageStatus {
        OK {
            @Override
            public int getName() {
                return 1;
            }
        },
        ERROR {
            @Override
            public int getName() {
                return 0;
            }
        },
        WARN{
            @Override
            public int getName() {
                return 2;
            }
        },
        NOFOUND{
            @Override
            public int getName() {
                return 3;
            }
        },
        ERRORSTATION{
            @Override
            public int getName() {
                return 4;
            }
        },
        TIMEOUT{
            @Override
            public int getName() {
                return 408;
            }
        };

        public abstract int getName();
    }


    /**
     * 资源类型
     */
    public enum MenuType{
        MENU {
            @Override
            public String getType() {
                return "menu";
            }
        },
        BUTTON {
            @Override
            public String getType() {
                return "button";
            }
        },
        URI {
            @Override
            public String getType() {
                return "uri";
            }
        };
        public abstract String getType();
    }

    /**
     * 组权限类型
     */
    public enum GroupType{
        ROLE {
            @Override
            public String getType() {
                return "1";
            }
        },
        DEPART {
            @Override
            public String getType() {
                return "2";
            }
        };
        public abstract String getType();
    }
    /**
     * 数据状态
     */
    public enum DataStatus{
        ENABLE {
            @Override
            public String getStatus() {
                return "1";
            }
        },
        DISABLE {
            @Override
            public String getStatus() {
                return "2";
            }
        },
        DELETE {
            @Override
            public String getStatus() {
                return "3";
            }
        };
        public abstract String getStatus();
    }
}
