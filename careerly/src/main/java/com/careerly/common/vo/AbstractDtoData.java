package com.careerly.common.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import com.careerly.common.vo.help.VoHelper;

public  abstract class AbstractDtoData implements Serializable{

	private static final long serialVersionUID = 3840542017618332722L;
	
	private Map<String, List> composition = new HashMap<String, List>();
	

    /**
     * 获得组件Map
     *
     * @return
     */
    public Map<String, List> getComposition() {
        return this.composition;
    }

    /**
     * 设置组件Map
     *
     * @param composition
     */
    public void setComposition(Map<String, List> composition) {
        this.composition = composition;
    }

    /**
     * 获得组件列表
     *
     * @return
     */
    public Collection<List> getComponentCollection() {
        return this.composition.values();
    }
    
    /**
     * 获得组件名称集合
     *
     * @return
     */
    public Set<String> getComponentNameSet() {
        return this.composition.keySet();
    }
    
    /**
     * @param componentName
     * @return
     */
    public boolean containsComponent(String componentName) {
        return this.getComponentNameSet().contains(componentName);
    }
    
    
    /**
     * 获得组件(List<Vo>)
     *
     * @param componentName
     * @return
     */
    public List getComponent(String componentName) {
        return this.composition.get(componentName);
    }
    
    
    /**
     * 设置组件
     *
     * @param componentName
     * @param component
     */
    public void putComponent(String componentName, List component) {
        this.composition.put(componentName, component);
    }
    
    
    /**
     * 去除组件
     *
     * @param componentName
     */
    public void removeComponent(String componentName) {
        this.composition.remove(componentName);
    }
    
    
    /**
     * 获得单记录组件VO
     *
     * @param componentName
     * @return
     */
    public Object getMonoComponent(String componentName) {
        List dataList = this.composition.get(componentName);
        if (CollectionUtils.isNotEmpty(dataList)) {
            return dataList.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * 设置单记录组件
     *
     * @param componentName
     * @param entry
     */
    public void putMonoComponent(String componentName, Object entry) {
        List component = new ArrayList();
        component.add(entry);
        this.composition.put(componentName, component);
    }
    
    
    /**
     *
     * @param src
     *           源DTO对象
     * @param voType
     *            复制的对象
     * @return
     */
    public AbstractDtoData clone(String voType) {
    	AbstractDtoData dst = null;
        if (voType.equals(VoHelper.VO_TYPE_DTO)) {
            dst = new DtoApplication();
        } 

        try {
            String VoType = voType;
            for (String comName : this.getComposition().keySet()) {
                    List dstComponent = new ArrayList();
                    List srcComponent = this.getComponent(comName);
                    Class clazz = null;
                    for (Object srcEntry : srcComponent) {
                    	if(srcEntry!=null){
                    	clazz =(clazz ==null)?VoHelper.getVoClass(srcEntry):clazz;
                        Object dstEntry = clazz.newInstance();
                        PropertyUtils.copyProperties(dstEntry, srcEntry);
                        dstComponent.add(dstEntry);
                    	}
                    }
                    dst.putComponent(comName, dstComponent);
            }

            return dst;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
