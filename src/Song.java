/**
 * 楽曲を表すクラス
 * @creator Ozawa
 * @version 1.0
 */
public class Song extends Media{

	/** アーティスト名 */
	private String artist;
	/** リリース年 */
	private int releaseYear;
	/** ジャンル */
	private String genre;
	/** 在庫数 */
	private int stockItems;

	/**
	 * コンストラクタ。
	 */
	public Song() {
		super();
	}

	/**
	* コンストラクタ。
	* @param janCode JANコード
	* @param title 作品名
	* @param artist アーティスト名
	* @param releaseYear リリース年
	* @param genre ジャンル
	* @param stock 在庫数
	*/
	public Song(String janCode, String title, String artist, int releaseYear,String genre,int stock) {
		super(janCode, title);
		this.setArtist(artist);
		this.setReleaseYear(releaseYear);
		this.setGenre(genre);
		this.setStockItems(stock);
	}

	/**
	 * @return artist アーティスト名
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist アーティスト名
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return releaseYear リリース年
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear リリース年
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	/**
	 * @return genre ジャンル
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre ジャンル
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return stock 在庫数
	 */
	public int getStockItems() {
		return stockItems;
	}

	/**
	 * @param stock 在庫数
	 */
	public void setStockItems(int stock) {
		this.stockItems = stock;
	}
}
