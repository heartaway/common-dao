package com.sfebiz.common.dao.domain;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/6 上午10:44
 */
public abstract class FeatureSupportBaseDO extends BaseDO {

    private static final long serialVersionUID = -4700827684696991151L;

    protected Map<String, Object> featureMap = null;

    /**
     * 初始化容器
     */
    private void initFeatureMap() {
        this.featureMap = new HashMap<String, Object>(10);
    }

    public Map<String, Object> getFeatureMap() {
        return featureMap;
    }

    public Map<String, Object> getFeaturesAsMap() {
        return getFeatureMap();
    }

    private void setFeatureMap(Map<String, Object> featureMap) {
        this.featureMap = featureMap;
    }

    public void addFeature(String key, Object value) {
        if (featureMap == null) {
            initFeatureMap();
        }
        featureMap.put(key, value);
    }

    public void addFeature(Map<String, Object> add) {
        if (featureMap == null) {
            initFeatureMap();
        }
        featureMap.putAll(add);
    }

    public Object getFeature(String key) {
        if (featureMap == null) {
            return null;
        }
        return featureMap.get(key);
    }

    public void removeFeature(String key) {
        if (featureMap != null) {
            featureMap.remove(key);
        }
    }

    //清空map,反应到数据库中为清空features的内容
    public void removeAll() {
        initFeatureMap();
    }

    public String getFeatures() {
        //如果featureMap为null，则表示不修改features字段，所以需要返回null
        if (featureMap == null) {
            return null;
        }
        return JSON.toJSONString(featureMap);
    }

    public void setFeatures(String features) {
        //容错一下，null传到JsonUtil.parseJson会抛nullPointer异常
        if (features == null) {
            return;
        }
        featureMap = (Map<String, Object>) JSON.parseObject(features, Map.class);
    }

    @Override
    public String toString() {
        return "FeatureSupportBaseDO{" +
                "featureMap=" + featureMap +
                "} " + super.toString();
    }
}

