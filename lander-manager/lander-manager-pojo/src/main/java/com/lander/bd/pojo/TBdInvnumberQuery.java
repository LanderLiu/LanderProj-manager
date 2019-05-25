package com.lander.bd.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TBdInvnumberQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TBdInvnumberQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andFidIsNull() {
            addCriterion("fid is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("fid is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(Long value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Long value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Long value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Long value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Long value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Long value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Long> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Long> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Long value1, Long value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Long value1, Long value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFcreatemanIsNull() {
            addCriterion("fcreateman is null");
            return (Criteria) this;
        }

        public Criteria andFcreatemanIsNotNull() {
            addCriterion("fcreateman is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatemanEqualTo(String value) {
            addCriterion("fcreateman =", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotEqualTo(String value) {
            addCriterion("fcreateman <>", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanGreaterThan(String value) {
            addCriterion("fcreateman >", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanGreaterThanOrEqualTo(String value) {
            addCriterion("fcreateman >=", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLessThan(String value) {
            addCriterion("fcreateman <", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLessThanOrEqualTo(String value) {
            addCriterion("fcreateman <=", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLike(String value) {
            addCriterion("fcreateman like", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotLike(String value) {
            addCriterion("fcreateman not like", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanIn(List<String> values) {
            addCriterion("fcreateman in", values, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotIn(List<String> values) {
            addCriterion("fcreateman not in", values, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanBetween(String value1, String value2) {
            addCriterion("fcreateman between", value1, value2, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotBetween(String value1, String value2) {
            addCriterion("fcreateman not between", value1, value2, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeIsNull() {
            addCriterion("fcreatedatetime is null");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeIsNotNull() {
            addCriterion("fcreatedatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("fcreatedatetime =", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("fcreatedatetime <>", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("fcreatedatetime >", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fcreatedatetime >=", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeLessThan(Date value) {
            addCriterionForJDBCDate("fcreatedatetime <", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fcreatedatetime <=", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("fcreatedatetime in", values, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("fcreatedatetime not in", values, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fcreatedatetime between", value1, value2, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fcreatedatetime not between", value1, value2, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanIsNull() {
            addCriterion("flastmodifyman is null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanIsNotNull() {
            addCriterion("flastmodifyman is not null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanEqualTo(String value) {
            addCriterion("flastmodifyman =", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanNotEqualTo(String value) {
            addCriterion("flastmodifyman <>", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanGreaterThan(String value) {
            addCriterion("flastmodifyman >", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanGreaterThanOrEqualTo(String value) {
            addCriterion("flastmodifyman >=", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanLessThan(String value) {
            addCriterion("flastmodifyman <", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanLessThanOrEqualTo(String value) {
            addCriterion("flastmodifyman <=", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanLike(String value) {
            addCriterion("flastmodifyman like", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanNotLike(String value) {
            addCriterion("flastmodifyman not like", value, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanIn(List<String> values) {
            addCriterion("flastmodifyman in", values, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanNotIn(List<String> values) {
            addCriterion("flastmodifyman not in", values, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanBetween(String value1, String value2) {
            addCriterion("flastmodifyman between", value1, value2, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifymanNotBetween(String value1, String value2) {
            addCriterion("flastmodifyman not between", value1, value2, "flastmodifyman");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeIsNull() {
            addCriterion("flastmodifydatetime is null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeIsNotNull() {
            addCriterion("flastmodifydatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime =", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime <>", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime >", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime >=", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeLessThan(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime <", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("flastmodifydatetime <=", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("flastmodifydatetime in", values, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("flastmodifydatetime not in", values, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("flastmodifydatetime between", value1, value2, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("flastmodifydatetime not between", value1, value2, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFcategoryidIsNull() {
            addCriterion("fcategoryid is null");
            return (Criteria) this;
        }

        public Criteria andFcategoryidIsNotNull() {
            addCriterion("fcategoryid is not null");
            return (Criteria) this;
        }

        public Criteria andFcategoryidEqualTo(Integer value) {
            addCriterion("fcategoryid =", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidNotEqualTo(Integer value) {
            addCriterion("fcategoryid <>", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidGreaterThan(Integer value) {
            addCriterion("fcategoryid >", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fcategoryid >=", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidLessThan(Integer value) {
            addCriterion("fcategoryid <", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidLessThanOrEqualTo(Integer value) {
            addCriterion("fcategoryid <=", value, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidIn(List<Integer> values) {
            addCriterion("fcategoryid in", values, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidNotIn(List<Integer> values) {
            addCriterion("fcategoryid not in", values, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidBetween(Integer value1, Integer value2) {
            addCriterion("fcategoryid between", value1, value2, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFcategoryidNotBetween(Integer value1, Integer value2) {
            addCriterion("fcategoryid not between", value1, value2, "fcategoryid");
            return (Criteria) this;
        }

        public Criteria andFleaderstrIsNull() {
            addCriterion("fleaderstr is null");
            return (Criteria) this;
        }

        public Criteria andFleaderstrIsNotNull() {
            addCriterion("fleaderstr is not null");
            return (Criteria) this;
        }

        public Criteria andFleaderstrEqualTo(String value) {
            addCriterion("fleaderstr =", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrNotEqualTo(String value) {
            addCriterion("fleaderstr <>", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrGreaterThan(String value) {
            addCriterion("fleaderstr >", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrGreaterThanOrEqualTo(String value) {
            addCriterion("fleaderstr >=", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrLessThan(String value) {
            addCriterion("fleaderstr <", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrLessThanOrEqualTo(String value) {
            addCriterion("fleaderstr <=", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrLike(String value) {
            addCriterion("fleaderstr like", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrNotLike(String value) {
            addCriterion("fleaderstr not like", value, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrIn(List<String> values) {
            addCriterion("fleaderstr in", values, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrNotIn(List<String> values) {
            addCriterion("fleaderstr not in", values, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrBetween(String value1, String value2) {
            addCriterion("fleaderstr between", value1, value2, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFleaderstrNotBetween(String value1, String value2) {
            addCriterion("fleaderstr not between", value1, value2, "fleaderstr");
            return (Criteria) this;
        }

        public Criteria andFflowIsNull() {
            addCriterion("fflow is null");
            return (Criteria) this;
        }

        public Criteria andFflowIsNotNull() {
            addCriterion("fflow is not null");
            return (Criteria) this;
        }

        public Criteria andFflowEqualTo(Integer value) {
            addCriterion("fflow =", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowNotEqualTo(Integer value) {
            addCriterion("fflow <>", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowGreaterThan(Integer value) {
            addCriterion("fflow >", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowGreaterThanOrEqualTo(Integer value) {
            addCriterion("fflow >=", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowLessThan(Integer value) {
            addCriterion("fflow <", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowLessThanOrEqualTo(Integer value) {
            addCriterion("fflow <=", value, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowIn(List<Integer> values) {
            addCriterion("fflow in", values, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowNotIn(List<Integer> values) {
            addCriterion("fflow not in", values, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowBetween(Integer value1, Integer value2) {
            addCriterion("fflow between", value1, value2, "fflow");
            return (Criteria) this;
        }

        public Criteria andFflowNotBetween(Integer value1, Integer value2) {
            addCriterion("fflow not between", value1, value2, "fflow");
            return (Criteria) this;
        }

        public Criteria andFdescriptIsNull() {
            addCriterion("fdescript is null");
            return (Criteria) this;
        }

        public Criteria andFdescriptIsNotNull() {
            addCriterion("fdescript is not null");
            return (Criteria) this;
        }

        public Criteria andFdescriptEqualTo(String value) {
            addCriterion("fdescript =", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptNotEqualTo(String value) {
            addCriterion("fdescript <>", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptGreaterThan(String value) {
            addCriterion("fdescript >", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptGreaterThanOrEqualTo(String value) {
            addCriterion("fdescript >=", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptLessThan(String value) {
            addCriterion("fdescript <", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptLessThanOrEqualTo(String value) {
            addCriterion("fdescript <=", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptLike(String value) {
            addCriterion("fdescript like", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptNotLike(String value) {
            addCriterion("fdescript not like", value, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptIn(List<String> values) {
            addCriterion("fdescript in", values, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptNotIn(List<String> values) {
            addCriterion("fdescript not in", values, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptBetween(String value1, String value2) {
            addCriterion("fdescript between", value1, value2, "fdescript");
            return (Criteria) this;
        }

        public Criteria andFdescriptNotBetween(String value1, String value2) {
            addCriterion("fdescript not between", value1, value2, "fdescript");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}