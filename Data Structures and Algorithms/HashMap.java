import java.util.ArrayList;
import java.util.List;

public class HashMap {

	public static void main(String[] args) {
		HashMap map = new HashMap();
		
		Student s = new Student();
		s.idNumber = 13;
		s.name = "Unlucky Person";
		
		Student s2 = new Student();
		s2.idNumber = 14;
		s2.name = "Rachel Vardi";
		
		map.add(s);
		map.add(s2);
		
		System.out.println(map.get(13));
		System.out.println(map.get(14));
	}
	
	public List<Student>[] grid;
	
	public HashMap() {
		grid = new ArrayList[101];
		
		for(int i = 0; i < grid.length; i++) {
			grid[i] = new ArrayList<>();
		}
	}
	
	public void add(Student s) {
		int hashCode = s.idNumber;
		int index = hashCode % grid.length;
		
		grid[index].add(s);
	}
	
	public String get(int idNumber) {
		int index = idNumber % grid.length;
		
		for(Student s: grid[index]) {
			if(s.idNumber == idNumber) {
				return s.name;
			}
		}
		
		return null;
	}
	
	public static class Student {
		public int idNumber;
		public String name;
	}
	
}
