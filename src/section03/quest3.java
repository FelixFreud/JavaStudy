package section03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quest3 {

	public static void main(String[] args) {
		System.out.println("--Q2------------");
		quest3_2();
		System.out.println("--Q3------------");
		quest3_3();
	}

	/**Q3-1
	 * 1.Set
	 * 2.List
	 * 3.Map  
	 */
	/**Q3-2*/
	static void quest3_2() {
		QuestHero h1 = new QuestHero("斎藤");
		QuestHero h2 = new QuestHero("鈴木");
		List<QuestHero> heros = new ArrayList<>();
		heros.add(h1);
		heros.add(h2);
		for(QuestHero h:heros) System.out.println(h.getName());
	}
	/**Q3_3*/
	static void quest3_3() {
		QuestHero h1 = new QuestHero("斎藤");
		QuestHero h2 = new QuestHero("鈴木");
		Map<QuestHero,Integer> heros = new HashMap<>();
		heros.put(h1, 3);
		heros.put(h2, 7);
		for(QuestHero h: heros.keySet()) System.out.println(h.getName()+"が倒した数＝"+heros.get(h));
	}
}
class QuestHero{
	private String name;
	public QuestHero(String name) {this.name = name;}
	public String getName() {return this.name;}
}
