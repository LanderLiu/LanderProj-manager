package com.lander.sale.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TSaleOrderQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TSaleOrderQuery() {
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

        public Criteria andFbizdatetimeIsNull() {
            addCriterion("fbizdatetime is null");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeIsNotNull() {
            addCriterion("fbizdatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("fbizdatetime =", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("fbizdatetime <>", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("fbizdatetime >", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fbizdatetime >=", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeLessThan(Date value) {
            addCriterionForJDBCDate("fbizdatetime <", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fbizdatetime <=", value, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("fbizdatetime in", values, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("fbizdatetime not in", values, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fbizdatetime between", value1, value2, "fbizdatetime");
            return (Criteria) this;
        }

        public Criteria andFbizdatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fbizdatetime not between", value1, value2, "fbizdatetime");
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

        public Criteria andFcustidIsNull() {
            addCriterion("fcustid is null");
            return (Criteria) this;
        }

        public Criteria andFcustidIsNotNull() {
            addCriterion("fcustid is not null");
            return (Criteria) this;
        }

        public Criteria andFcustidEqualTo(Long value) {
            addCriterion("fcustid =", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidNotEqualTo(Long value) {
            addCriterion("fcustid <>", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidGreaterThan(Long value) {
            addCriterion("fcustid >", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidGreaterThanOrEqualTo(Long value) {
            addCriterion("fcustid >=", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidLessThan(Long value) {
            addCriterion("fcustid <", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidLessThanOrEqualTo(Long value) {
            addCriterion("fcustid <=", value, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidIn(List<Long> values) {
            addCriterion("fcustid in", values, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidNotIn(List<Long> values) {
            addCriterion("fcustid not in", values, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidBetween(Long value1, Long value2) {
            addCriterion("fcustid between", value1, value2, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustidNotBetween(Long value1, Long value2) {
            addCriterion("fcustid not between", value1, value2, "fcustid");
            return (Criteria) this;
        }

        public Criteria andFcustnameIsNull() {
            addCriterion("fcustname is null");
            return (Criteria) this;
        }

        public Criteria andFcustnameIsNotNull() {
            addCriterion("fcustname is not null");
            return (Criteria) this;
        }

        public Criteria andFcustnameEqualTo(String value) {
            addCriterion("fcustname =", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameNotEqualTo(String value) {
            addCriterion("fcustname <>", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameGreaterThan(String value) {
            addCriterion("fcustname >", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameGreaterThanOrEqualTo(String value) {
            addCriterion("fcustname >=", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameLessThan(String value) {
            addCriterion("fcustname <", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameLessThanOrEqualTo(String value) {
            addCriterion("fcustname <=", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameLike(String value) {
            addCriterion("fcustname like", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameNotLike(String value) {
            addCriterion("fcustname not like", value, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameIn(List<String> values) {
            addCriterion("fcustname in", values, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameNotIn(List<String> values) {
            addCriterion("fcustname not in", values, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameBetween(String value1, String value2) {
            addCriterion("fcustname between", value1, value2, "fcustname");
            return (Criteria) this;
        }

        public Criteria andFcustnameNotBetween(String value1, String value2) {
            addCriterion("fcustname not between", value1, value2, "fcustname");
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

        public Criteria andFauditmemoIsNull() {
            addCriterion("fauditmemo is null");
            return (Criteria) this;
        }

        public Criteria andFauditmemoIsNotNull() {
            addCriterion("fauditmemo is not null");
            return (Criteria) this;
        }

        public Criteria andFauditmemoEqualTo(String value) {
            addCriterion("fauditmemo =", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoNotEqualTo(String value) {
            addCriterion("fauditmemo <>", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoGreaterThan(String value) {
            addCriterion("fauditmemo >", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoGreaterThanOrEqualTo(String value) {
            addCriterion("fauditmemo >=", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoLessThan(String value) {
            addCriterion("fauditmemo <", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoLessThanOrEqualTo(String value) {
            addCriterion("fauditmemo <=", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoLike(String value) {
            addCriterion("fauditmemo like", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoNotLike(String value) {
            addCriterion("fauditmemo not like", value, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoIn(List<String> values) {
            addCriterion("fauditmemo in", values, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoNotIn(List<String> values) {
            addCriterion("fauditmemo not in", values, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoBetween(String value1, String value2) {
            addCriterion("fauditmemo between", value1, value2, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmemoNotBetween(String value1, String value2) {
            addCriterion("fauditmemo not between", value1, value2, "fauditmemo");
            return (Criteria) this;
        }

        public Criteria andFauditmanIsNull() {
            addCriterion("fauditMan is null");
            return (Criteria) this;
        }

        public Criteria andFauditmanIsNotNull() {
            addCriterion("fauditMan is not null");
            return (Criteria) this;
        }

        public Criteria andFauditmanEqualTo(String value) {
            addCriterion("fauditMan =", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanNotEqualTo(String value) {
            addCriterion("fauditMan <>", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanGreaterThan(String value) {
            addCriterion("fauditMan >", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanGreaterThanOrEqualTo(String value) {
            addCriterion("fauditMan >=", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanLessThan(String value) {
            addCriterion("fauditMan <", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanLessThanOrEqualTo(String value) {
            addCriterion("fauditMan <=", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanLike(String value) {
            addCriterion("fauditMan like", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanNotLike(String value) {
            addCriterion("fauditMan not like", value, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanIn(List<String> values) {
            addCriterion("fauditMan in", values, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanNotIn(List<String> values) {
            addCriterion("fauditMan not in", values, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanBetween(String value1, String value2) {
            addCriterion("fauditMan between", value1, value2, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditmanNotBetween(String value1, String value2) {
            addCriterion("fauditMan not between", value1, value2, "fauditman");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeIsNull() {
            addCriterion("fauditdatetime is null");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeIsNotNull() {
            addCriterion("fauditdatetime is not null");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeEqualTo(Date value) {
            addCriterion("fauditdatetime =", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeNotEqualTo(Date value) {
            addCriterion("fauditdatetime <>", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeGreaterThan(Date value) {
            addCriterion("fauditdatetime >", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fauditdatetime >=", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeLessThan(Date value) {
            addCriterion("fauditdatetime <", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("fauditdatetime <=", value, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeIn(List<Date> values) {
            addCriterion("fauditdatetime in", values, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeNotIn(List<Date> values) {
            addCriterion("fauditdatetime not in", values, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeBetween(Date value1, Date value2) {
            addCriterion("fauditdatetime between", value1, value2, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFauditdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("fauditdatetime not between", value1, value2, "fauditdatetime");
            return (Criteria) this;
        }

        public Criteria andFitemcountIsNull() {
            addCriterion("fitemcount is null");
            return (Criteria) this;
        }

        public Criteria andFitemcountIsNotNull() {
            addCriterion("fitemcount is not null");
            return (Criteria) this;
        }

        public Criteria andFitemcountEqualTo(Integer value) {
            addCriterion("fitemcount =", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountNotEqualTo(Integer value) {
            addCriterion("fitemcount <>", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountGreaterThan(Integer value) {
            addCriterion("fitemcount >", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("fitemcount >=", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountLessThan(Integer value) {
            addCriterion("fitemcount <", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountLessThanOrEqualTo(Integer value) {
            addCriterion("fitemcount <=", value, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountIn(List<Integer> values) {
            addCriterion("fitemcount in", values, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountNotIn(List<Integer> values) {
            addCriterion("fitemcount not in", values, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountBetween(Integer value1, Integer value2) {
            addCriterion("fitemcount between", value1, value2, "fitemcount");
            return (Criteria) this;
        }

        public Criteria andFitemcountNotBetween(Integer value1, Integer value2) {
            addCriterion("fitemcount not between", value1, value2, "fitemcount");
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