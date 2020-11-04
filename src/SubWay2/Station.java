package SubWay2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Station {
//����վ����
private String name; 
//������·��
private String Linename; 

//�ڵ�ǰ��·ʱ������վ���ǰһ��վ
public Station prevsation; 

//�ڵ�ǰ��·ʱ������վ��ĺ�һ��վ
public Station nextsation; 

//����վ�㵽ĳһ��Ŀ��վ��(key)������������վ����
private Map<Station,LinkedHashSet<Station>> ordermap = new HashMap<Station,LinkedHashSet<Station>>();

		public String getLinename() {
			return Linename;
		}

		public void setLinename(String linename) {
			Linename = linename;
		}

	public Station (String name){
		this.name = name;
	}
 

	public Station() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	//��¼��ǰ����վ�㵽Ŀ��վ��������������վ�㣬������
	public LinkedHashSet<Station> getAllPassedStations(Station station) {
		if(ordermap.get(station) == null){     
			LinkedHashSet<Station> set = new LinkedHashSet<Station>(); 
			set.add(this);//thisΪ��ǰ���ʵ��
			ordermap.put(station, set);
		}
		return ordermap.get(station);
	}
 
	public Map<Station, LinkedHashSet<Station>> getOrderMap() {
		return ordermap;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		} else if(obj instanceof Station){
			Station s = (Station) obj;
			if(s.getName().equals(this.getName())){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

}
