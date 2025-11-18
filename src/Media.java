/**
 * メディアを表すクラス
 * @creator Ozawa
 * @version 1.0
 */
public class Media {

	/** JANコード */
	private String janCode;
	/** 作品名 */
	private String title;


	/**
	 * コンストラクタ。
	 */
	public Media() {
	}

	/**
	* コンストラクタ
	* @param janCode JANコード
	* @param title 作品名
	*/
	public Media(String janCode, String title) {
		this.setJanCode(janCode);
		this.setTitle(title);
	}

	/**
	 * @return janCode JANコード
	 */
	public String getJanCode() {
		return janCode;
	}

	/**
	 * @param janCode JANコード
	 */
	public void setJanCode(String janCode) {
		this.janCode = janCode;
	}

	/**
	 * @return title 作品名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title  作品名
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
