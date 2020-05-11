import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileOperationTest{
	
	private static final Path DIR = Paths.get("./data/");	//Eclipseでの相対パス
	private static final Path PLAYER = DIR.resolve("game_player.csv");	//DIRと連結 (In Eclipse, DAT's path is "${workspace}/${this project}/data/game_player.csv")
	private static final Path OUTPUT = DIR.resolve("output.csv");	//DIRと連結 (In Eclipse, DAT's path is "${workspace}/${this project}/data/output.csv")

	
	public static void main(String[] args){
		List<Player> players;
		if(Files.exists(PLAYER)){
			players = load(PLAYER);
		}else{
			System.out.println(PLAYER + "が見つかりません");
			return;
		}

		List<Player> players_new = update(players);
		save(OUTPUT, players_new);
	}
	
	
	static List<Player> update(List<Player> players){
		for(Player p : players){
			String[] items = p.getItems();
			
			if(p.getLevel() == 1){
				if(Arrays.asList(items).contains(null)){
					items[Arrays.asList(p.getItems()).indexOf(null)] = "応援旗";
					p.setItems(items);
				}else{
					p.setPossetion(p.getPossetion() + 100);
				}
			}
			
			if(p.getLevel() <= 10){
				p.setPossetion(p.getPossetion() + 100);
			}
			
			if(Arrays.asList(items).contains("鋼玉")){
				items[Arrays.asList(items).indexOf("鋼玉")] = "スペシャルソード";
			}
		}
		return players;
	}
	
	
	static List<Player> load(Path FILE){
		List<Player> players = new ArrayList<Player>();
		String str;
		
		try(BufferedReader br = Files.newBufferedReader(FILE)){	//CSVの読み込み
			
			while((str = br.readLine()) != null){	//EOFまでstrに代入
				String[] params = str.split(",");
				players.add(new Player(params));	//プレイヤー登録
			}
			return players;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	static void save(Path FILE, List<Player> players){
		if(checkFile(FILE)){
			try(BufferedWriter bw = Files.newBufferedWriter(FILE)){
				for(Player p : players){
					bw.write(p.toString() + "\n");
				}
				System.out.println("作業を終了します");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	static boolean checkFile(Path FILE){
		if(Files.exists(FILE)){
			String str;
			try(Scanner scan = new Scanner(System.in)){
				while(true){
					System.out.print("ファイルを上書きしますか？ [y/n] > ");
					if((str = scan.next()).equals("y")){
						return true;
					}else if(str.equals("n")){
						System.out.println("作業を中断します");
						break;
					}
				}
				return false;
			}
		}else{
			return true;
		}
	}
}
