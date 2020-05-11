import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Player {
	private int ID;
	private String name;
	private String job;
	private int level;
	private int possetion;
	private String weapon;
	private String armor;
	private String[] items;
	
	public Player(String[] params){
		this.ID = Integer.parseInt(params[0]);
		this.name = params[1];
		this.job = params[2];
		this.level = Integer.parseInt(params[3]);
		this.possetion = Integer.parseInt(params[4]);
		this.weapon = params[5];
		this.armor = params[6];
		this.items = Arrays.copyOfRange(params, 7, 11);
	}
	
	
	public Player(){}
	
	
	public String toString(){
		return String.valueOf(this.ID) + "," +
				this.name + "," +
				this.job + "," +
				String.valueOf(this.level) + "," +
				String.valueOf(this.possetion) + "," +
				this.weapon + "," +
				this.armor + "," +
				this.items[0] + "," +
				this.items[1] + "," +
				this.items[2] + "," +
				this.items[3];
	}
	
	
	public void setID(int ID){
		this.ID = ID;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setJob(String job){
		this.job = job;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setPossetion(int possetion){
		this.possetion = possetion;
	}
	public void setWeapon(String weapon){
		this.weapon = weapon;
	}
	public void setArmor(String armor){
		this.armor = armor;
	}
	public void setItems(String[] items){
		this.items = items;
	}
	
	
	public int getID(){
		return this.ID;
	}	
	public String getName(){
		return this.name;
	}	
	public String getJob(){
		return this.job;
	}	
	public int getLevel(){
		return this.level;
	}	
	public int getPossetion(){
		return this.possetion;
	}	
	public String getArmor(){
		return this.armor;
	}	
	public String[] getItems(){
		return this.items;
	}
}
