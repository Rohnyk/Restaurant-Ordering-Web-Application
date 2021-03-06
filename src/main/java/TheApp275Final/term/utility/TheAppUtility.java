package TheApp275Final.term.utility;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TheApp275Final.term.dto.OrderTimes;
import TheApp275Final.term.model.Order;
import TheApp275Final.term.model.Pipeline;
import TheApp275Final.term.model.Pipeline1;
import TheApp275Final.term.model.Pipeline2;
import TheApp275Final.term.model.Pipeline3;

public class TheAppUtility {

	public static LocalTime convertStringToLocalTime(String time){
		LocalTime localTime = LocalTime.parse(time);
		return localTime;
	}
	
	public static Pipeline getPipeline(int number){
		Pipeline pipeline=null;
		
		switch (number) {
		case 1:
			pipeline = new Pipeline1();
			break;

		case 2:
			pipeline = new Pipeline2();
			break;

		case 3:
			pipeline = new Pipeline3();
			break;

		default:
			break;
		}	
		
		return pipeline;
	}
	
	public static HashMap<Integer,ArrayList<OrderTimes>> convertListToMap(List<Order> orderList){
		HashMap<Integer,ArrayList<OrderTimes>> orderMap = new HashMap<Integer,ArrayList<OrderTimes>>();
		// iterate through your objects
		// Take this value from property files ie no of pipelines
		for(int i=1;i<=3;i++)
		orderMap.put(i, new ArrayList<OrderTimes>());
		for(Order order : orderList){
			if(order.getPipeline().getPipelineNo()==0)
				continue;

		    // fetch the list for this object's id
		    //List<Order> temp = orderMap.get(order.getPipeline().getPipelineNo());
			OrderTimes orderTimes = new OrderTimes(order.getOrderStartTime().toLocalTime(),order.getOrderEndTime().toLocalTime());
			ArrayList<OrderTimes> tmp;
			System.out.println("order.getPipeline().getPipelineNo()"+order.getPipeline().getPipelineNo());
		    tmp = orderMap.get(order.getPipeline().getPipelineNo());
			tmp.add(orderTimes);
			
			
		}
		for(int key : orderMap.keySet()){
			System.out.println("after map is converted");
			System.out.println(orderMap.get(key).toString());
		}
		return orderMap;
	}
}
