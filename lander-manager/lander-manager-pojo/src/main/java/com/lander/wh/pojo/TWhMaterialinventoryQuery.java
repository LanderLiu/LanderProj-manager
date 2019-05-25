package com.lander.wh.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWhMaterialinventoryQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TWhMaterialinventoryQuery() {
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
            addCriterion("FCreateMan is null");
            return (Criteria) this;
        }

        public Criteria andFcreatemanIsNotNull() {
            addCriterion("FCreateMan is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatemanEqualTo(String value) {
            addCriterion("FCreateMan =", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotEqualTo(String value) {
            addCriterion("FCreateMan <>", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanGreaterThan(String value) {
            addCriterion("FCreateMan >", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanGreaterThanOrEqualTo(String value) {
            addCriterion("FCreateMan >=", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLessThan(String value) {
            addCriterion("FCreateMan <", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLessThanOrEqualTo(String value) {
            addCriterion("FCreateMan <=", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanLike(String value) {
            addCriterion("FCreateMan like", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotLike(String value) {
            addCriterion("FCreateMan not like", value, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanIn(List<String> values) {
            addCriterion("FCreateMan in", values, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotIn(List<String> values) {
            addCriterion("FCreateMan not in", values, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanBetween(String value1, String value2) {
            addCriterion("FCreateMan between", value1, value2, "fcreateman");
            return (Criteria) this;
        }

        public Criteria andFcreatemanNotBetween(String value1, String value2) {
            addCriterion("FCreateMan not between", value1, value2, "fcreateman");
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

        public Criteria andFperiodidIsNull() {
            addCriterion("fperiodid is null");
            return (Criteria) this;
        }

        public Criteria andFperiodidIsNotNull() {
            addCriterion("fperiodid is not null");
            return (Criteria) this;
        }

        public Criteria andFperiodidEqualTo(Long value) {
            addCriterion("fperiodid =", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidNotEqualTo(Long value) {
            addCriterion("fperiodid <>", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidGreaterThan(Long value) {
            addCriterion("fperiodid >", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidGreaterThanOrEqualTo(Long value) {
            addCriterion("fperiodid >=", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidLessThan(Long value) {
            addCriterion("fperiodid <", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidLessThanOrEqualTo(Long value) {
            addCriterion("fperiodid <=", value, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidIn(List<Long> values) {
            addCriterion("fperiodid in", values, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidNotIn(List<Long> values) {
            addCriterion("fperiodid not in", values, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidBetween(Long value1, Long value2) {
            addCriterion("fperiodid between", value1, value2, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFperiodidNotBetween(Long value1, Long value2) {
            addCriterion("fperiodid not between", value1, value2, "fperiodid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidIsNull() {
            addCriterion("fwarehouseid is null");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidIsNotNull() {
            addCriterion("fwarehouseid is not null");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidEqualTo(Long value) {
            addCriterion("fwarehouseid =", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidNotEqualTo(Long value) {
            addCriterion("fwarehouseid <>", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidGreaterThan(Long value) {
            addCriterion("fwarehouseid >", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidGreaterThanOrEqualTo(Long value) {
            addCriterion("fwarehouseid >=", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidLessThan(Long value) {
            addCriterion("fwarehouseid <", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidLessThanOrEqualTo(Long value) {
            addCriterion("fwarehouseid <=", value, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidIn(List<Long> values) {
            addCriterion("fwarehouseid in", values, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidNotIn(List<Long> values) {
            addCriterion("fwarehouseid not in", values, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidBetween(Long value1, Long value2) {
            addCriterion("fwarehouseid between", value1, value2, "fwarehouseid");
            return (Criteria) this;
        }

        public Criteria andFwarehouseidNotBetween(Long value1, Long value2) {
            addCriterion("fwarehouseid not between", value1, value2, "fwarehouseid");
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

        public Criteria andFqtyInitIsNull() {
            addCriterion("fqty_init is null");
            return (Criteria) this;
        }

        public Criteria andFqtyInitIsNotNull() {
            addCriterion("fqty_init is not null");
            return (Criteria) this;
        }

        public Criteria andFqtyInitEqualTo(BigDecimal value) {
            addCriterion("fqty_init =", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitNotEqualTo(BigDecimal value) {
            addCriterion("fqty_init <>", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitGreaterThan(BigDecimal value) {
            addCriterion("fqty_init >", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty_init >=", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitLessThan(BigDecimal value) {
            addCriterion("fqty_init <", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty_init <=", value, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitIn(List<BigDecimal> values) {
            addCriterion("fqty_init in", values, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitNotIn(List<BigDecimal> values) {
            addCriterion("fqty_init not in", values, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty_init between", value1, value2, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyInitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty_init not between", value1, value2, "fqtyInit");
            return (Criteria) this;
        }

        public Criteria andFqtyEndIsNull() {
            addCriterion("fqty_end is null");
            return (Criteria) this;
        }

        public Criteria andFqtyEndIsNotNull() {
            addCriterion("fqty_end is not null");
            return (Criteria) this;
        }

        public Criteria andFqtyEndEqualTo(BigDecimal value) {
            addCriterion("fqty_end =", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndNotEqualTo(BigDecimal value) {
            addCriterion("fqty_end <>", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndGreaterThan(BigDecimal value) {
            addCriterion("fqty_end >", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty_end >=", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndLessThan(BigDecimal value) {
            addCriterion("fqty_end <", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fqty_end <=", value, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndIn(List<BigDecimal> values) {
            addCriterion("fqty_end in", values, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndNotIn(List<BigDecimal> values) {
            addCriterion("fqty_end not in", values, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty_end between", value1, value2, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFqtyEndNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fqty_end not between", value1, value2, "fqtyEnd");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameIsNull() {
            addCriterion("fwarehousename is null");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameIsNotNull() {
            addCriterion("fwarehousename is not null");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameEqualTo(String value) {
            addCriterion("fwarehousename =", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameNotEqualTo(String value) {
            addCriterion("fwarehousename <>", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameGreaterThan(String value) {
            addCriterion("fwarehousename >", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameGreaterThanOrEqualTo(String value) {
            addCriterion("fwarehousename >=", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameLessThan(String value) {
            addCriterion("fwarehousename <", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameLessThanOrEqualTo(String value) {
            addCriterion("fwarehousename <=", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameLike(String value) {
            addCriterion("fwarehousename like", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameNotLike(String value) {
            addCriterion("fwarehousename not like", value, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameIn(List<String> values) {
            addCriterion("fwarehousename in", values, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameNotIn(List<String> values) {
            addCriterion("fwarehousename not in", values, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameBetween(String value1, String value2) {
            addCriterion("fwarehousename between", value1, value2, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenameNotBetween(String value1, String value2) {
            addCriterion("fwarehousename not between", value1, value2, "fwarehousename");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberIsNull() {
            addCriterion("fwarehousenumber is null");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberIsNotNull() {
            addCriterion("fwarehousenumber is not null");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberEqualTo(String value) {
            addCriterion("fwarehousenumber =", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberNotEqualTo(String value) {
            addCriterion("fwarehousenumber <>", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberGreaterThan(String value) {
            addCriterion("fwarehousenumber >", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberGreaterThanOrEqualTo(String value) {
            addCriterion("fwarehousenumber >=", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberLessThan(String value) {
            addCriterion("fwarehousenumber <", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberLessThanOrEqualTo(String value) {
            addCriterion("fwarehousenumber <=", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberLike(String value) {
            addCriterion("fwarehousenumber like", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberNotLike(String value) {
            addCriterion("fwarehousenumber not like", value, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberIn(List<String> values) {
            addCriterion("fwarehousenumber in", values, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberNotIn(List<String> values) {
            addCriterion("fwarehousenumber not in", values, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberBetween(String value1, String value2) {
            addCriterion("fwarehousenumber between", value1, value2, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFwarehousenumberNotBetween(String value1, String value2) {
            addCriterion("fwarehousenumber not between", value1, value2, "fwarehousenumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberIsNull() {
            addCriterion("fperiodnumber is null");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberIsNotNull() {
            addCriterion("fperiodnumber is not null");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberEqualTo(String value) {
            addCriterion("fperiodnumber =", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberNotEqualTo(String value) {
            addCriterion("fperiodnumber <>", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberGreaterThan(String value) {
            addCriterion("fperiodnumber >", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberGreaterThanOrEqualTo(String value) {
            addCriterion("fperiodnumber >=", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberLessThan(String value) {
            addCriterion("fperiodnumber <", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberLessThanOrEqualTo(String value) {
            addCriterion("fperiodnumber <=", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberLike(String value) {
            addCriterion("fperiodnumber like", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberNotLike(String value) {
            addCriterion("fperiodnumber not like", value, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberIn(List<String> values) {
            addCriterion("fperiodnumber in", values, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberNotIn(List<String> values) {
            addCriterion("fperiodnumber not in", values, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberBetween(String value1, String value2) {
            addCriterion("fperiodnumber between", value1, value2, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnumberNotBetween(String value1, String value2) {
            addCriterion("fperiodnumber not between", value1, value2, "fperiodnumber");
            return (Criteria) this;
        }

        public Criteria andFperiodnameIsNull() {
            addCriterion("fperiodname is null");
            return (Criteria) this;
        }

        public Criteria andFperiodnameIsNotNull() {
            addCriterion("fperiodname is not null");
            return (Criteria) this;
        }

        public Criteria andFperiodnameEqualTo(String value) {
            addCriterion("fperiodname =", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameNotEqualTo(String value) {
            addCriterion("fperiodname <>", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameGreaterThan(String value) {
            addCriterion("fperiodname >", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameGreaterThanOrEqualTo(String value) {
            addCriterion("fperiodname >=", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameLessThan(String value) {
            addCriterion("fperiodname <", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameLessThanOrEqualTo(String value) {
            addCriterion("fperiodname <=", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameLike(String value) {
            addCriterion("fperiodname like", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameNotLike(String value) {
            addCriterion("fperiodname not like", value, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameIn(List<String> values) {
            addCriterion("fperiodname in", values, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameNotIn(List<String> values) {
            addCriterion("fperiodname not in", values, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameBetween(String value1, String value2) {
            addCriterion("fperiodname between", value1, value2, "fperiodname");
            return (Criteria) this;
        }

        public Criteria andFperiodnameNotBetween(String value1, String value2) {
            addCriterion("fperiodname not between", value1, value2, "fperiodname");
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