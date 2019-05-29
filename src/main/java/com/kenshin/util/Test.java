package com.kenshin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {

		
		MapToXml root=new MapToXml("root","");
		root.addAttribute("attr","attr1").addAttribute("attr2","attr2").addNode("node1", "node1_value")
		.addNode("node_two", "node_vlue2");
		MapToXml node3=new MapToXml("node3","node3_value");
		root.addNode(node3);
		node3.addNode(new MapToXml("node4","node4_value").addNode("node5","node5_value"));
		System.out.println("Before remove:");
		System.out.println(root.ToXml("root"));
		System.out.println(root.getTotalcounts());
		System.out.println("After remove:");
		root.remove("node_two").remove("node1");
		System.out.println(root.ToXml("root"));
		System.out.println(root.ToXml("node3"));
		System.out.println(root.getTotalcounts());
		List<Object> os=root.findNodes("node1");
		System.out.println(os==null||os.isEmpty());
		
	}

}
