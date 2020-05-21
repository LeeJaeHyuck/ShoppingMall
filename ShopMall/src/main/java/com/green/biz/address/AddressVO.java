package com.green.biz.address;

public class AddressVO {
	private String zip_num;
	private String sido;
	private String gugun;
	private String dong;
	private String zipcode;
	private String bunji;

	public String getzip_num() {
		return zip_num;
	}

	public void setzip_num(String zip_num) {
		this.zip_num = zip_num;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getzipcode() {
		return zipcode;
	}

	public void setzipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	@Override
	public String toString() {
		return "AddressVO [zip_num=" + zip_num + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong
				+ ", zipcode=" + zipcode + ", bunji=" + bunji + "]";
	}

}
