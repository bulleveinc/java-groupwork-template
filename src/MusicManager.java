import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MusicManager {

	private MusicLibrary musicLibrary;

	public MusicManager() {
		musicLibrary = new MusicLibrary();
	}

	public void displayMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";

		while (!choice.equals("7")) {
			System.out.println("----- 音楽マネージャ -----");
			System.out.println("1) 曲を登録する");
			System.out.println("2) 曲を削除する");
			System.out.println("3) 全ての曲をリストする");
			System.out.println("4) アーティスト名で検索する");
			System.out.println("5) ジャンルで検索する");
			System.out.println("6) 曲名で検索する");
			System.out.println("7) 終了する");
			System.out.print("> ");
			choice = reader.readLine().trim();

			switch (choice) {
			case "1":
				registerSong();
				break;
			case "2":
				deleteSong();
				break;
			case "3":
				listAllSongs();
				break;
			case "4":
				searchArtist();
				break;
			case "5":
				searchGenres();
				break;
			case "6":
				searchMusics();
				break;
			case "7":
				musicLibrary.save();
				System.out.println("プログラムを終了します。");
				break;
			default:
				System.out.println("無効な選択肢です、もう一度お試しください。");
			}
		}
	}


	// JANコードの入力を求める
	// 追加分　13桁の整数かのチェック
	public boolean stCheck(String janCode) throws IOException{
		// JANコードの入力を求める
		//  System.out.print("JANコードを入力してください: ");
		//  String janCode = reader.readLine().trim();
		if(janCode.matches("\\d{13}")){
			return false;
		}else  {
			System.out.println("13桁で入力してください");
			return true;
		}
	}

	private void registerSong() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String janCode ="";
		boolean j = true;
		
		while(j) {
			System.out.print("JANコードを入力してください: ");
			janCode = reader.readLine().trim();
			j = stCheck(janCode);
			if (j == false) {  
				int index = musicLibrary.getIndexByJAN(janCode);
				if (index != -1) {
					System.out.println("入力されたJANコードは既に登録済みのため入力できません。");
					j = true;
				}
			}
		}




		// 曲名の入力を求める
		System.out.print("曲名を入力してください: ");
		String title = reader.readLine().trim();


		// アーティスト名の入力を求める
		System.out.print("アーティスト名を入力してください: ");
		String artist = reader.readLine().trim();


		// リリース年の入力を求める
		boolean releaseYearCheck = true;
		int releaseYear = 0;
		while(releaseYearCheck) {
			System.out.print("リリース年を入力してください: ");
			try {
				releaseYear = Integer.parseInt(reader.readLine().trim());
				releaseYearCheck = false;
			}catch (NumberFormatException e) {
				System.out.println("数値に変換できません: " + e.getMessage());
			}
		}
		
		// ジャンルの入力を求める
		System.out.print("ジャンルを入力してください: ");
		String genre = reader.readLine().trim();
		
		// 在庫数の入力を求める
		boolean stockCheck = true;
		int stock = 0;
		while(stockCheck) {
			System.out.print("在庫数を入力してください: ");
			try {
				stock = Integer.parseInt(reader.readLine().trim());
				stockCheck = false;
			}catch (NumberFormatException e) {
				System.out.println("数値に変換できません: " + e.getMessage());
			}
		}


		// MusicLibraryのaddSongメソッドを呼び出して曲を登録する
		musicLibrary.addSong(janCode, title, artist, releaseYear,genre,stock);

		System.out.println("曲の登録に成功しました！");
	}


	private void deleteSong() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


		// JANコードの入力を求める
		System.out.print("削除したい曲のJANコードを入力してください: ");
		String janCode = reader.readLine().trim();


		// JANコードに基づいて曲のインデックスを取得する
		int index = musicLibrary.getIndexByJAN(janCode);
		if (index == -1) {
			System.out.println("指定されたJANコードの曲は見つかりませんでした。");
			return;
		}

		System.out.println("本当に削除しますか？");
		System.out.print("削除する時は1を入力してください。削除しない時はその他の数字を入力してください。");


		String deleteCheck = reader.readLine().trim();
		if(deleteCheck.equals ("1")) {
			// 曲を削除する
			musicLibrary.deleteSong(index);

			System.out.println("曲の削除に成功しました！");
		}
	}

	private void listAllSongs() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Song> songs = musicLibrary.getAllSongs();

		final int SONGS_PER_PAGE = 20;
		int totalPages = (int) Math.ceil((double) songs.size() / SONGS_PER_PAGE);
		int currentPage = 1;

		while (true) {
			int startIdx = (currentPage - 1) * SONGS_PER_PAGE;
			int endIdx = Math.min(startIdx + SONGS_PER_PAGE, songs.size());

			System.out.println("ページ " + currentPage + " / " + totalPages);
			for (int i = startIdx; i < endIdx; i++) {
				Song song = songs.get(i);
				System.out
				.println((i + 1) + ". " + song.getJanCode() + "\t" + song.getTitle() + "\t" + song.getArtist()
				+ "\t" + song.getReleaseYear() + "年");
			}


			System.out.println("1) 前のページ\t2) 次のページ\t3) メニューに戻る");
			System.out.print("オプションを選択してください: ");
			String choice = reader.readLine().trim();

			switch (choice) {
			case "1":
				if (currentPage > 1) {
					currentPage--;
				} else {
					System.out.println("最初のページにいます。");
				}
				break;
			case "2":
				if (currentPage < totalPages) {
					currentPage++;
				} else {
					System.out.println("最後のページにいます。");
				}
				break;
			case "3":
				return; // メインメニューに戻る
			default:
				System.out.println("無効な選択肢です、もう一度お試しください。");
			}
		}
	}

	private void searchArtist() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Song> songs = musicLibrary.getAllSongs();

		// アーティスト名の入力を求める
		System.out.print("アーティスト名を入力してください: ");
		String artist = reader.readLine().trim();

		ArrayList<Integer> index = musicLibrary.getSongsByArtist(artist);

		if(index.isEmpty()){
			System.out.println("入力したアーティスト名は登録されていません。");
		}else{
			for (int i = 0; i < index.size(); i++) {
				Song song = songs.get(index.get(i));
				System.out.println((index.get(i)+ 1) + ". " + song.getJanCode() + "\t" + song.getTitle() + "\t" + song.getArtist()
				+ "\t" + song.getReleaseYear() + "年");
			}
		}
	}
	
	private void searchGenres() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Song> songs = musicLibrary.getAllSongs();

		// ジャンルの入力を求める
		System.out.print("ジャンルを入力してください: ");
		String genre = reader.readLine().trim();

		ArrayList<Integer> index = musicLibrary.getSongsByGenre(genre);

		if(index.isEmpty()){
			System.out.println("入力したジャンルは登録されていません。");
		}else{
			for (int i = 0; i < index.size(); i++) {
				Song song = songs.get(index.get(i));
				System.out.println((index.get(i)+ 1) + ". " + song.getJanCode() + "\t" + song.getTitle() + "\t" + song.getArtist()
				+ "\t" + song.getReleaseYear() + "年" + "\t" + "ジャンル:" +song.getGenre());
			}
		}
	}
	
	private void searchMusics() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Song> songs = musicLibrary.getAllSongs();

		// 曲名の入力を求める
		System.out.print("曲名を入力してください: ");
		String title = reader.readLine().trim();

		ArrayList<Integer> index = musicLibrary.getSongsByTitle(title);

		if(index.isEmpty()){
			System.out.println("入力した曲名は登録されていません。");
		}else{
			for (int i = 0; i < index.size(); i++) {
				Song song = songs.get(index.get(i));
				System.out.println((index.get(i)+ 1) + ". " + song.getJanCode() + "\t" + song.getTitle() + "\t" + song.getArtist()
				+ "\t" + song.getReleaseYear() + "年");
			}
		}
	}


	public static void main(String[] args) throws IOException {
		MusicManager musicManager = new MusicManager();
		musicManager.displayMenu();
	}
}
