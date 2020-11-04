package SubWay2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import SubWay2.Station;

public class ReadFile {
	//���ϱ���������·������·��վ����Ϣ
	public static Set<List<Station>> lineSet = new HashSet<List<Station>>();
	//����վ����
    public static int totalStation = 0;
     //�ļ���ȡ����  
	public static void readText(){
		Station Prevstation = new Station();
        try {
            File file=new File("������·��Ϣ.txt");
            //�ж��ļ��Ƿ����
            if(file.isFile() && file.exists()){ 
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(reader);
                String stationline = null;
              
                int flag = 0;
                while((stationline = bufferedReader.readLine()) != null){
                     String[] tmp = stationline.split(" ");//�ָ���ţ����ո��ÿ�
                     List<Station> Linestations = new ArrayList<Station>();//����һ����������·��Ϣ
                	for(int j=1;j<tmp.length;j++) {
                		Station station = new Station();
                		station.setLinename(tmp[0]);//��ÿһ�еĵ�һ���ַ���������·��
                		station.setName(tmp[j]);//����վ������
                		Linestations.add(station);//������·��Ϣ
                		totalStation++;//������վ����
                		if(flag != 0) {//��flag��Ϊ��ʱ������ǰ��վ��
                			station.prevsation = Prevstation;
                			Prevstation.nextsation = station;
                		}
                		Prevstation = station;//��¼���㿪ʼ��¼��վ��
                		flag++;
                	}
                	lineSet.add(Linestations);//���Ϊ��·��
                }
               // �ر��ļ�
                reader.close();
                bufferedReader.close();
            }
            else
                System.out.println("�Ҳ���ָ�����ļ�!");
          } catch (Exception e) {
              System.out.println("�ļ����ݳ���!");
              e.printStackTrace();
           }        
        }
	 
}





