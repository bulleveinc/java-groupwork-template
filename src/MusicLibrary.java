
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 楽曲データを管理するクラス
 * @creator Ozawa
 * @version 1.0
 */
public class MusicLibrary {

	/** 楽曲データのArrayList */
	private ArrayList<Song> songs = new ArrayList<>();

	/**
	 * コンストラクタ。
	 **/
	public MusicLibrary() {
		try (BufferedReader br = new BufferedReader(new FileReader("songs.txt"));) {
			String line;
			while ((line = br.readLine()) != null) { //1行目がnullなら33行目へ
				String[] elements = line.split(",");
				String janCode = elements[0];
				String title = elements[1];
                String artist = elements[2];
                int releaseYear = Integer.parseInt(elements[3]); //そのまま数字を打つと文字と認識されるので数字と宣言してる
                String genre = elements[4];
                int stock = Integer.parseInt(elements[5]); 
                songs.add(new Song(janCode, title, artist, releaseYear,genre,stock));
			}
		} catch (IOException e) {
			System.out.println("ファイルが読み込めません。");
		}
	}

	/**
	 * 楽曲を登録する
	 * @param janCode JANコード
	 * @param title 楽曲名
	 * @param artist アーティスト名
	 * @param releaseYear　リリース年
	 * @param genre ジャンル
	 */
	public void addSong(String janCode, String title, String artist, int releaseYear,String genre,int stock) {
		Song b = new Song(janCode, title, artist, releaseYear,genre, stock);
		songs.add(b);
	}

	/**
	 * 全楽曲情報を返却する。
	 * @return 全楽曲情報。
	 */
	public ArrayList<Song> getAllSongs() {
		return songs;
	}

	/**
	 * 対象indexの楽曲データを取得する。
	 * @param index index。
	 * @return indexに対応する楽曲データ。
	 */
	public Song getSongByIndex(int index) {
		return songs.get(index);
	}
	
	/**
	 * JANコードから楽曲の格納場所を返却する。
	 * @param janCode 取得対象のJANコード。
	 * @return 取得対象が格納されたindex。見つからない場合は-1。
	 */
	public int getIndexByJAN(String janCode) {
		int index = -1;
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getJanCode().equals(janCode)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public ArrayList<Integer> getSongsByArtist(String artist) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i = 0;i < songs.size(); i++) {
			if(songs.get(i).getArtist().equals(artist)) {
				index.add(i);
			}
		}
		return index;
	}
	
	public ArrayList<Integer> getSongsByTitle(String title) {
		ArrayList<Integer> searchList = new ArrayList <>();
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getTitle().equals(title)) {
				searchList.add(i);
			}
		}
		return (searchList);
	}
	
	/** ジャンルで検索 */
	public ArrayList<Integer> getSongsByGenre(String genre) {
		ArrayList<Integer> searchGenreList = new ArrayList <>();
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getGenre().equals(genre)) {
				searchGenreList.add(i);
			}
		}
		return (searchGenreList);
	}
	/**
	 * 楽曲を削除する。
	 * @param index 削除対象のindex。
	 */
	public void deleteSong(int index) {
		songs.remove(index);
	}

	/**
	 * ファイルの書き込み処理を行う。
	 */
	public void save() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("songs.txt"));) {
			int count = 0;
			while (songs.size() > count) {
				Song song = songs.get(count);
				bw.write(song.getJanCode() + ","
						+ song.getTitle() + "," 
						+ song.getArtist() + "," 
						+ song.getReleaseYear() + ","
						+ song.getGenre() + ","
						+ song.getStockItems());
				bw.newLine();
				count++;
			}
		} catch (IOException e) {
			System.out.println("ファイルが書き込めません。");
		} catch (Exception e) {
		}
	}

}
