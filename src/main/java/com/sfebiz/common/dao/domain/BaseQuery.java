package com.sfebiz.common.dao.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午3:46
 */
public class BaseQuery<E> extends BaseParms {

    protected String sum;

    /**
     * 一般的查询条件放这里
     */
    protected E data;
    /**
     * group by
     */
    protected List<String> groupBys;
    /**
     * order by
     */
    protected List<OrderBy> orderBys;


    /**
     * in(xxx, xxx,xxx)
     */
    protected List<InOrNotin> ins;
    /**
     * not in(xxx,xxx,xxx)
     */
    protected List<InOrNotin> notIns;
    //=
    protected List<Parm> equals;
    //=
    protected List<Parm> notEquals;
    //大于
    protected List<Parm> gts;
    //小yu
    protected List<Parm> lts;
    //>=
    protected List<Parm> gtes;
    //<=
    protected List<Parm> ltes;
    //likes
    protected List<Parm> likes;
    /**
     * 范围
     */
    protected List<Range> ranges;

    protected Map<String, Object> features;

    private final static String DESC = "desc";
    private final static String ASC = "asc";

    protected BaseQuery() {
        super();
    }

    public BaseQuery(E e) {
        super();
        this.data = e;
    }


    public static class DIRECTION implements Serializable {
        public final static Integer DESC = 0;
        public final static Integer ASC = 1;
    }


    public void addFeature(String key, Object value) {
        if (features == null) {
            features = new HashMap<String, Object>();
        }
        features.put(key, value);
    }

    public void addOrderBy(String column, int direction) {
        if (orderBys == null) {
            orderBys = new ArrayList<OrderBy>();
        }
        OrderBy ob = new OrderBy();
        ob.setColumn(getColumnByProperty(column));
        ob.setDirection(direction == 1 ? ASC : DESC);
        this.orderBys.add(ob);
    }

    public void addGroupBy(String column) {
        if (groupBys == null) {
            groupBys = new ArrayList<String>();
        }
        this.groupBys.add(getColumnByProperty(column));
    }

    public void addIn(String column, List values) {
        if (StringUtils.isBlank(column) || values == null || values.size() == 0) {
            return;
        }
        if (ins == null) {
            ins = new ArrayList<InOrNotin>();
        }
        InOrNotin in = new InOrNotin();
        in.setColumn(getColumnByProperty(column));
        in.setValues(values);
        this.ins.add(in);
    }

    public void addNotIn(String column, List values) {
        if (StringUtils.isBlank(column) || values == null || values.size() == 0) {
            return;
        }
        if (notIns == null) {
            notIns = new ArrayList<InOrNotin>();
        }
        InOrNotin in = new InOrNotin();
        in.setColumn(getColumnByProperty(column));
        in.setValues(values);
        this.notIns.add(in);
    }


    public void addEquals(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (equals == null) {
            this.equals = new ArrayList<Parm>();
        }
        this.equals.add(new Parm(getColumnByProperty(column), value));
    }

    public void addNotEquals(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (notEquals == null) {
            this.notEquals = new ArrayList<Parm>();
        }
        this.notEquals.add(new Parm(getColumnByProperty(column), value));
    }

    public void addGt(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (gts == null) {
            this.gts = new ArrayList<Parm>();
        }
        this.gts.add(new Parm(getColumnByProperty(column), value));
    }

    public void addLt(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (lts == null) {
            this.lts = new ArrayList<Parm>();
        }
        this.lts.add(new Parm(getColumnByProperty(column), value));
    }


    public void addGte(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (gtes == null) {
            this.gtes = new ArrayList<Parm>();
        }
        this.gtes.add(new Parm(getColumnByProperty(column), value));
    }


    public void addLte(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (ltes == null) {
            this.ltes = new ArrayList<Parm>();
        }
        this.ltes.add(new Parm(getColumnByProperty(column), value));
    }

    public void addRange(String column, Object from, Object to) {
        if (StringUtils.isBlank(column) || from == null || to == null) {
            return;
        }
        if (ranges == null) {
            ranges = new ArrayList<Range>();
        }
        ranges.add(new Range(getColumnByProperty(column), from, to));
    }


    public void addLike(String column, Object value) {
        if (StringUtils.isBlank(column) || value == null) {
            return;
        }
        if (likes == null) {
            this.likes = new ArrayList<Parm>();
        }
        this.likes.add(new Parm(getColumnByProperty(column), value));
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public List<InOrNotin> getIns() {
        return ins;
    }

    public List<InOrNotin> getNotIns() {
        return notIns;
    }

    public List<Parm> getEquals() {
        return equals;
    }

    public List<Parm> getGts() {
        return gts;
    }

    public List<Parm> getLts() {
        return lts;
    }

    public List<Parm> getGtes() {
        return gtes;
    }

    public List<Parm> getLtes() {
        return ltes;
    }

    public String getSum() {
        return sum;
    }


    public List<String> getGroupBys() {
        return groupBys;
    }

    public List<OrderBy> getOrderBys() {
        return orderBys;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }


    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    /**
     * 通过查询条件获取实例
     *
     * @param data
     * @param <O>
     * @return
     */
    public static <O> BaseQuery<O> getInstance(O data) {
        BaseQuery<O> baseQuery = new BaseQuery<O>();
        baseQuery.setData(data);

        return baseQuery;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String getColumnByProperty(String property) {
        if (isNotBlank(property)) {
            if (property.length() == 1) {
                return property.toLowerCase();
            }
            char[] chars = property.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char c : chars) {
                if (c >= 'A' && c <= 'Z') {
                    sb.append("_").append((char) (c + 32));
                } else {
                    sb.append(c);
                }


            }
            return sb.toString();
        }
        return property;
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A_";
        System.out.println(getColumnByProperty(s));
    }
}
