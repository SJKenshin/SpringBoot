package com.kenshin.util;

import java.util.LinkedHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author kenshin.wang 将MAP对象按照子节点和属性集合生成可读性较好的对应的XML字符串 删除节点，增加节点，比较节点，查找等功能；可以从任何节点开始输出XML
 * 
 *
 */
public class MapToXml extends LinkedHashMap<String, Object> {

	private int totalcounts = 1; // 整个XML对应的总节点数

	private List<MapToXml> sonNodes;// 所有子节点

	private List<MapToXml> attributes;// 所有属性

	private int rank = 0;// 本节点为几级节点

	private MapToXml parentNode = null;

	public MapToXml(String key, Object value) {//
		super.put(key, value);
	}

	public int getRank() {
		return rank;
	}

	public int getTotalcounts() {
		return totalcounts;
	}

	public MapToXml put(String key, Object value) {// 防止PUT进了多个元素，确保一个MAP只有一个XML元素。子节点放在sonNodes中
		for (String s : this.keySet()) {
			if (s != null && !s.isEmpty()) {
				return this;
			}

		}
		super.put(key, value);
		return this;
	}

	public MapToXml getParentNode() {
		return parentNode;
	}

	public void setParentNode(MapToXml parentNode) {
		this.parentNode = parentNode;
	}

	public MapToXml() {// 生成空的节点

	}

	public List<MapToXml> getSonNods() {
		return sonNodes;
	}

	public List<MapToXml> getAttributes() {
		return attributes;
	}

	public MapToXml addNode(String key, Object value) {
		return addNode(new MapToXml(key, value));
	}

	public MapToXml addNode(MapToXml son) {// 添加子节点
		if (sonNodes == null) {
			sonNodes = new ArrayList<MapToXml>();
		}
		if (son != null) {// 更新rank和totalcounts的信息
			son.setParentNode(this);
			son.rank = this.rank + 1;
			List<MapToXml> sonNodes0 = son.getSonNods();
			if (sonNodes0 != null && !sonNodes0.isEmpty()) {
				for (MapToXml map : sonNodes0) {
					map.rank = son.rank + 1;
				}
			}
			this.totalcounts = this.totalcounts + son.totalcounts;
			MapToXml parent = this.getParentNode();
			if (parent != null) {
				parent.totalcounts = parent.totalcounts + son.totalcounts;
			}
			sonNodes.add(son);
		}
		return this;
	}

	public MapToXml addAttribute(String key, Object value) {
		return addAttribute(new MapToXml(key, ("\"" + value.toString() + "\"")));
	}

	public MapToXml addAttribute(MapToXml attr) {
		if (attributes == null) {
			attributes = new ArrayList<MapToXml>();
		}
		attributes.add(attr);
		return this;
	}

	public MapToXml remove(String name) {
		this.totalcounts=this.totalcounts-this.remove0(name);
		return this;
	}
	public int remove0(String name) {// 从本节点往下删除指定名称的节点(不包括本节点)
		int counts = 0;
		List<MapToXml> sonNodes=this.getSonNods();
		if(sonNodes!=null&&!sonNodes.isEmpty()) {	
			for(MapToXml map:sonNodes) {
				List<MapToXml> sonNodes1=map.getSonNods();
			    if(sonNodes1!=null&&!sonNodes1.isEmpty()) {
			    	counts=counts+map.remove0(name);
			    }else {
			    	if(map.get(name)!=null) {
			    		sonNodes.remove(map);
			    		counts++;
			    	}
			    }			
			}		
			
			
		}
		return counts;
	}

	public MapToXml getRootNode() {
		MapToXml tempNode = this;
		while (tempNode != null) {
			if (tempNode.getParentNode() == null) {
				return tempNode;
			} else {
				tempNode = tempNode.getParentNode();
			}
		}
		return tempNode;
	}

	public boolean isRootNode(MapToXml son) {
		return son.getParentNode() == null;
	}

	public boolean equalsTo(MapToXml other) throws Exception { // 生成的XML一样，就判定他们为相等
		if (other == null) {
			throw new Exception("other can not be null");
		}
		if (this == other) {
			return true;
		}
		if (this.equals(other)) {
			return true;
		}
		if (other.ToXmlFromRoot().equals(this.ToXmlFromRoot())) {
			return true;
		}
		return false;
	}

	public List<Object> findNodes(String name) {
		MapToXml root = this.getRootNode();
		return findNodeFromRoot(root, name);
	}

	private List<Object> findNodeFromRoot(Object root, String name) {
		List<Object> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		if (name.equals(getNodeName((MapToXml) root))) {
			list.add(root);
		}
		List<MapToXml> sonNodes = ((MapToXml) root).getSonNods();
		if (sonNodes != null && !sonNodes.isEmpty()) {
			for (MapToXml map : sonNodes) {
				Object n = map.get(name);
				if (n != null) {//找到了就放到LIST
					list.add(map);
				}
				List<Object> list_next = findNodeFromRoot(map, name);//继续寻找其子节点
				if (list_next != null && !list_next.isEmpty()) {
					list.addAll(list_next);
				}

			}
		}

		return list;
	}

	private String getNodeName(MapToXml root) {
		String n = "";
		for (String k : root.keySet()) {
			n = k;
		}
		return n;

	}

	public String ToXmlFromRoot() {// 从根节点开始生成XML
		MapToXml root = getRootNode();
		if (root != null) {
			return ToXmlFromRoot(getNodeName(root), root);
		}
		return "";
	}

	public String ToXml(String name) {// 从第一个符合此名称的节点的开始生成
		MapToXml node = (MapToXml) findNodes(name).get(0);
		if (node != null) {
			return ToXmlFromRoot(name, node);
		}
		return "";
	}

	private String ToXmlFromRoot(String rootname, MapToXml root) {
		StringBuilder builder = new StringBuilder();
		int i = root.rank;
		while (i > 0) {// 根据几级节点生成空格
			builder.append(" ");
			i--;
		}
		builder.append("<" + rootname);// 前缀和属性的生成
		List<MapToXml> attributes = root.getAttributes();
		if (attributes != null && !attributes.isEmpty()) {
			for (MapToXml map : attributes) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					builder.append(" " + entry.getKey() + "=" + entry.getValue());
				}
			}

		}
		builder.append(">");
		List<MapToXml> sonNodes = root.getSonNods();
		if (sonNodes != null && !sonNodes.isEmpty()) {// 有子节点则递归调用生成子节点XML，忽略本身节点的value值
			builder.append("\r");
			for (MapToXml map : sonNodes) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					builder.append(ToXmlFromRoot(entry.getKey(), map));
				}
			}
			int y = root.rank;
			while (y > 0) {// 根据几级节点生成空格，和前缀空格对应
				builder.append(" ");
				y--;
			}
			builder.append("</" + rootname + ">");
			builder.append("\r");
		} else {// 灭有子节点则直接输出后缀
			builder.append(root.get(rootname));
			builder.append("</" + rootname + ">");
			builder.append("\r");
		}
		return builder.toString();
	}

}
