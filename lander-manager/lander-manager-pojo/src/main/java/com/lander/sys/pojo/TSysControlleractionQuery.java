package com.lander.sys.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysControlleractionQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public TSysControlleractionQuery() {
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

        public Criteria andFidEqualTo(Integer value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Integer value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Integer value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Integer value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Integer value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Integer> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Integer> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Integer value1, Integer value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Integer value1, Integer value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFparentidIsNull() {
            addCriterion("fparentid is null");
            return (Criteria) this;
        }

        public Criteria andFparentidIsNotNull() {
            addCriterion("fparentid is not null");
            return (Criteria) this;
        }

        public Criteria andFparentidEqualTo(Integer value) {
            addCriterion("fparentid =", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotEqualTo(Integer value) {
            addCriterion("fparentid <>", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidGreaterThan(Integer value) {
            addCriterion("fparentid >", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fparentid >=", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidLessThan(Integer value) {
            addCriterion("fparentid <", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidLessThanOrEqualTo(Integer value) {
            addCriterion("fparentid <=", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidIn(List<Integer> values) {
            addCriterion("fparentid in", values, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotIn(List<Integer> values) {
            addCriterion("fparentid not in", values, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidBetween(Integer value1, Integer value2) {
            addCriterion("fparentid between", value1, value2, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotBetween(Integer value1, Integer value2) {
            addCriterion("fparentid not between", value1, value2, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFcaptionIsNull() {
            addCriterion("fcaption is null");
            return (Criteria) this;
        }

        public Criteria andFcaptionIsNotNull() {
            addCriterion("fcaption is not null");
            return (Criteria) this;
        }

        public Criteria andFcaptionEqualTo(String value) {
            addCriterion("fcaption =", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionNotEqualTo(String value) {
            addCriterion("fcaption <>", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionGreaterThan(String value) {
            addCriterion("fcaption >", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionGreaterThanOrEqualTo(String value) {
            addCriterion("fcaption >=", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionLessThan(String value) {
            addCriterion("fcaption <", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionLessThanOrEqualTo(String value) {
            addCriterion("fcaption <=", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionLike(String value) {
            addCriterion("fcaption like", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionNotLike(String value) {
            addCriterion("fcaption not like", value, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionIn(List<String> values) {
            addCriterion("fcaption in", values, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionNotIn(List<String> values) {
            addCriterion("fcaption not in", values, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionBetween(String value1, String value2) {
            addCriterion("fcaption between", value1, value2, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcaptionNotBetween(String value1, String value2) {
            addCriterion("fcaption not between", value1, value2, "fcaption");
            return (Criteria) this;
        }

        public Criteria andFcontrollerIsNull() {
            addCriterion("fcontroller is null");
            return (Criteria) this;
        }

        public Criteria andFcontrollerIsNotNull() {
            addCriterion("fcontroller is not null");
            return (Criteria) this;
        }

        public Criteria andFcontrollerEqualTo(String value) {
            addCriterion("fcontroller =", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerNotEqualTo(String value) {
            addCriterion("fcontroller <>", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerGreaterThan(String value) {
            addCriterion("fcontroller >", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerGreaterThanOrEqualTo(String value) {
            addCriterion("fcontroller >=", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerLessThan(String value) {
            addCriterion("fcontroller <", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerLessThanOrEqualTo(String value) {
            addCriterion("fcontroller <=", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerLike(String value) {
            addCriterion("fcontroller like", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerNotLike(String value) {
            addCriterion("fcontroller not like", value, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerIn(List<String> values) {
            addCriterion("fcontroller in", values, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerNotIn(List<String> values) {
            addCriterion("fcontroller not in", values, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerBetween(String value1, String value2) {
            addCriterion("fcontroller between", value1, value2, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFcontrollerNotBetween(String value1, String value2) {
            addCriterion("fcontroller not between", value1, value2, "fcontroller");
            return (Criteria) this;
        }

        public Criteria andFactionIsNull() {
            addCriterion("faction is null");
            return (Criteria) this;
        }

        public Criteria andFactionIsNotNull() {
            addCriterion("faction is not null");
            return (Criteria) this;
        }

        public Criteria andFactionEqualTo(String value) {
            addCriterion("faction =", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionNotEqualTo(String value) {
            addCriterion("faction <>", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionGreaterThan(String value) {
            addCriterion("faction >", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionGreaterThanOrEqualTo(String value) {
            addCriterion("faction >=", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionLessThan(String value) {
            addCriterion("faction <", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionLessThanOrEqualTo(String value) {
            addCriterion("faction <=", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionLike(String value) {
            addCriterion("faction like", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionNotLike(String value) {
            addCriterion("faction not like", value, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionIn(List<String> values) {
            addCriterion("faction in", values, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionNotIn(List<String> values) {
            addCriterion("faction not in", values, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionBetween(String value1, String value2) {
            addCriterion("faction between", value1, value2, "faction");
            return (Criteria) this;
        }

        public Criteria andFactionNotBetween(String value1, String value2) {
            addCriterion("faction not between", value1, value2, "faction");
            return (Criteria) this;
        }

        public Criteria andFisenableIsNull() {
            addCriterion("fisenable is null");
            return (Criteria) this;
        }

        public Criteria andFisenableIsNotNull() {
            addCriterion("fisenable is not null");
            return (Criteria) this;
        }

        public Criteria andFisenableEqualTo(Boolean value) {
            addCriterion("fisenable =", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableNotEqualTo(Boolean value) {
            addCriterion("fisenable <>", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableGreaterThan(Boolean value) {
            addCriterion("fisenable >", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fisenable >=", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableLessThan(Boolean value) {
            addCriterion("fisenable <", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableLessThanOrEqualTo(Boolean value) {
            addCriterion("fisenable <=", value, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableIn(List<Boolean> values) {
            addCriterion("fisenable in", values, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableNotIn(List<Boolean> values) {
            addCriterion("fisenable not in", values, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableBetween(Boolean value1, Boolean value2) {
            addCriterion("fisenable between", value1, value2, "fisenable");
            return (Criteria) this;
        }

        public Criteria andFisenableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fisenable not between", value1, value2, "fisenable");
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

        public Criteria andFisfreeIsNull() {
            addCriterion("fisfree is null");
            return (Criteria) this;
        }

        public Criteria andFisfreeIsNotNull() {
            addCriterion("fisfree is not null");
            return (Criteria) this;
        }

        public Criteria andFisfreeEqualTo(Boolean value) {
            addCriterion("fisfree =", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeNotEqualTo(Boolean value) {
            addCriterion("fisfree <>", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeGreaterThan(Boolean value) {
            addCriterion("fisfree >", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fisfree >=", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeLessThan(Boolean value) {
            addCriterion("fisfree <", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeLessThanOrEqualTo(Boolean value) {
            addCriterion("fisfree <=", value, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeIn(List<Boolean> values) {
            addCriterion("fisfree in", values, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeNotIn(List<Boolean> values) {
            addCriterion("fisfree not in", values, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeBetween(Boolean value1, Boolean value2) {
            addCriterion("fisfree between", value1, value2, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFisfreeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fisfree not between", value1, value2, "fisfree");
            return (Criteria) this;
        }

        public Criteria andFindexIsNull() {
            addCriterion("findex is null");
            return (Criteria) this;
        }

        public Criteria andFindexIsNotNull() {
            addCriterion("findex is not null");
            return (Criteria) this;
        }

        public Criteria andFindexEqualTo(Integer value) {
            addCriterion("findex =", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexNotEqualTo(Integer value) {
            addCriterion("findex <>", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexGreaterThan(Integer value) {
            addCriterion("findex >", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("findex >=", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexLessThan(Integer value) {
            addCriterion("findex <", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexLessThanOrEqualTo(Integer value) {
            addCriterion("findex <=", value, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexIn(List<Integer> values) {
            addCriterion("findex in", values, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexNotIn(List<Integer> values) {
            addCriterion("findex not in", values, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexBetween(Integer value1, Integer value2) {
            addCriterion("findex between", value1, value2, "findex");
            return (Criteria) this;
        }

        public Criteria andFindexNotBetween(Integer value1, Integer value2) {
            addCriterion("findex not between", value1, value2, "findex");
            return (Criteria) this;
        }

        public Criteria andFisparentIsNull() {
            addCriterion("fisparent is null");
            return (Criteria) this;
        }

        public Criteria andFisparentIsNotNull() {
            addCriterion("fisparent is not null");
            return (Criteria) this;
        }

        public Criteria andFisparentEqualTo(Boolean value) {
            addCriterion("fisparent =", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentNotEqualTo(Boolean value) {
            addCriterion("fisparent <>", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentGreaterThan(Boolean value) {
            addCriterion("fisparent >", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fisparent >=", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentLessThan(Boolean value) {
            addCriterion("fisparent <", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentLessThanOrEqualTo(Boolean value) {
            addCriterion("fisparent <=", value, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentIn(List<Boolean> values) {
            addCriterion("fisparent in", values, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentNotIn(List<Boolean> values) {
            addCriterion("fisparent not in", values, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentBetween(Boolean value1, Boolean value2) {
            addCriterion("fisparent between", value1, value2, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFisparentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fisparent not between", value1, value2, "fisparent");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidIsNull() {
            addCriterion("fitemtypeid is null");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidIsNotNull() {
            addCriterion("fitemtypeid is not null");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidEqualTo(Byte value) {
            addCriterion("fitemtypeid =", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidNotEqualTo(Byte value) {
            addCriterion("fitemtypeid <>", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidGreaterThan(Byte value) {
            addCriterion("fitemtypeid >", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidGreaterThanOrEqualTo(Byte value) {
            addCriterion("fitemtypeid >=", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidLessThan(Byte value) {
            addCriterion("fitemtypeid <", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidLessThanOrEqualTo(Byte value) {
            addCriterion("fitemtypeid <=", value, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidIn(List<Byte> values) {
            addCriterion("fitemtypeid in", values, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidNotIn(List<Byte> values) {
            addCriterion("fitemtypeid not in", values, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidBetween(Byte value1, Byte value2) {
            addCriterion("fitemtypeid between", value1, value2, "fitemtypeid");
            return (Criteria) this;
        }

        public Criteria andFitemtypeidNotBetween(Byte value1, Byte value2) {
            addCriterion("fitemtypeid not between", value1, value2, "fitemtypeid");
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