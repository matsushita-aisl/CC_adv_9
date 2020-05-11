import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileOperationTest{
	
	private static final Path DIR = Paths.get("./data/");	//Eclipseでの相対パス
	private static final Path PLAYER = DIR.resolve("game_player.csv");	//DIRと連結 (In Eclipse, DAT's path is "${workspace}/${this project}/data/game_player.csv")
	private static final Path OUTPUT = DIR.resolve("output.csv");	//DIRと連結 (In Eclipse, DAT's path is "${workspace}/${this project}/data/output.csv")
	
	static String str;
	////
	static String[] sample = {"1", "n", "j", "2", "3", "w", "a", "i", "", "", ""};
	////
	
	public static void main(String[] args){
		List<Player> players;
		if(Files.exists(PLAYER)){
			players = load(PLAYER);
			System.out.println("Found!");
		}else{
			System.out.println(PLAYER + "が見つかりません");
			return;
		}
		
		//players.stream().forEach(x -> System.out.println(x + "," + Arrays.asList(x.getItems()).indexOf(null)));

		for(Player p : players){
			if(p.getLevel() == 1){
				String[] items = p.getItems();
				if(Arrays.asList(items).contains(null)){
					items[Arrays.asList(p.getItems()).indexOf(null)] = "応援旗";
					p.setItems(items);
				}else{
					p.setPossetion(p.getPossetion() + 100);
				}
			}
		}
	}
	
	static List<Player> load(Path FILE){
		List<Player> players = new ArrayList<Player>();
		
		try(BufferedReader bw = Files.newBufferedReader(FILE)){	//CSVの読み込み
			
			while((str = bw.readLine()) != null){	//EOFまでstrに代入
				String[] params = str.split(",");
				players.add(new Player(params));	//プレイヤー登録
			}
			return players;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
