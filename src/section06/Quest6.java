package section06;

public class Quest6 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*Q6-1*/
		Func1 f1 = FuncList::isOdd;
		FuncList fl = new FuncList();
		Func2 f2 = fl::addNamePrefix;
		System.out.println(f2.func2(f1.func1(3), "Felix"));
		/*Q6-2*/
		Func1 f3 = x -> x % 2 == 1;
		Func2 f4 = (x,y) -> {
			if(x == true) {return "Mr. "+y;}
			else return "Ms. "+y;
		};
	}


	interface Func1{
		boolean func1(int x);
	}
	interface Func2{
		String func2(boolean male, String name);
	}
}
class FuncList{
	public static boolean isOdd(int x) { return (x % 2 == 1); }
	public String addNamePrefix(boolean male, String name) {
		if(male == true) { return "Mr."+name; }
		else { return "Ms."+name; }
	}
}
