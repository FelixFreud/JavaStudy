package section10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.Properties;

public class Quest10 {

	public static void main(String[] args) {
		System.out.println("--1---------------");
		quest10_1();
		System.out.println("--2---------------");
		quest10_2();
		quest10_2_5();
	}

	/*Q10-1*/
	static void quest10_1() {
		try(Reader fr = new FileReader("pref.properties");){
			Properties p = new Properties();
			p.load(fr);
			String aichiCapital = p.getProperty("aichi.capital");
			String aichiFood = p.getProperty("aichi.food");
			System.out.println(aichiCapital+":"+aichiFood);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*Q10-2*/
	static void quest10_2() {
		Employee tanaka = new Employee("田中太郎",41);
		Department soumu = new Department("総務部",tanaka);
		try(	
				FileOutputStream fos = new FileOutputStream("company.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				){
			oos.writeObject(soumu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*Q10-2確認用*/
	static void quest10_2_5() {
		Department soumu = null;
		try(
				FileInputStream fis = new FileInputStream("company.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				){
			soumu = (Department) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(soumu);
	}
}
class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public Employee(String name,int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return this.name+"（"+this.age+"歳）";
	}
}
class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	public String name;
	public Employee leader;
	public Department(String name,Employee leader) {
		this.name = name;
		this.leader = leader;
	}
	@Override
	public String toString() {
		return this.name+":"+leader.toString();
	}
}