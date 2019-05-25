package com.lander.enums;
public enum InvTypeEnum
    {
        RECEIVE("收货入仓单",1),
        ARRIVE("领料出仓单",2),
        OTHERIN("其它入仓单",3),
        OTHEROUT("其它出仓单",4),
		PICKING("收货入仓单",5),
		TRANSFEROUT("调拨出仓单",6),
		TRANSFERIN("调拨入仓单",7),
		SALE("销售出仓单",8);
	
        //成员变量
        private String name;
        private int index;
        //构造方法
        private InvTypeEnum(String name,int index)
        {
            this.name=name;
            this.index=index;
        }
        //覆盖方法
        @Override
        public String toString()
        {
            return this.index+"-"+this.name;
        }
    }