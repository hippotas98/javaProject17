package abc;
import cde.Cau1;
import java.util.Collections.*;
import java.util.*;
public class source
{	
    int a = 0;
	//public void static abc ()
//{
//	a = 1;
//}
	public source(int a) {
		this.a = a;
	}
    public static void main (String [] args)
    {
        Teacher t1 = new Teacher();
        t1.setAge(10);
        t1.setGender("male");
        t1.setName("Hello");
        t1.setRank("Phd");
        t1.setSchool("ABC");
        System.out.println(t1.getName() + "\n" + t1.getGender()+
        "\n" + t1.getRank() + "\n" + t1.getSchool());
        
    }
}
interface hello
{
void draw();
}
interface h2
{
void print();
}
interface h3 extends h2{
	void print();
	void draw();
}
class Person 
{
    int age;
    public String name;
    private String gender;
    public int getAge() {
        return this.age;
    }
    public void setAge (int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender (String gender) {
        this.gender = gender;
    }
}
class Teacher extends Person implements hello
{
    protected String school;  
    String rank;
    Animal p;
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getRank() {
        return this.rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
	public void draw()
	{
		school = "1";
	}
}
class Cau1 {
    int a, b;
    Teacher t;
    void drawcau2(){

    }
}
class Developer extends cde.Cau1
{
    List<String> languages = new ArrayList<String>();
    cde.Cau1 d;
    public List<String> getLanguage() {
        return this.languages;
    }
    public void setLanguage(List<String> languages) {
        this.languages = languages;
    }
}
class Student extends Person{
    String school;
    String Class;
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String GetClass() {
        return this.Class;
    }
    public void setClass(String Class) {
        this.Class = Class;
    }
}
class Animal extends Cau1 implements h2,hello
{
    String gender;
    int leg;
    public int getLeg() {
        return this.leg;
    }
    public void setLeg(int leg) {
        this.leg = leg;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void print() {
    	
    }
    public void draw() {
    	
    }
}
class Cat extends Animal{
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 4;
    }
}
class Chicken extends Animal{
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 2;
    }
}
class Fish extends Animal
{
    String habitat;
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 0;
    }
    public String getHabitat() {
        return this.habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
class Bird extends Animal{
    String habitat;
    boolean layeggs;
    public String getHabitat() {
        return this.habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    public boolean getLayeggs() {
        return this.layeggs;
    }
    public void setLayeggs(boolean layeggs) {
        this.layeggs = layeggs;
    }
    class BabyBird implements hello{
    		public void draw() {
    			habitat = "0";
    		}
    		class H {
    			int b;
    		}
    }
    BabyBird n = new BabyBird();
}
abstract class h1 extends Bird {
	int a,b;
	public void hello() {
		System.out.println(1);
	}
	public abstract void hi();
}