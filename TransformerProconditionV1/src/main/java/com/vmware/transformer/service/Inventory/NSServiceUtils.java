package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.NSServiceALG.ALG;
import com.vmware.transformer.model.Inventory.NSServiceEtherType.EtherType;
import com.vmware.transformer.model.Inventory.NSServiceICMPType.ICMPType;
import com.vmware.transformer.model.Inventory.NSServiceIGMPType.IGMPType;
import com.vmware.transformer.model.Inventory.NSServiceIPProtocolService.IPProtocolNSService;
import com.vmware.transformer.model.Inventory.NSServiceL4PortSet.L4PortSet;


public class NSServiceUtils {

	
	public static ArrayList<EtherType> getEtherTypeList(){
//		String [] ether_type_values = {"33011","32923","2054", "34978","8944","34841","35074","24579","34958","34980","36864","34824","34987","35078","35092","34939","35041","35119",
//				"34970","2048","34525","33079","33080","35020","35045","35043","34888","34887","34915","34916","34962","34984","35063","33284","32821","35093","35021","34825","8947","51966","33024","2114"};
		String [] ether_type_values = {"33011","32923","2054"};
		ArrayList<EtherType> list = new ArrayList<EtherType>();
		
		for(int i = 0; i< ether_type_values.length; i++){
			EtherType net = new EtherType(ether_type_values[i], "EtherTypeNSService");
			list.add(net);
		}	
		return list;
	}
	
	public static ArrayList<IPProtocolNSService> getIPProtocolNSServiceList(){
//		String[] protocol_number_list = {"34","107","51","114","68","61"};
		String[] protocol_number_list = {"34","107","51"};
		ArrayList<IPProtocolNSService> list = new ArrayList<IPProtocolNSService>();
		for(int i=0; i< protocol_number_list.length; i++){
			IPProtocolNSService ins = new IPProtocolNSService("IPProtocolNSService",protocol_number_list[i]);
			list.add(ins);
		}
		return list;
	}
	
//	public static ArrayList<IGMPType> getIGMPTypeList(){
//		String [] sub_protocol = {"19","21","17"};
//		ArrayList<IGMPType> list = new ArrayList<IGMPType>();
//		
//		for(int i=0; i< sub_protocol.length; i++){
//			IGMPType it = new IGMPType("IGMPTypeNSService",sub_protocol[i]);
//			list.add(it);
//		}
//		return list;
//	}
	
	public static ArrayList<IGMPType> getIGMPTypeList(){
		ArrayList<IGMPType> list = new ArrayList<IGMPType>();
	
		IGMPType it = new IGMPType("IGMPTypeNSService");
		list.add(it);

		return list;
	}
	
	public static ArrayList<ICMPType> getICMPTypeList(){
		String [] icmp_type_ipv4 = {"18"};
		String [] icmp_code_ipv4 = {"0"};
		String [] icmp_type_ipv6 = {"140"};
		String [] icmp_code_ipv6 = {"0"};
		
		ArrayList<ICMPType> list = new ArrayList<ICMPType>();
		
		for(int i=0; i< icmp_type_ipv4.length; i++){
			ICMPType icmp = new ICMPType("ICMPTypeNSService", icmp_type_ipv4[i], icmp_code_ipv4[i], "ICMPv4");
			list.add(icmp);
		}
		for(int i=0; i< icmp_type_ipv6.length; i++){
			ICMPType icmp = new ICMPType("ICMPTypeNSService", icmp_type_ipv6[i], icmp_code_ipv6[i], "ICMPv6");
			list.add(icmp);
		}
		
		return list;
	}
	
	@SuppressWarnings("serial")
	public static ArrayList<ALG> getALGList(){
		
		ALG alg01 = new ALG("ALGTypeNSService", new ArrayList<String>(){{add("21");}}, new ArrayList<String>(){{add("10000");}}, "FTP");
		ALG alg02 = new ALG("ALGTypeNSService", new ArrayList<String>(){{add("345");}}, new ArrayList<String>(){{add("10000");}}, "MS_RPC_TCP");
		ALG alg03 = new ALG("ALGTypeNSService", new ArrayList<String>(){{add("456");}}, new ArrayList<String>(){{add("10000");}}, "MS_RPC_UDP");
		
		ArrayList<ALG> list = new ArrayList<ALG>();
		list.add(alg01);
		list.add(alg02);
		list.add(alg03);
		return list;
	}
	
	@SuppressWarnings("serial")
	public static ArrayList<L4PortSet> getL4PortSetList(){
		L4PortSet l01 = new L4PortSet("L4PortSetNSService", new ArrayList<String>(){{add("21");add("123");}}, new ArrayList<String>(){{add("1");add("12");}}, "UDP");
		L4PortSet l02 = new L4PortSet("L4PortSetNSService", new ArrayList<String>(){{add("1");add("456");}}, new ArrayList<String>(){{add("2");add("564");}}, "TCP");
		
		ArrayList<L4PortSet> list = new ArrayList<L4PortSet>();
		list.add(l01);
		list.add(l02);
		
		return list;
	}
	
	
}
