package boot;

import java.util.LinkedList;
import java.util.List;

public class RunTestMain {

	public static void main(String[] args) {
		List<Integer> _linkList = new LinkedList<>(); 
		_linkList.add(0);
		
		_linkList = _linkList.subList(0,2);
		System.out.println(_linkList.size());
		_linkList.forEach(System.out::println);
	}

}
