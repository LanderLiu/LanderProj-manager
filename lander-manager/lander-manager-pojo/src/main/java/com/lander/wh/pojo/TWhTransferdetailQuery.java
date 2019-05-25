package com.lander.wh.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWhTransferdetailQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TWhTransferdetailQuery() {
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

        public Criteria andFcreatedatetimeIsNull() {
            addCriterion("fcreatedatetime is null");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeIsNotNull() {
            addCriterion("fcreatedatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeEqualTo(Date value) {
            addCriterion("fcreatedatetime =", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotEqualTo(Date value) {
            addCriterion("fcreatedatetime <>", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeGreaterThan(Date value) {
            addCriterion("fcreatedatetime >", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fcreatedatetime >=", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeLessThan(Date value) {
            addCriterion("fcreatedatetime <", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeLessThanOrEqualTo(Date value) {
            addCriterion("fcreatedatetime <=", value, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeIn(List<Date> values) {
            addCriterion("fcreatedatetime in", values, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotIn(List<Date> values) {
            addCriterion("fcreatedatetime not in", values, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeBetween(Date value1, Date value2) {
            addCriterion("fcreatedatetime between", value1, value2, "fcreatedatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatedatetimeNotBetween(Date value1, Date value2) {
            addCriterion("fcreatedatetime not between", value1, value2, "fcreatedatetime");
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

        public Criteria andFlastmodifydatetimeIsNull() {
            addCriterion("flastmodifydatetime is null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeIsNotNull() {
            addCriterion("flastmodifydatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeEqualTo(Date value) {
            addCriterion("flastmodifydatetime =", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotEqualTo(Date value) {
            addCriterion("flastmodifydatetime <>", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeGreaterThan(Date value) {
            addCriterion("flastmodifydatetime >", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("flastmodifydatetime >=", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeLessThan(Date value) {
            addCriterion("flastmodifydatetime <", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeLessThanOrEqualTo(Date value) {
            addCriterion("flastmodifydatetime <=", value, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeIn(List<Date> values) {
            addCriterion("flastmodifydatetime in", values, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotIn(List<Date> values) {
            addCriterion("flastmodifydatetime not in", values, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeBetween(Date value1, Date value2) {
            addCriterion("flastmodifydatetime between", value1, value2, "flastmodifydatetime");
            return (Criteria) this;
        }

        public Criteria andFlastmodifydatetimeNotBetween(Date value1, Date value2) {
            addCriterion("flastmodifydatetime not between", value1, value2, "flastmodifydatetime");
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

        public Criteria andFmasteridIsNull() {
            addCriterion("fmasterid is null");
            return (Criteria) this;
        }

        public Criteria andFmasteridIsNotNull() {
            addCriterion("fmasterid is not null");
            return (Criteria) this;
        }

        public Criteria andFmasteridEqualTo(Long value) {
            addCriterion("fmasterid =", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridNotEqualTo(Long value) {
            addCriterion("fmasterid <>", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridGreaterThan(Long value) {
            addCriterion("fmasterid >", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridGreaterThanOrEqualTo(Long value) {
            addCriterion("fmasterid >=", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridLessThan(Long value) {
            addCriterion("fmasterid <", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridLessThanOrEqualTo(Long value) {
            addCriterion("fmasterid <=", value, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridIn(List<Long> values) {
            addCriterion("fmasterid in", values, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridNotIn(List<Long> values) {
            addCriterion("fmasterid not in", values, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridBetween(Long value1, Long value2) {
            addCriterion("fmasterid between", value1, value2, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmasteridNotBetween(Long value1, Long value2) {
            addCriterion("fmasterid not between", value1, value2, "fmasterid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIsNull() {
            addCriterion("fmaterialid is null");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIsNotNull() {
            addCriterion("fmaterialid is not null");
            return (Criteria) this;
        }

        public Criteria andFmaterialidEqualTo(Long value) {
            addCriterion("fmaterialid =", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotEqualTo(Long value) {
            addCriterion("fmaterialid <>", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidGreaterThan(Long value) {
            addCriterion("fmaterialid >", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidGreaterThanOrEqualTo(Long value) {
            addCriterion("fmaterialid >=", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidLessThan(Long value) {
            addCriterion("fmaterialid <", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidLessThanOrEqualTo(Long value) {
            addCriterion("fmaterialid <=", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIn(List<Long> values) {
            addCriterion("fmaterialid in", values, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotIn(List<Long> values) {
            addCriterion("fmaterialid not in", values, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidBetween(Long value1, Long value2) {
            addCriterion("fmaterialid between", value1, value2, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotBetween(Long value1, Long value2) {
            addCriterion("fmaterialid not between", value1, value2, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameIsNull() {
            addCriterion("fmaterialname is null");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameIsNotNull() {
            addCriterion("fmaterialname is not null");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameEqualTo(String value) {
            addCriterion("fmaterialname =", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameNotEqualTo(String value) {
            addCriterion("fmaterialname <>", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameGreaterThan(String value) {
            addCriterion("fmaterialname >", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameGreaterThanOrEqualTo(String value) {
            addCriterion("fmaterialname >=", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameLessThan(String value) {
            addCriterion("fmaterialname <", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameLessThanOrEqualTo(String value) {
            addCriterion("fmaterialname <=", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameLike(String value) {
            addCriterion("fmaterialname like", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameNotLike(String value) {
            addCriterion("fmaterialname not like", value, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameIn(List<String> values) {
            addCriterion("fmaterialname in", values, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameNotIn(List<String> values) {
            addCriterion("fmaterialname not in", values, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameBetween(String value1, String value2) {
            addCriterion("fmaterialname between", value1, value2, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnameNotBetween(String value1, String value2) {
            addCriterion("fmaterialname not between", value1, value2, "fmaterialname");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberIsNull() {
            addCriterion("fmaterialnumber is null");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberIsNotNull() {
            addCriterion("fmaterialnumber is not null");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberEqualTo(String value) {
            addCriterion("fmaterialnumber =", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberNotEqualTo(String value) {
            addCriterion("fmaterialnumber <>", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberGreaterThan(String value) {
            addCriterion("fmaterialnumber >", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberGreaterThanOrEqualTo(String value) {
            addCriterion("fmaterialnumber >=", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberLessThan(String value) {
            addCriterion("fmaterialnumber <", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberLessThanOrEqualTo(String value) {
            addCriterion("fmaterialnumber <=", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberLike(String value) {
            addCriterion("fmaterialnumber like", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberNotLike(String value) {
            addCriterion("fmaterialnumber not like", value, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberIn(List<String> values) {
            addCriterion("fmaterialnumber in", values, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberNotIn(List<String> values) {
            addCriterion("fmaterialnumber not in", values, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberBetween(String value1, String value2) {
            addCriterion("fmaterialnumber between", value1, value2, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialnumberNotBetween(String value1, String value2) {
            addCriterion("fmaterialnumber not between", value1, value2, "fmaterialnumber");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationIsNull() {
            addCriterion("fmaterialspecification is null");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationIsNotNull() {
            addCriterion("fmaterialspecification is not null");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationEqualTo(String value) {
            addCriterion("fmaterialspecification =", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationNotEqualTo(String value) {
            addCriterion("fmaterialspecification <>", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationGreaterThan(String value) {
            addCriterion("fmaterialspecification >", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationGreaterThanOrEqualTo(String value) {
            addCriterion("fmaterialspecification >=", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationLessThan(String value) {
            addCriterion("fmaterialspecification <", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationLessThanOrEqualTo(String value) {
            addCriterion("fmaterialspecification <=", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationLike(String value) {
            addCriterion("fmaterialspecification like", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationNotLike(String value) {
            addCriterion("fmaterialspecification not like", value, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationIn(List<String> values) {
            addCriterion("fmaterialspecification in", values, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationNotIn(List<String> values) {
            addCriterion("fmaterialspecification not in", values, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationBetween(String value1, String value2) {
            addCriterion("fmaterialspecification between", value1, value2, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFmaterialspecificationNotBetween(String value1, String value2) {
            addCriterion("fmaterialspecification not between", value1, value2, "fmaterialspecification");
            return (Criteria) this;
        }

        public Criteria andFqtyIsNull() {
            addCriterion("fqty is null");
            return (Criteria) this;
        }

        public Criteria andFqtyIsNotNull() {
            addCriterion("fqty is not null");
            return (Criteria) this;
        }

        public Criteria andFqtyEqualTo(BigDecimal value) {
            addCriterion("fqty =", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyNotEqualTo(BigDecimal value) {
            addCriterion("fqty <>", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyGreaterThan(BigDecimal value) {
            addCriterion("fqty >", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty >=", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyLessThan(BigDecimal value) {
            addCriterion("fqty <", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty <=", value, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyIn(List<BigDecimal> values) {
            addCriterion("fqty in", values, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyNotIn(List<BigDecimal> values) {
            addCriterion("fqty not in", values, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty between", value1, value2, "fqty");
            return (Criteria) this;
        }

        public Criteria andFqtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty not between", value1, value2, "fqty");
            return (Criteria) this;
        }

        public Criteria andFunitIsNull() {
            addCriterion("funit is null");
            return (Criteria) this;
        }

        public Criteria andFunitIsNotNull() {
            addCriterion("funit is not null");
            return (Criteria) this;
        }

        public Criteria andFunitEqualTo(String value) {
            addCriterion("funit =", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitNotEqualTo(String value) {
            addCriterion("funit <>", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitGreaterThan(String value) {
            addCriterion("funit >", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitGreaterThanOrEqualTo(String value) {
            addCriterion("funit >=", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitLessThan(String value) {
            addCriterion("funit <", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitLessThanOrEqualTo(String value) {
            addCriterion("funit <=", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitLike(String value) {
            addCriterion("funit like", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitNotLike(String value) {
            addCriterion("funit not like", value, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitIn(List<String> values) {
            addCriterion("funit in", values, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitNotIn(List<String> values) {
            addCriterion("funit not in", values, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitBetween(String value1, String value2) {
            addCriterion("funit between", value1, value2, "funit");
            return (Criteria) this;
        }

        public Criteria andFunitNotBetween(String value1, String value2) {
            addCriterion("funit not between", value1, value2, "funit");
            return (Criteria) this;
        }

        public Criteria andFmemoIsNull() {
            addCriterion("fmemo is null");
            return (Criteria) this;
        }

        public Criteria andFmemoIsNotNull() {
            addCriterion("fmemo is not null");
            return (Criteria) this;
        }

        public Criteria andFmemoEqualTo(String value) {
            addCriterion("fmemo =", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotEqualTo(String value) {
            addCriterion("fmemo <>", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoGreaterThan(String value) {
            addCriterion("fmemo >", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoGreaterThanOrEqualTo(String value) {
            addCriterion("fmemo >=", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLessThan(String value) {
            addCriterion("fmemo <", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLessThanOrEqualTo(String value) {
            addCriterion("fmemo <=", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLike(String value) {
            addCriterion("fmemo like", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotLike(String value) {
            addCriterion("fmemo not like", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoIn(List<String> values) {
            addCriterion("fmemo in", values, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotIn(List<String> values) {
            addCriterion("fmemo not in", values, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoBetween(String value1, String value2) {
            addCriterion("fmemo between", value1, value2, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotBetween(String value1, String value2) {
            addCriterion("fmemo not between", value1, value2, "fmemo");
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