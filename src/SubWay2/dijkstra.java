package SubWay2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import SubWay2.ReadFile;
import SubWay2.Station;



public class dijkstra {

private List<Station> List1 = new ArrayList<Station>();//�����Ѿ���������վ��

//�õ��봫������������ڵ�վ��
	private List<Station> getAllNearStations(Station station){
		List<Station> linkedStaions = new ArrayList<Station>();
		for(List<Station> line : ReadFile.lineSet){//����������·
			if(line.contains(station)){//��ǰ��·�а�������վ��
				Station s = line.get(line.indexOf(station));//ȡ����ǰ��·�ϵĸ�վ���ֵ
				if(s.prevsation != null){
					linkedStaions.add(s.prevsation);//�õ���ǰ��·�ĸ�վ���ǰһ��վ��
				}
				if(s.nextsation != null){
					linkedStaions.add(s.nextsation);//�õ���ǰ��·�ĸ�վ��ĺ�һ��վ��
				}
			}
		}
		return linkedStaions;
	}
	
//�õ�����������վ����̾���ÿһվ����Ϊ1
		private Station getShortPath(Station station){
			int min = 10000;//����һ���ϴ���������Ƚ�
			Station rets = null;
			for(Station s :station.getOrderMap().keySet()){//������ǰ����վ�㵽����վ��
				if(List1.contains(s)){
					continue;
				}
				LinkedHashSet<Station> set  = station.getAllPassedStations(s);//��ǰ����վ�㵽δ����վ���·��
				if(set.size() < min){
					min = set.size();//ȡ·����̵���һ��
					rets = s;
				}
			}
			return rets;//�����������һ��վ��
		}
	
	//������̾���·��
	public void result(Station s1,Station s2){
		int flag = 0;
		String tempname = "";
		String linename = "";
		int flag1=0;
		//��������վ�㣬�ж������վ�������Ƿ����
		for(List<Station> line : ReadFile.lineSet){
			if(line.contains(s1)){
				flag1=1;
			}
		}
		if(flag1==0) {
			System.out.println("����վ�㲻���ڣ����������룡");
			return ;
		}
		
		int flag2=0;
		
		for(List<Station> line : ReadFile.lineSet){
			if(line.contains(s2)){
				flag2=1;
			}
		}
		if(flag2==0) {
			System.out.println("����վ�㲻���ڣ����������룡");
			return ;
		}
		//������վ�㶼������
		if(List1.size() == ReadFile.totalStation){
			System.out.println("��㣺"+s1.getName()+"  �յ�վ��"+s2.getName()+"��������"+(s1.getAllPassedStations(s2).size()-1)+"վ");
			int p=0;
			for(Station station : s1.getAllPassedStations(s2)){//�������s1��s2������·��
				p++;
				if(flag == 0) {
					tempname = station.getName();
				}
				else if(flag == 1) {
					linename=station.getLinename();
					System.out.print(station.getLinename()+tempname+"->");
				}
				else {
					if(!station.getLinename().equals(linename)) {//������·�������
						linename=station.getLinename();
						System.out.println();
					}
					if(p<s1.getAllPassedStations(s2).size()-1) {
						System.out.print(station.getLinename()+" "+station.getName()+"->");
					}
					else {
						System.out.print(station.getLinename()+" "+station.getName());
					}
					
				}
				flag++;
			}
			return;
		}
		if(!List1.contains(s1)){//����ǰ����վ�㴢�����ѱ�������
			List1.add(s1);
		}
		//������Ĳ���վ���OrderMapΪ�գ����һ���ø�վ������վ���ʼ��
		if(s1.getOrderMap().isEmpty()){
			List<Station> Linkedstations = getAllNearStations(s1);
			for(Station s : Linkedstations){
				s1.getAllPassedStations(s).add(s);
			}
		}
		Station psation = getShortPath(s1);//��ȡ�������վs1�����һ��վ
		if(psation == s2){
			System.out.println("�ҵ�Ŀ��վ�㣺"+s2+"��������"+(s1.getAllPassedStations(s2).size()-1)+"վ");
			for(Station station : s1.getAllPassedStations(s2)){
				System.out.print(station.getName()+"->");
			}
			return;
		}
		for(Station station1 : getAllNearStations(psation)){
			if(List1.contains(station1)){
				continue;
			}
			int shortestPath = (s1.getAllPassedStations(psation).size()-1) + 1;//�����������
			if(s1.getAllPassedStations(station1).contains(station1)){
				//���s1�Ѿ����������station1�ľ������룬��ô�Ƚϳ���С�ľ���
				if((s1.getAllPassedStations(station1).size()-1) > shortestPath){
					//����S1����Χ��վ����С·��
					s1.getAllPassedStations(station1).clear();
					s1.getAllPassedStations(station1).addAll(s1.getAllPassedStations(psation));
					s1.getAllPassedStations(station1).add(station1);
				}
			} else {
				//���s1��û�м��������station1�ľ�������
				s1.getAllPassedStations(station1).addAll(s1.getAllPassedStations(psation));
				s1.getAllPassedStations(station1).add(station1);
			}
		}
		List1.add(psation);
		result(s1,s2);//�ظ����㣬������վ����չ
	}

 
	public static void main(String[] args) {
		dijkstra subway = new dijkstra();
		ReadFile.readText();
		Scanner input =new Scanner(System.in);
		System.out.print("��������㣺");
		String str1 = input.next();
		System.out.print("�������յ㣺");
		String str2 = input.next();
		
		subway.result(new Station(str1), new Station(str2));	
	}
}
