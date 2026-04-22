package funs.gamez.dialog;

public class UserScore {
	private int score;
	private String name;

	private long time;

	public UserScore(String name,int scroe ,long time) {
		this(name, scroe);
		this.time=time;
	}
	public UserScore(String name,int scroe ) {
		super();
		this.score = scroe;
		this.name = name;
	}
	public int getScroe() {
		return score;
	}
	public void setScroe(int scroe) {
		this.score = scroe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return  name + "," +score + "," +time+"\n" ;
//		return  name + "," +score+"\n" ;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
