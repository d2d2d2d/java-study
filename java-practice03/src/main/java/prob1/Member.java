package prob1;

public class Member {
	private String id;
	private static String name;
	private int point;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Member.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}
