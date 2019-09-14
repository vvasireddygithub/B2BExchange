package com.b2b.exchnage;

import java.util.ArrayList;

public class SFTPUserProfile {
	private String userid;
	private String password;
	private ArrayList<String> publickeys;
	private String homeFolder;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<String> getPublickeys() {
		return publickeys;
	}
	public void setPublickeys(ArrayList<String> publickeys) {
		this.publickeys = publickeys;
	}
	@Override
	public String toString() {
		return "SFTPUserProfile [userid=" + userid + ", publickeys="
				+ publickeys.toString() + ", homeFolder=" + homeFolder + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((homeFolder == null) ? 0 : homeFolder.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((publickeys == null) ? 0 : publickeys.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SFTPUserProfile other = (SFTPUserProfile) obj;
		if (homeFolder == null) {
			if (other.homeFolder != null)
				return false;
		} else if (!homeFolder.equals(other.homeFolder))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (publickeys == null) {
			if (other.publickeys != null)
				return false;
		} else if (!publickeys.equals(other.publickeys))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	public String getHomeFolder() {
		return homeFolder;
	}
	public void setHomeFolder(String homeFolder) {
		this.homeFolder = homeFolder;
	}

}
