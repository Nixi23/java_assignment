import java.util.*;
class A62
{
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		System.out.println("Before Swapping : ");
		System.out.println(list);
		Collections.swap(list,3,4);
		System.out.println("After Swapping :");
		System.out.println(list);
	}
}