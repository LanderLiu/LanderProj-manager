package com.lander.wh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWhPeriodQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TWhPeriodQuery() {
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

        public Criteria andFnumberIsNull() {
            addCriterion("fnumber is null");
            return (Criteria) this;
        }

        public Criteria andFnumberIsNotNull() {
            addCriterion("fnumber is not null");
            return (Criteria) this;
        }

        public Criteria andFnumberEqualTo(String value) {
            addCriterion("fnumber =", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberNotEqualTo(String value) {
            addCriterion("fnumber <>", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberGreaterThan(String value) {
            addCriterion("fnumber >", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberGreaterThanOrEqualTo(String value) {
            addCriterion("fnumber >=", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberLessThan(String value) {
            addCriterion("fnumber <", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberLessThanOrEqualTo(String value) {
            addCriterion("fnumber <=", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberLike(String value) {
            addCriterion("fnumber like", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberNotLike(String value) {
            addCriterion("fnumber not like", value, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberIn(List<String> values) {
            addCriterion("fnumber in", values, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberNotIn(List<String> values) {
            addCriterion("fnumber not in", values, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberBetween(String value1, String value2) {
            addCriterion("fnumber between", value1, value2, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnumberNotBetween(String value1, String value2) {
            addCriterion("fnumber not between", value1, value2, "fnumber");
            return (Criteria) this;
        }

        public Criteria andFnameIsNull() {
            addCriterion("fname is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("fname is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("fname =", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("fname <>", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("fname >", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("fname >=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("fname <", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("fname <=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLike(String value) {
            addCriterion("fname like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotLike(String value) {
            addCriterion("fname not like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("fname in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("fname not in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("fname between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("fname not between", value1, value2, "fname");
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

        public Criteria andFfromIsNull() {
            addCriterion("ffrom is null");
            return (Criteria) this;
        }

        public Criteria andFfromIsNotNull() {
            addCriterion("ffrom is not null");
            return (Criteria) this;
        }

        public Criteria andFfromEqualTo(Date value) {
            addCriterion("ffrom =", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotEqualTo(Date value) {
            addCriterion("ffrom <>", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromGreaterThan(Date value) {
            addCriterion("ffrom >", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromGreaterThanOrEqualTo(Date value) {
            addCriterion("ffrom >=", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromLessThan(Date value) {
            addCriterion("ffrom <", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromLessThanOrEqualTo(Date value) {
            addCriterion("ffrom <=", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromIn(List<Date> values) {
            addCriterion("ffrom in", values, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotIn(List<Date> values) {
            addCriterion("ffrom not in", values, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromBetween(Date value1, Date value2) {
            addCriterion("ffrom between", value1, value2, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotBetween(Date value1, Date value2) {
            addCriterion("ffrom not between", value1, value2, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFtoIsNull() {
            addCriterion("fto is null");
            return (Criteria) this;
        }

        public Criteria andFtoIsNotNull() {
            addCriterion("fto is not null");
            return (Criteria) this;
        }

        public Criteria andFtoEqualTo(Date value) {
            addCriterion("fto =", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoNotEqualTo(Date value) {
            addCriterion("fto <>", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoGreaterThan(Date value) {
            addCriterion("fto >", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoGreaterThanOrEqualTo(Date value) {
            addCriterion("fto >=", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoLessThan(Date value) {
            addCriterion("fto <", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoLessThanOrEqualTo(Date value) {
            addCriterion("fto <=", value, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoIn(List<Date> values) {
            addCriterion("fto in", values, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoNotIn(List<Date> values) {
            addCriterion("fto not in", values, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoBetween(Date value1, Date value2) {
            addCriterion("fto between", value1, value2, "fto");
            return (Criteria) this;
        }

        public Criteria andFtoNotBetween(Date value1, Date value2) {
            addCriterion("fto not between", value1, value2, "fto");
            return (Criteria) this;
        }

        public Criteria andFstateidIsNull() {
            addCriterion("fstateid is null");
            return (Criteria) this;
        }

        public Criteria andFstateidIsNotNull() {
            addCriterion("fstateid is not null");
            return (Criteria) this;
        }

        public Criteria andFstateidEqualTo(Integer value) {
            addCriterion("fstateid =", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidNotEqualTo(Integer value) {
            addCriterion("fstateid <>", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidGreaterThan(Integer value) {
            addCriterion("fstateid >", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fstateid >=", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidLessThan(Integer value) {
            addCriterion("fstateid <", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidLessThanOrEqualTo(Integer value) {
            addCriterion("fstateid <=", value, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidIn(List<Integer> values) {
            addCriterion("fstateid in", values, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidNotIn(List<Integer> values) {
            addCriterion("fstateid not in", values, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidBetween(Integer value1, Integer value2) {
            addCriterion("fstateid between", value1, value2, "fstateid");
            return (Criteria) this;
        }

        public Criteria andFstateidNotBetween(Integer value1, Integer value2) {
            addCriterion("fstateid not between", value1, value2, "fstateid");
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

        public Criteria andFiscurrentIsNull() {
            addCriterion("fiscurrent is null");
            return (Criteria) this;
        }

        public Criteria andFiscurrentIsNotNull() {
            addCriterion("fiscurrent is not null");
            return (Criteria) this;
        }

        public Criteria andFiscurrentEqualTo(Integer value) {
            addCriterion("fiscurrent =", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentNotEqualTo(Integer value) {
            addCriterion("fiscurrent <>", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentGreaterThan(Integer value) {
            addCriterion("fiscurrent >", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentGreaterThanOrEqualTo(Integer value) {
            addCriterion("fiscurrent >=", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentLessThan(Integer value) {
            addCriterion("fiscurrent <", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentLessThanOrEqualTo(Integer value) {
            addCriterion("fiscurrent <=", value, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentIn(List<Integer> values) {
            addCriterion("fiscurrent in", values, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentNotIn(List<Integer> values) {
            addCriterion("fiscurrent not in", values, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentBetween(Integer value1, Integer value2) {
            addCriterion("fiscurrent between", value1, value2, "fiscurrent");
            return (Criteria) this;
        }

        public Criteria andFiscurrentNotBetween(Integer value1, Integer value2) {
            addCriterion("fiscurrent not between", value1, value2, "fiscurrent");
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