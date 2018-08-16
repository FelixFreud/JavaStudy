package section07;

import java.lang.reflect.*;

public class Launcher {
	public static void main(String[] args){
		//1
		String fqcn = args[0];
		String id = args[1];
		//2
		System.out.println((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024+"MB");
		//6
		try {
			//3
			Class<?> cls = Class.forName(fqcn);
			Method[] mtd = cls.getDeclaredMethods();
			for(Method m:mtd) System.out.println(m.getName());
			//4
			if(id == "E") {
				ProcessBuilder pb = new ProcessBuilder("java", cls.getName());
				Process proc = pb.start();
				proc.waitFor();
			}else if(id == "I") {
				Method m = cls.getMethod("main", String[].class);
				String[] str = {};
				m.invoke(null, (Object)str);
			}else {
				System.out.println("第２引数はEまたはIを指定してください");
			}
			//5
			System.out.println(((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024)+"MB");
			System.exit(0);
		//6
		}catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}
